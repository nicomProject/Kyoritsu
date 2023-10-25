package com.enicom.board.kyoritsu.login;

import com.enicom.board.kyoritsu.dao.entity.Code;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.repository.manager.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberDetailService implements UserDetailsService {
    private final ManagerRepository managerRepository;
    private final SecurityUtil securityUtil;


    @Autowired
    public MemberDetailService(ManagerRepository managerRepository, SecurityUtil securityUtil) {
        this.managerRepository = managerRepository;
        this.securityUtil = securityUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Manager> managerOptional = managerRepository.findByUserId(username);
        if (!managerOptional.isPresent()) {
            throw new UsernameNotFoundException("이용자를 찾을 수 없습니다.");
        }
        return managerOptional.get().toMember();
    }

    public void updateFailureCount(String username) {
        managerRepository.findByUserId(username).ifPresent(manager -> {
            manager.setFailureCnt(manager.getFailureCnt() + 1);
            managerRepository.save(manager);
        });
    }

    public void updateAccessDate(String username) {
        managerRepository.findByUserId(username).ifPresent(manager -> {
            manager.setFailureCnt(0);
            manager.setLoginDate(LocalDateTime.now());
            managerRepository.save(manager);
        });
    }

    public String getInitPwd() {
        Code code = securityUtil.getInitPwd();
        return code.getValue1();
    }
}
