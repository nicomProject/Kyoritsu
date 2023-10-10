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
@RequestMapping(path = "/api")
public class SettingController {
    private final SettingService settingService;

    @RequestMapping(path = "/setting/roles", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 70, desc = "역할 조회")
    public ResponseHandler<?> getRoleList() {
        return new ResponseHandler<>(settingService.getRoleList());
    }

    @RequestMapping(path = "/setting/initpwd", method = {RequestMethod.GET})
    @ApiMapping(order = 71, desc = "[환경설정] 초기 비밀번호 조회")
    public ResponseHandler<?> getInitPwd() {
        return new ResponseHandler<>(settingService.getInitPwd());
    }

    @RequestMapping(path = "/setting/initpwd", method = {RequestMethod.POST})
    @ApiMapping(order = 72, desc = "[환경설정] 초기 비밀번호 변경", param = CodeParam.class)
    public ResponseHandler<?> setInitPwd(@RequestBody @Valid CodeParam param) {
        return new ResponseHandler<>(settingService.setInitPwd(param));
    }
}
