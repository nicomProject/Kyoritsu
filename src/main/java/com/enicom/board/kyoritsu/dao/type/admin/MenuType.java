package com.enicom.board.kyoritsu.dao.type.admin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MenuType {
    INTRODUCTIONS("introductions", "소개글"),
    ANNOUNCEMENT("announcement", "공지사항 관리"),
    JOBPOSTING("jobposting", "채용 공고"),
    APPLICANTCHECK("applicantcheck", "지원자 조회"),
    JOBINQUIRY("jobinquiry", "채용 문의"),
    ADMINACCOUNTSCHECK("adminaccountscheck", "관리자 계정 조회");

    private final String code;
    private final String name;

    public String id() {
        return code;
    }

    public String display() {
        return name;
    }
}
