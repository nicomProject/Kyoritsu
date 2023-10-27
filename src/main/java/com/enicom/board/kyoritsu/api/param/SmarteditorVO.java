package com.enicom.board.kyoritsu.api.param;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class SmarteditorVO {
    private MultipartFile filedata;
    private String callback;
    private String callback_func;
}
