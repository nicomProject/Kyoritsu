package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.service.setting.MainSettingService;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/main/setting")
public class MainSettingController {
    private final MainSettingService mainSettingService;

    @RequestMapping(path = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 60, desc = "메뉴 목록 조회")
    public ResponseHandler<?> getMenuList(Model model, @RequestParam("languageValue") String languageValue, HttpServletRequest request) {

         HttpSession session = request.getSession();
         if(!languageValue.equals("origin")) {
             session.setAttribute("languageValue", languageValue);
         }
        String language = (String) session.getAttribute("languageValue");
        if(language == null){
            language = "kr";
        }
         List<MainMenu> menuDTOList = mainSettingService.getMenuList(language);

         return new ResponseHandler<>(PageVO.builder(menuDTOList).build());
    }


    @RequestMapping(path = "/category", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 60, desc = "소개글 메뉴 카테고리 조회")
    public ResponseHandler<?> getCategoryList() {
        return new ResponseHandler<>(mainSettingService.getCategoryList());
    }



}
