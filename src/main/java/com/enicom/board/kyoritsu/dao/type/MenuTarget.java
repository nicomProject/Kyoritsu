package com.enicom.board.kyoritsu.dao.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MenuTarget {
    SELF("_self"),
    BLANK("_blank");

    private String code;

    public String getCode() {
        return code;
    }
}
