package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.entity.admin.Menu;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuGroup;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.ManagerRepository;
import com.enicom.board.kyoritsu.dao.repository.admin.MenuGroupRepository;
import com.enicom.board.kyoritsu.dao.repository.admin.MenuRepository;
import com.enicom.board.kyoritsu.dao.type.admin.MenuGroupType;
import com.enicom.board.kyoritsu.dao.type.admin.MenuType;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@Slf4j
public class DataInitRunner implements ApplicationRunner {
    private final SecurityUtil securityUtil;
    private final CodeRepository codeRepository;
    private final ManagerRepository managerRepository;
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;

    @Autowired
    public DataInitRunner(SecurityUtil securityUtil, CodeRepository codeRepository, ManagerRepository managerRepository,
                          MenuRepository menuRepository, MenuGroupRepository menuGroupRepository) {
        this.securityUtil = securityUtil;
        this.codeRepository = codeRepository;
        this.managerRepository = managerRepository;
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
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
        // 메뉴 그룹 업데이트
        Map<MenuGroupType, MenuGroup> groupStoredList = new HashMap<>();
        menuGroupRepository.findAll().forEach(group -> {
            groupStoredList.put(group.getCode(), group);
        });

        MenuGroup home = MenuGroup.builder(MenuGroupType.HOME).orderSeq(0).build();
        MenuGroup homepage = MenuGroup.builder(MenuGroupType.HOMEPAGE).orderSeq(1).build();
        MenuGroup recruit = MenuGroup.builder(MenuGroupType.RECRUIT).orderSeq(3).build();
        MenuGroup system = MenuGroup.builder(MenuGroupType.SYSTEM).orderSeq(4).build();

        if(groupStoredList.containsKey(MenuGroupType.HOME)){
            home = groupStoredList.get(MenuGroupType.HOME);
        }
        if(groupStoredList.containsKey(MenuGroupType.HOMEPAGE)){
            homepage = groupStoredList.get(MenuGroupType.HOMEPAGE);
        }
        if(groupStoredList.containsKey(MenuGroupType.RECRUIT)){
            recruit = groupStoredList.get(MenuGroupType.RECRUIT);
        }
        if(groupStoredList.containsKey(MenuGroupType.SYSTEM)){
            system = groupStoredList.get(MenuGroupType.SYSTEM);
        }

        menuGroupRepository.saveAll(Arrays.asList(home, homepage, recruit, system));

        // 메뉴 목록 업데이트
        Map<MenuType, Menu> menuStoredList = new HashMap<>();
        menuRepository.findAll().forEach(menu -> {
            menuStoredList.put(menu.getCode(), menu);
        });

        List<Menu> menuList = new ArrayList<>();
        if (!menuStoredList.containsKey(MenuType.DASHBOARD)) {
            menuList.add(Menu.builder(MenuType.DASHBOARD).group(home).orderSeq(0).icon("fas fa-tachometer-alt").build());
        }
        if (!menuStoredList.containsKey(MenuType.INTRODUCTION)) {
            menuList.add(Menu.builder(MenuType.INTRODUCTION).group(homepage).orderSeq(1).icon("fas fa-handshake").build());
        }
        if (!menuStoredList.containsKey(MenuType.NOTICE)) {
            menuList.add(Menu.builder(MenuType.NOTICE).group(homepage).orderSeq(2).icon("fas fa-volume-down").build());
        }
        if (!menuStoredList.containsKey(MenuType.JOB)) {
            menuList.add(Menu.builder(MenuType.JOB).group(recruit).orderSeq(3).icon("fas fa-exclamation-circle").build());
        }
        if (!menuStoredList.containsKey(MenuType.APPLICANT)) {
            menuList.add(Menu.builder(MenuType.APPLICANT).group(recruit).orderSeq(4).icon("fas fa-id-badge").build());
        }
        if (!menuStoredList.containsKey(MenuType.INQUIRY)) {
            menuList.add(Menu.builder(MenuType.INQUIRY).group(recruit).orderSeq(5).icon("fas fa-question-circle").build());
        }
        if (!menuStoredList.containsKey(MenuType.ACCESS)) {
            menuList.add(Menu.builder(MenuType.ACCESS).group(system).orderSeq(6).icon("fas fa-sign-in-alt").build());
        }
        if (!menuStoredList.containsKey(MenuType.ACCOUNT)) {
            menuList.add(Menu.builder(MenuType.ACCOUNT).group(system).orderSeq(7).icon("fas fa-users-cog").build());
        }

        log.info("메뉴 {}건 추가됨", menuList.size());
        menuRepository.saveAll(menuList);
    }
}
