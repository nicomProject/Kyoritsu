package com.enicom.board.kyoritsu.api.exception;

import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private int code;
    private String desc;
    private Object result;

    public ApiException(int code, Object result) {
        super(ResponseDataValue.makeMessage(code));

        this.code = code;
        this.desc = ResponseDataValue.makeMessage(code);
        this.result = result;
    }
}
