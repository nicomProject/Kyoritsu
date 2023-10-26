package com.enicom.board.kyoritsu.api.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.introductions.IntroductionsService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class IntroductionsController {
    private final IntroductionsService introductionsService;

    @RequestMapping(value = "/introductions/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid IntroductionsParam param) throws Exception {
        return new ResponseHandler<>(introductionsService.add(param));
    }

    @RequestMapping(path = "/introductions/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(introductionsService.findAll());
    }

    @RequestMapping(path = "/introductions/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody @Valid IntroductionsParam param){
        System.out.println(introductionsService.findAll(param));
        return new ResponseHandler<>(introductionsService.findAll(param));
    }

    @RequestMapping(value = "/introductions/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid IntroductionsParam param) throws Exception {
        return new ResponseHandler<>(introductionsService.update(param));
    }

    @RequestMapping(value = "/introductions/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        return new ResponseHandler<>(introductionsService.delete(param));
    }

    @RequestMapping(value = "/introductions/check", method = RequestMethod.POST)
    public ResponseHandler<?> check(@RequestBody @Valid MultipleParam param) throws Exception {
        System.out.println("asdasdasdasd");
        System.out.println(param);
        return new ResponseHandler<>(introductionsService.check(param));
    }

}
