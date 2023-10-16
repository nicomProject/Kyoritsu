package com.enicom.board.kyoritsu.dao.type.admin;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MenuPageType {
    INTRODUCTION("introductions", "소개글 관리"),
    NOTICE("notices", "공지사항 관리"),
    JOB("jobs", "채용 공고 관리"),
    APPLICANT("applicants", "지원자 조회"),
    INQUIRY("inquires", "채용 문의"),
    ACCOUNT("accounts", "관리자 계정 조회"),
    ACCESS("access", "접속 기록");

    @JsonValue
    private final String code;
    private final String name;
}