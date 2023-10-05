package com.enicom.board.kyoritsu.login;

import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.utils.ParamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private MemberDetailService memberService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemberDetail member = (MemberDetail) authentication.getPrincipal();
        String initPwd = ParamUtil.parseString(memberService.getInitPwd());

        int successCode = 220;
        if (passwordEncoder.matches(initPwd, member.getPassword())) {
            successCode = 221;
        }

        memberService.updateAccessDate(member.getUsername());
        response.getWriter().write(ResponseDataValue.builder(successCode).build().toString());
    }
}
