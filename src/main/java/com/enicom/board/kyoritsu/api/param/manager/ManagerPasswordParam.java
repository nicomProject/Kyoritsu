package com.enicom.board.kyoritsu.api.param.manager;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ManagerPasswordParam {
    @NotNull
    private String password;
    @NotNull
    private String newPassword;
    @NotNull
    private String newPasswordConfirm;
    private String key;
}
