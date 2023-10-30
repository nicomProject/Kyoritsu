package com.enicom.board.kyoritsu.api.param.manager;

import com.enicom.board.kyoritsu.login.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ManagerInfoParam {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    private Role role;
    private Integer enable;
    private String key;
}
