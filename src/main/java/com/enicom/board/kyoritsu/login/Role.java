package com.enicom.board.kyoritsu.login;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반 사용자", 2),
    ADMIN("ROLE_ADMIN", "관리자", 1),
    SYSTEM("ROLE_SYSTEM", "시스템 관리자", 0);

    private final String name;
    private final String alias;
    private final Integer rank;

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public Integer getRank() {
        return rank;
    }

    public boolean isHigherThan(Role other){
        return this.rank < other.getRank();
    }
    public boolean isLowerThan(Role other){
        return this.rank > other.getRank();
    }
}
