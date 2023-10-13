package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;

public interface MainSettingService {

    PageVO<MainMenu> getMenuList();

}
