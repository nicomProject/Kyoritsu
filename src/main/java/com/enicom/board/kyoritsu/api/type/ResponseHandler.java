package com.enicom.board.kyoritsu.api.type;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler<T> extends ResponseEntity<ResponseDataValue> {
    public ResponseHandler(int code, Object body) {
        super(ResponseDataValue.builder(code, body).build(), HttpStatus.OK);
    }

    public ResponseHandler(ResponseDataValue<?> data) {
        super(data, data.getStatus());
    }

    public ResponseHandler(PageVO<?> page) {
        super(ResponseDataValue.builder(200, page).build(), HttpStatus.OK);
    }

    public ResponseHandler(InfoVO<?> info) {
        super(ResponseDataValue.builder(200, info).build(), HttpStatus.OK);
    }

    public ResponseHandler(int code) {
        super(ResponseDataValue.builder(code).build(), HttpStatus.OK);
    }
}
