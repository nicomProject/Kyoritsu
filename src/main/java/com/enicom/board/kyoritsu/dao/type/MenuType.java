package com.enicom.board.kyoritsu.dao.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuType {
    INTRO("intro", "소개페이지"),
    NOTICE("notice", "공지사항"),
    RECRUIT("recruit", "채용정보"),
    ;

    private final String code;
    private final String name;

    @JsonValue
    public String getCode() {
        return code;
    }
}
