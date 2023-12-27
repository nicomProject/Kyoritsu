package com.enicom.board.kyoritsu.dto;

import com.enicom.board.kyoritsu.dao.entity.MainMenu;

import java.util.List;

public interface MainMenuRepositoryCustom {

    List<MainMenu> findAllName();

    List<MainMenu> findAllNameEnglish();

    List<MainMenu> findAllnameJapanese();

}
