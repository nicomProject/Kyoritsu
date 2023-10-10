package com.enicom.board.kyoritsu.api.service.setting;


import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.Menu;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.dao.repository.MenuAdminRepository;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final SecurityUtil securityUtil;
    private final MenuAdminRepository menuAdminRepository;
    private final CodeRepository codeRepository;


    @Override
    public PageVO<MenuAdmin> getMenuList() {
        List<Role> roles = securityUtil.getRoleList();
        return PageVO.builder(menuAdminRepository.findAll()).build();
    }

}
