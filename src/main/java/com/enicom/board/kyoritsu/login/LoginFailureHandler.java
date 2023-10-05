package com.enicom.board.kyoritsu.login;

import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private MemberDetailService memberDetailService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String userName = request.getParameter("userName");
        memberDetailService.updateFailureCount(userName);

        log.info("Login Failed, user: {}, message: {}", userName, exception.getMessage());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(ResponseDataValue.builder(480).build().toString());
    }
}
