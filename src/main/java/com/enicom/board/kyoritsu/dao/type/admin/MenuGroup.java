package com.enicom.board.kyoritsu.dao.type.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MenuGroup {
    HOME("홈"),
    INTRODUCTION("소개글"),
    NOTICE("공지사항"),
    RECRUIT("채용"),
    SYSTEM("시스템"),
    ;
    private final String name;
}
