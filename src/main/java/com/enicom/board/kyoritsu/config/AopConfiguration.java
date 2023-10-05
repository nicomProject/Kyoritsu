package com.enicom.board.kyoritsu.config;

import com.enicom.board.kyoritsu.aop.ControllerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {
    @Bean
    public ControllerAspect controllerAspect() {
        return new ControllerAspect();
    }
}
