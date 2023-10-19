package com.enicom.board.kyoritsu.dao.type.admin;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MenuPageType {
    INTRODUCTIONS("introductions", "소개글 관리"),
    NOTICES("notices", "공지사항 관리"),
    JOBS("jobs", "채용 공고 관리"),
    APPLICANTS("applicants", "지원자 조회"),
    INQUIRES("inquires", "채용 문의"),
    ACCOUNTS("accounts", "관리자 계정 조회"),
    ACCESS("access", "접속 기록");

    @JsonValue
    private final String code;
    private final String name;
}
