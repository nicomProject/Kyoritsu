package com.enicom.board.kyoritsu.api.service.setting;

import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.dao.type.MenuType;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainSettingServiceImpl implements MainSettingService {
    private final SecurityUtil securityUtil;
    private final MainMenuRepository menuRepository;

    @Transactional
    @Override
    public List<MainMenu> getMenuList(String languageValue) {
        List<MainMenu> menuList = null;
        if(languageValue.equals("kr")){
            menuList = menuRepository.findAllName();
        }
        else if(languageValue.equals("eng")){
            menuList = menuRepository.findAllNameEnglish();
        }else if(languageValue.equals("jp")){
            menuList = menuRepository.findAllnameJapanese();
        }
        return menuList;
    }



    @Transactional
    @Override
    public PageVO<MainMenu> getCategoryList() {
        return PageVO.builder(menuRepository.findAllByType(MenuType.INTRO)).build();
    }

}
