package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.ManagerRepository;
import com.enicom.board.kyoritsu.dao.repository.MenuAdminRepository;
import com.enicom.board.kyoritsu.dao.type.admin.MenuType;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DataInitRunner implements ApplicationRunner {
    private final SecurityUtil securityUtil;
    private final CodeRepository codeRepository;
    private final ManagerRepository managerRepository;
    private final MenuAdminRepository menuAdminRepository;

    @Autowired
    public DataInitRunner(SecurityUtil securityUtil, CodeRepository codeRepository, ManagerRepository managerRepository,
                          MenuAdminRepository menuAdminRepository) {
        this.securityUtil = securityUtil;
        this.codeRepository = codeRepository;
        this.managerRepository = managerRepository;
        this.menuAdminRepository = menuAdminRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configureCode();
        configureManager();
        configureMenu();
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

    private void configureMenu() {
        Map<String, MenuAdmin> storeList = new HashMap<>();
        menuAdminRepository.findAll().forEach(MenuAdmin -> {
            storeList.put(MenuAdmin.getUrl().replace("/admin/", ""), MenuAdmin);
        });

        List<MenuAdmin> menuList = new ArrayList<>();
        if (!storeList.containsKey(MenuType.INTRODUCTION.getCode())) {
            menuList.add(
                    MenuAdmin.builder().order(1).name(MenuType.INTRODUCTION.getName())
                            .url("/admin/" + MenuType.INTRODUCTION.getCode())
                            .icon("fas fa-handshake")
                            .build());
        }
        if (!storeList.containsKey(MenuType.NOTICE.getCode())) {
            menuList.add(MenuAdmin.builder().order(2).name(MenuType.NOTICE.getName())
                    .url("/admin/" + MenuType.NOTICE.getCode())
                    .icon("fas fa-volume-down")
                    .build());
        }
        if (!storeList.containsKey(MenuType.JOB.getCode())) {
            menuList.add(MenuAdmin.builder().order(3).name(MenuType.JOB.getName())
                    .url("/admin/" + MenuType.JOB.getCode())
                    .icon("fas fa-exclamation-circle")
                    .build());
        }
        if (!storeList.containsKey(MenuType.APPLICANT.getCode())) {
            menuList.add(MenuAdmin.builder().order(4).name(MenuType.APPLICANT.getName())
                    .url("/admin/" + MenuType.APPLICANT.getCode())
                    .icon("fas fa-id-badge")
                    .build());
        }
        if (!storeList.containsKey(MenuType.INQUIRY.getCode())) {
            menuList.add(MenuAdmin.builder().order(5).name(MenuType.INQUIRY.getName())
                    .url("/admin/" + MenuType.INQUIRY.getCode())
                    .icon("fas fa-question-circle")
                    .build());
        }
        if (!storeList.containsKey(MenuType.ACCOUNT.getCode())) {
            menuList.add(MenuAdmin.builder().order(6).name(MenuType.ACCOUNT.getName())
                    .url("/admin/" + MenuType.ACCOUNT.getCode())
                    .icon("fas fa-users-cog")
                    .build());
        }

        log.info("메뉴 {}건 추가됨", menuList.size());
        menuAdminRepository.saveAll(menuList);
    }
}
