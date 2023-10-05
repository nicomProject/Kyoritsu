package com.enicom.board.kyoritsu.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
public class MemberDetail implements UserDetails {
    private String id;
    private String password;

    @Builder.Default
    private String name = "관리자";

    @Builder.Default
    private Role role = Role.ADMIN;

    @Builder.Default
    private Integer enable = 1;

    @Builder.Default
    private Integer failureCnt = 1;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(Role.values())
                .filter(r -> r.ordinal() <= role.ordinal())
                .forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));

        return authorities;
    }

    public List<Role> getRoles() {
        return Arrays.stream(Role.values()).filter(r -> r.ordinal() <= role.ordinal()).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return failureCnt < 5;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable == 1;
    }
}
