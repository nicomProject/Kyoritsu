package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.param.CodeParam;
import com.enicom.board.kyoritsu.api.type.InfoVO;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Code;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.entity.admin.Menu;

public interface SettingService {
    PageVO<RoleVO> getRoleList();

    PageVO<Menu> getMenuList();

//    PageVO<MainMenu> getAdminMenuList();

    InfoVO<Code> getInitPwd();

    ResponseDataValue<?> setInitPwd(CodeParam param);
}
