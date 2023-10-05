package com.enicom.board.kyoritsu.aop;

import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import com.enicom.board.kyoritsu.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Slf4j
public class ControllerAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && "
            + "@annotation(requestMapping) && execution(* *(..))")
    private void controller(RequestMapping requestMapping) {
    }

    @Around(value = "controller(mapping)", argNames = "pjp,mapping")
    public Object errorHandler(ProceedingJoinPoint pjp, RequestMapping mapping) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = Utils.getClientIP(request);

        Object[] args = pjp.getArgs();
        String uri = request.getRequestURI();

        log.info("* REQUEST [url: {}, ip: {}, params: {}]", uri, ip, Arrays.toString(args));

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseHandler<>(400);
        }
    }
}
