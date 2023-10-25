package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.introductions.IntroductionsService;
import com.enicom.board.kyoritsu.api.service.notice.NoticeService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class NoticeController {
    private final NoticeService noticeService;

    @RequestMapping(value = "/notice/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid NoticeParam param) throws Exception {
        return new ResponseHandler<>(noticeService.add(param));
    }

    @RequestMapping(path = "/notice/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(noticeService.findAll());
    }

    @RequestMapping(path = "/notice/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody @Valid NoticeParam param){
        return new ResponseHandler<>(noticeService.findAll(param));
    }

    @RequestMapping(path = "/notice/detail/{recKey}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@PathVariable Long recKey){
        return new ResponseHandler<>(noticeService.findBy(recKey));
    }

    @RequestMapping(value = "/notice/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid NoticeParam param) throws Exception {
        return new ResponseHandler<>(noticeService.update(param));
    }

    @RequestMapping(value = "/notice/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        System.out.println("hihihihi");
        return new ResponseHandler<>(noticeService.delete(param));
    }

}
