package com.enicom.board.kyoritsu.login;

import com.enicom.board.kyoritsu.dao.entity.Code;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.entity.admin.Menu;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.dao.repository.admin.MenuRepository;
import com.enicom.board.kyoritsu.dao.type.CodeGroup;
import com.enicom.board.kyoritsu.dao.type.admin.MenuPageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityUtil {
    private final SessionRegistry sessionRegistry;
    private final CodeRepository codeRepository;
    private final BCryptPasswordEncoder encoder;
    private final MenuRepository menuRepository;

    @Value("${system.initPwd}")
    private String initPwd;

    @Autowired
    public SecurityUtil(SessionRegistry sessionRegistry, CodeRepository codeRepository, BCryptPasswordEncoder encoder,
                        MenuRepository menuRepository) {
        this.sessionRegistry = sessionRegistry;
        this.codeRepository = codeRepository;
        this.encoder = encoder;
        this.menuRepository = menuRepository;
    }

    /**
     * [SecurityUtil] 초기 비밀번호
     *
     * @return 초기비밀번호 code 객체
     */
    public Code getInitPwd() {
        return codeRepository.findByGroupAndCode(CodeGroup.SYSTEM, "initPwd")
                .orElse(Code.builder().group(CodeGroup.SYSTEM).code("initPwd").value1(initPwd).build());
    }

    /**
     * [SecurityUtil] 암호화된 초기 비밀번호
     *
     * @return 암호화된 초기 비밀번호
     */
    public String getEncodedInitPwd() {
        String raw = getInitPwd().getValue1();
        return encoder.encode(raw);
    }

    /**
     * [SecurityUtil] 코드 암호화
     *
     * @param raw 암호화할 코드
     * @return 암호화된 코드
     */
    public String encode(String raw) {
        return encoder.encode(raw);
    }

    public boolean match(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }

    public boolean isEncoded(String encoded) {
        try {
            encoder.upgradeEncoding(encoded);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public MemberDetail getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDetail member;
        try {
            member = (MemberDetail) auth.getPrincipal();
        } catch (Exception e) {
            return null;
        }

        if (!(auth instanceof AnonymousAuthenticationToken) && !sessionRegistry.getAllSessions(auth.getPrincipal(), false).isEmpty()) {
            return member;
        }

        return null;
    }

    public List<Role> getRoleList() {
        List<Role> roles = Collections.emptyList();

        MemberDetail member = getCurrentUser();
        if (member != null) {
            roles = getCurrentUser().getRoles();
        }
        return roles;
    }

    public Optional<Menu> getMenu(String code) {
        code = code.toUpperCase();
        return menuRepository.findByCode(MenuPageType.valueOf(code));
    }

    public Optional<Menu> getDetailMenu(String code) {
        code = code.toUpperCase();
        return menuRepository.findByCodeDetail(code);
    }
}
