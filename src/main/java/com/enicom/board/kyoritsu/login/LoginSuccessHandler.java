package com.enicom.board.kyoritsu.login;

import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.admin.AccessLog;
import com.enicom.board.kyoritsu.dao.repository.AccessLogRepository;
import com.enicom.board.kyoritsu.utils.ParamUtil;
import com.enicom.board.kyoritsu.utils.Utils;
import lombok.RequiredArgsConstructor;
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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private MemberDetailService memberService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemberDetail member = (MemberDetail) authentication.getPrincipal();

        // 접속 로그 추가
        AccessLog accessLog = AccessLog.builder().loginId(member.getId()).loginIp(Utils.getClientIP(request)).build();
        accessLogRepository.save(accessLog);

        // 초기 비밀번호 체크
        String initPwd = ParamUtil.parseString(memberService.getInitPwd());
        int successCode = 220;
        if (passwordEncoder.matches(initPwd, member.getPassword())) {
            successCode = 221;
        }

        memberService.updateAccessDate(member.getUsername());
        response.getWriter().write(ResponseDataValue.builder(successCode).build().toString());
    }
}
