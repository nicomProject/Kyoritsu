package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.Menu;

public interface MainSettingService {

    PageVO<Menu> getMenuList();

}
