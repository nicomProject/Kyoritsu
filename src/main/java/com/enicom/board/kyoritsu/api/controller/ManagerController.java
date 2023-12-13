package com.enicom.board.kyoritsu.api.controller;

import com.enicom.board.kyoritsu.api.annotation.ApiMapping;
import com.enicom.board.kyoritsu.api.param.manager.ManagerInfoParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerPasswordParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.manager.ManagerService;
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
public class ManagerController {
    private final ManagerService managerService;

    @RequestMapping(path = "/managers", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiMapping(order = 50, desc = "[관리자] 관리자 목록 조회")
    public ResponseHandler<?> getManagerList() {
        return new ResponseHandler<>(managerService.findAll());
    }

    @RequestMapping(path = "/manager/add", method = {RequestMethod.POST})
    @ApiMapping(order = 51, desc = "[관리자] 관리자 추가", param = ManagerInfoParam.class)
    public ResponseHandler<?> addManagerInfo(@RequestBody @Valid ManagerInfoParam param) {
        return new ResponseHandler<>(managerService.add(param));
    }

//    @RequestMapping(path = "/manager/mod", method = {RequestMethod.POST})
//    @ApiMapping(order = 52, desc = "[관리자] 관리자 정보 수정", param = ManagerInfoParam.class)
//    public ResponseHandler<?> modifyManagerInfo(@RequestBody @Valid ManagerInfoParam param) {
//        System.out.println(param + "param값");
//        return new ResponseHandler<>(managerService.modify(param));
//    }

//    @RequestMapping(path = "/manager/del", method = {RequestMethod.POST})
//    @ApiMapping(order = 53, desc = "[관리자] 관리자 삭제", param = MultipleParam.class)
//    public ResponseHandler<?> deleteManagerInfo(@RequestBody @Valid MultipleParam param) {
//        return new ResponseHandler<>(managerService.delete(param));
//    }

    @RequestMapping(path = "/manager/init", method = {RequestMethod.POST})
    @ApiMapping(order = 54, desc = "[관리자] 관리자 초기화", param = MultipleParam.class)
    public ResponseHandler<?> initManagerInfo(@RequestBody @Valid MultipleParam param) {
        return new ResponseHandler<>(managerService.init(param));
    }

    @RequestMapping(path = "/manager/mypassword", method = {RequestMethod.POST})
    @ApiMapping(order = 55, desc = "[관리자] 관리자 비밀번호 변경", param = ManagerPasswordParam.class)
    public ResponseHandler<?> changePassword(@RequestBody @Valid ManagerPasswordParam param) {
        return new ResponseHandler<>(managerService.changePassword(param));
    }

    //    @RequestMapping(path = "/introductions/find", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseHandler<?> find() {
//        return new ResponseHandler<>(introductionsService.findAll());
//    }
//
    @RequestMapping(path = "/manager/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody ManagerInfoParam param) {
        return new ResponseHandler<>(managerService.findAll(param));
    }

    @RequestMapping(value = "/manager/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid ManagerInfoParam param) throws Exception {
        return new ResponseHandler<>(managerService.modify(param));
    }

    @RequestMapping(value = "/manager/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        return new ResponseHandler<>(managerService.delete(param));
    }
}
