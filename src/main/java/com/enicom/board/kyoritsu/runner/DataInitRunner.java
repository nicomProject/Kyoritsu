package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.ManagerRepository;
import com.enicom.board.kyoritsu.dao.repository.MenuAdminRepository;
import com.enicom.board.kyoritsu.dao.type.admin.MenuGroup;
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
        configureAdminMenu();
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

    private void configureAdminMenu() {
        Map<MenuType, MenuAdmin> storeList = new HashMap<>();
        menuAdminRepository.findAll().forEach(MenuAdmin -> {
            storeList.put(MenuAdmin.getCode(), MenuAdmin);
        });

        List<MenuAdmin> menuList = new ArrayList<>();
        if (!storeList.containsKey(MenuType.INTRODUCTION)) {
            menuList.add(MenuAdmin.builder(MenuType.INTRODUCTION).group(MenuGroup.INTRODUCTION).order(1).icon("fas fa-handshake").build());
        }
        if (!storeList.containsKey(MenuType.NOTICE)) {
            menuList.add(MenuAdmin.builder(MenuType.NOTICE).group(MenuGroup.NOTICE).icon("fas fa-volume-down").build());
        }
        if (!storeList.containsKey(MenuType.JOB)) {
            menuList.add(MenuAdmin.builder(MenuType.JOB).group(MenuGroup.RECRUIT).order(3).icon("fas fa-exclamation-circle").build());
        }
        if (!storeList.containsKey(MenuType.APPLICANT)) {
            menuList.add(MenuAdmin.builder(MenuType.APPLICANT).group(MenuGroup.RECRUIT).order(4).icon("fas fa-id-badge").build());
        }
        if (!storeList.containsKey(MenuType.INQUIRY)) {
            menuList.add(MenuAdmin.builder(MenuType.INQUIRY).group(MenuGroup.RECRUIT).order(5).icon("fas fa-question-circle").build());
        }
        if (!storeList.containsKey(MenuType.ACCESS)) {
            menuList.add(MenuAdmin.builder(MenuType.ACCESS).group(MenuGroup.SYSTEM).order(6).icon("fas fa-sign-in-alt").build());
        }
        if (!storeList.containsKey(MenuType.ACCOUNT)) {
            menuList.add(MenuAdmin.builder(MenuType.ACCOUNT).group(MenuGroup.SYSTEM).order(7).icon("fas fa-users-cog").build());
        }

        log.info("메뉴 {}건 추가됨", menuList.size());
        menuAdminRepository.saveAll(menuList);
    }
}
