package com.enicom.board.kyoritsu.dao.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MenuType {
    INTRO("intro", "소개페이지"),

    NOTICE("notice", "공지사항"),
    RECRUIT("recruit", "채용정보"),
    ;

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
