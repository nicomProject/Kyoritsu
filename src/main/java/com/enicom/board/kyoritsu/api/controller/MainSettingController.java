package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.service.setting.MainSettingService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/main/setting")
public class MainSettingController {
    private final MainSettingService mainSettingService;

    @RequestMapping(path = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 60, desc = "메뉴 목록 조회")
    public ResponseHandler<?> getMenuList() {
        return new ResponseHandler<>(mainSettingService.getMenuList());
    }

    @RequestMapping(path = "/category", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 60, desc = "소개글 메뉴 카테고리 조회")
    public ResponseHandler<?> getCategoryList() {
        return new ResponseHandler<>(mainSettingService.getCategoryList());
    }



}
