package com.enicom.board.kyoritsu.api.param;

import com.enicom.board.kyoritsu.login.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MenuModifyParam {
    private String code;
    private String name;
    private String icon;
    private Integer order;
    private Role readRole;
    private Role editRole;
    private Integer enable;
}
