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
            storeList.put(MenuAdmin.getCode(), MenuAdmin);
        });

        List<MenuAdmin> menuList = new ArrayList<>();
        if (!storeList.containsKey(MenuType.INTRODUCTIONS.id())) {
            menuList.add(
                    MenuAdmin.builder().recKey(1L).order(1).name(MenuType.INTRODUCTIONS.display()).code(MenuType.INTRODUCTIONS.id())
                            .build());
        }
        if (!storeList.containsKey(MenuType.ANNOUNCEMENT.id())) {
            menuList.add(MenuAdmin.builder().recKey(2L).order(2).name(MenuType.ANNOUNCEMENT.display()).code(MenuType.ANNOUNCEMENT.id())
                    .build());
        }
        if (!storeList.containsKey(MenuType.JOBPOSTING.id())) {
            menuList.add(MenuAdmin.builder().recKey(3L).order(3).name(MenuType.JOBPOSTING.display()).code(MenuType.JOBPOSTING.id())
                    .build());
        }
        if (!storeList.containsKey(MenuType.APPLICANTCHECK.id())) {
            menuList.add(MenuAdmin.builder().recKey(4L).order(4).name(MenuType.APPLICANTCHECK.display()).code(MenuType.APPLICANTCHECK.id())
                    .build());
        }
        if (!storeList.containsKey(MenuType.JOBINQUIRY.id())) {
            menuList.add(MenuAdmin.builder().recKey(5L).order(5).name(MenuType.JOBINQUIRY.display()).code(MenuType.JOBINQUIRY.id())
                    .build());
        }
        if (!storeList.containsKey(MenuType.ADMINACCOUNTSCHECK.id())) {
            menuList.add(MenuAdmin.builder().recKey(6L).order(6).name(MenuType.ADMINACCOUNTSCHECK.display()).code(MenuType.ADMINACCOUNTSCHECK.id())
                    .build());
        }

        log.info("메뉴 {}건 추가됨", menuList.size());
        menuAdminRepository.saveAll(menuList);
    }


}
