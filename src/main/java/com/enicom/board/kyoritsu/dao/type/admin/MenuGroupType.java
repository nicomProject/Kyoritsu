package com.enicom.board.kyoritsu.dao.type.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MenuGroupType {
    HOME("home", "홈"),
    INTRODUCTION("intro", "소개글"),
    NOTICE("notice","공지사항"),
    RECRUIT("recruit","채용"),
    SYSTEM("system", "시스템"),
    ;
    private final String code;
    private final String name;

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static MenuGroupType from(String code) {
        for (MenuGroupType type : MenuGroupType.values()) {
            if (type.name().equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
