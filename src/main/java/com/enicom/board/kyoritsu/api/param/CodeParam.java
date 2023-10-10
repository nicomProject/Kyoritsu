package com.enicom.board.kyoritsu.api.param;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CodeParam {
    @NotNull(message = "변경하실 코드 값을 입력해주세요.")
    private String value;
}
