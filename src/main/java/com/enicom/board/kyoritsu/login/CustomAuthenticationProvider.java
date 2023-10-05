package com.enicom.board.kyoritsu.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MemberDetailService memberDetailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = this.passwordEncoder.encode(authentication.getCredentials().toString());

        log.info("접속 아이디: {}", username);
        UserDetails member = memberDetailService.loadUserByUsername(username);
        if (member == null) {
            throw new BadCredentialsException("일치하는 사용자 아이디가 없습니다.");
        }

        if (!member.isEnabled()) {
            throw new DisabledException("사용이 중지된 계정입니다.\r\n관리자에게 문의하시기 바랍니다.");
        } else if (!member.isAccountNonLocked()) {
            throw new LockedException("로그인 시도 가능 횟수를 초과했습니다.\r\n관리자에게 문의하시기 바랍니다.");
        } else if (!this.passwordEncoder.matches(member.getPassword(), password)) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(member, password, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
