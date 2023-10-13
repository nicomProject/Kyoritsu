package com.enicom.board.kyoritsu.dao.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuType {
    GROUP("group", "메뉴 그룹"),
    GENERAL("general", "일반 페이지"),
    INTRO("intro", "소개 페이지"),
    ;

    private final String code;
    private final String name;

    @JsonValue
    public String getCode() {
        return code;
    }
}
