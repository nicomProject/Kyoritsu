package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.Manager;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.ManagerRepository;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DataInitRunner implements ApplicationRunner {
    private final SecurityUtil securityUtil;
    private final CodeRepository codeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public DataInitRunner(SecurityUtil securityUtil, CodeRepository codeRepository, ManagerRepository managerRepository) {
        this.securityUtil = securityUtil;
        this.codeRepository = codeRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configureCode();
        configureManager();
    }

    private void configureCode() {
        // 코드 추가
        codeRepository.save(securityUtil.getInitPwd());
    }

    private void configureManager() {
        Manager manager = managerRepository.findByUserId("nicom")
                .orElse(Manager.builder().userId("nicom").name("나이콤 관리자").password(securityUtil.getEncodedInitPwd()).createDate(LocalDateTime.now()).build());
        manager.setRole(Role.SYSTEM);
        managerRepository.save(manager);
    }
}
