package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;

import java.util.List;

public interface MainSettingService {

    List<MainMenu> getMenuList(String languageValue);

    PageVO<MainMenu> getCategoryList();
}
