package com.enicom.board.kyoritsu.api.service.setting;

import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.Menu;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainSettingServiceImpl implements MainSettingService {
    private final SecurityUtil securityUtil;
    private final MainMenuRepository menuRepository;

    @Override
    public PageVO<Menu> getMenuList() {
        List<Role> roles = securityUtil.getRoleList();
        return PageVO.builder(menuRepository.findAll()).build();
    }

}
