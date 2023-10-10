package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.Menu;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;

public interface SettingService {
    PageVO<MenuAdmin> getMenuList();
}
