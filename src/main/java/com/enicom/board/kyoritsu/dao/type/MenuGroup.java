package com.enicom.board.kyoritsu.dao.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MenuGroup {
    HOME("홈"),
    INTRODUCTIONS("소개글"),
    ANNOUNCEMENT("공지사항"),
    JOBPOSTING("채용 공고"),
    JOBINQUIRY("채용 문의"),
    SYSTEM("시스템"),
    ;
    private final String name;
}
