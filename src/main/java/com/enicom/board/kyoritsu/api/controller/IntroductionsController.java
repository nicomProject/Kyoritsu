package com.enicom.board.kyoritsu.api.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
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

}
