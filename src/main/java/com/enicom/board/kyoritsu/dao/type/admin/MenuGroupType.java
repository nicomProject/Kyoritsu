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
    HOMEPAGE("homepage", "홈페이지 관리"),
    RECRUIT("recruit","채용 관리"),
    SYSTEM("system", "시스템 관리"),
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
