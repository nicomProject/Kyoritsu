package com.enicom.board.kyoritsu.runner;

import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.dao.type.MenuType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class MainDataInitRunner implements ApplicationRunner {
    private final MainMenuRepository menuRepository;

    @Autowired
    public MainDataInitRunner(MainMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configureMenu();
    }

    private void configureMenu() {
        Map<MenuType, MainMenu> storeList = new HashMap<>();

        List<MainMenu> menuList = new ArrayList<>();

        MainMenu company = MainMenu.builder().order(1).name("회사소개").nameEnglish("About Us").nameJapanese("会社紹介").type(MenuType.GROUP).build();
        MainMenu product = MainMenu.builder().order(2).name("사업영역").nameEnglish("Business area").nameJapanese("事業領域").type(MenuType.GROUP).build();
        MainMenu notice = MainMenu.builder().order(3).name("공지사항").nameEnglish("announcement").nameJapanese("お知らせ").type(MenuType.GROUP).build();
        MainMenu recruit = MainMenu.builder().order(4).name("채용정보").nameEnglish("Recruitment information").nameJapanese("採用情報").type(MenuType.GROUP).build();
        menuRepository.saveAll(Arrays.asList(company, product, notice, recruit));



        menuList.add(MainMenu.builder().order(1).menu(company).url("/intro/overview").name("기업개요").nameEnglish("Company Overview").nameJapanese("企業概要").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(2).menu(company).url("/intro/vision").name("경영이념/비전").nameEnglish("Management philosophy/vision").nameJapanese("経営理念/ビジョン").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(3).menu(company).url("/intro/history").name("연혁").nameEnglish("history").nameJapanese("歴史").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(4).menu(company).url("/intro/organization").name("조직도").nameEnglish("organization").nameJapanese("組織図").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(5).menu(company).url("/intro/location").name("오시는길").nameEnglish("way to come").nameJapanese("アクセス").type(MenuType.INTRO).build());

        menuList.add(MainMenu.builder().order(1).menu(product).url("/business/dormitory").name("기숙사").nameEnglish("dormitory").nameJapanese("寮").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(2).menu(product).url("/business/dormyinn").name("도미인").nameEnglish("Dormy Inn").nameJapanese("ドーミー").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(3).menu(product).url("/business/resort").name("리조트").nameEnglish("resort").nameJapanese("リゾート").type(MenuType.INTRO).build());
        menuList.add(MainMenu.builder().order(4).menu(product).url("/business/seniorlife").name("노인주택").nameEnglish("Senior housing").nameJapanese("高齢住宅").type(MenuType.INTRO).build());

        menuList.add(MainMenu.builder().order(1).menu(notice).url("/notice/notice").name("공지사항").nameEnglish("announcement").nameJapanese("お知らせ").type(MenuType.GENERAL).build());

        menuList.add(MainMenu.builder().order(1).menu(recruit).url("/recruit/info").name("채용 안내").nameEnglish("Recruitment information").nameJapanese("採用案内").type(MenuType.GENERAL).build());
        menuList.add(MainMenu.builder().order(2).menu(recruit).url("/recruit/notice").name("채용 공고").nameEnglish("Recruitment notice").nameJapanese("採用発表").type(MenuType.GENERAL).build());
        menuList.add(MainMenu.builder().order(3).menu(recruit).url("/recruit/apply").name("채용 지원").nameEnglish("Recruitment support").nameJapanese("採用支援").type(MenuType.GENERAL).build());
        menuList.add(MainMenu.builder().order(4).menu(recruit).url("/recruit/inquire").name("채용 문의").nameEnglish("Recruitment Inquiry").nameJapanese("採用お問い合わせ").type(MenuType.GENERAL).build());

        menuRepository.saveAll(menuList);

        log.info("메뉴 {}건 추가됨", menuList.size());
    }
}
