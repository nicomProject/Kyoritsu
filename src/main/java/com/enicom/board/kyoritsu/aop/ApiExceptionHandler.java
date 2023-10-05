package com.enicom.board.kyoritsu.aop;

import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseHandler<?> parameterError(MethodArgumentNotValidException ex) {
        return new ResponseHandler<>(410);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseHandler<?> jsonParsingError(HttpMessageNotReadableException ex) {
        try {
            log.info("message: {}", ex.getHttpInputMessage().getBody().read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseHandler<>(401);
    }
}
