package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.param.CodeParam;
import com.enicom.board.kyoritsu.api.service.setting.SettingService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/adm/setting")
public class SettingController {
    private final SettingService settingService;

    @RequestMapping(path = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 60, desc = "메뉴 목록 조회")
    public ResponseHandler<?> getMenuList() {
        return new ResponseHandler<>(settingService.getMenuList());
    }
//    @RequestMapping(path = "/admin/menus", method = {RequestMethod.GET, RequestMethod.POST})
//    @ApiMapping(order = 60, desc = "메뉴 목록 조회")
//    public ResponseHandler<?> getAdminMenuList() {
//        System.out.println("asdasdasdasd");
//        return new ResponseHandler<>(settingService.getMenuList());
//    }
    @RequestMapping(path = "/roles", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 70, desc = "역할 조회")
    public ResponseHandler<?> getRoleList() {
        return new ResponseHandler<>(settingService.getRoleList());
    }

    @RequestMapping(path = "/initpwd", method = {RequestMethod.GET})
    @ApiMapping(order = 71, desc = "[환경설정] 초기 비밀번호 조회")
    public ResponseHandler<?> getInitPwd() {
        return new ResponseHandler<>(settingService.getInitPwd());
    }

    @RequestMapping(path = "/initpwd", method = {RequestMethod.POST})
    @ApiMapping(order = 72, desc = "[환경설정] 초기 비밀번호 변경", param = CodeParam.class)
    public ResponseHandler<?> setInitPwd(@RequestBody @Valid CodeParam param) {
        return new ResponseHandler<>(settingService.setInitPwd(param));
    }
}


