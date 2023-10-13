package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.Menu;
import com.enicom.board.kyoritsu.dao.repository.MenuRepository;
import com.enicom.board.kyoritsu.dao.type.admin.MenuGroup;
import com.enicom.board.kyoritsu.dao.type.MenuType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MainDataInitRunner implements ApplicationRunner {
    private final MenuRepository menuRepository;

    @Autowired
    public MainDataInitRunner(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configureMenu();
    }

    private void configureMenu() {
        Map<MenuType, Menu> storeList = new HashMap<>();

        List<Menu> menuList = new ArrayList<>();
        if (!storeList.containsKey(MenuType.INTRO)) {
            menuList.add(Menu.builder(MenuType.INTRO).order(1).build());
        }
        if (!storeList.containsKey(MenuType.NOTICE)) {
            menuList.add(Menu.builder(MenuType.NOTICE).order(2).build());
        }
        if (!storeList.containsKey(MenuType.RECRUIT)) {
            menuList.add(Menu.builder(MenuType.RECRUIT).order(3).build());
        }

        log.info("메뉴 {}건 추가됨", menuList.size());
        menuRepository.saveAll(menuList);
    }
}
