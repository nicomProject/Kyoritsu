package com.enicom.board.kyoritsu.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiMapping {
    int order() default 1;
    String desc();
    Class<?> param() default Object.class;
}
