package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.inquiry.InquiryService;
import com.enicom.board.kyoritsu.api.type.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Slf4j
public class InquiryController {
    private final InquiryService inquiryService;

    @RequestMapping(value = "/inquiry/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid InquiryParam param) throws Exception {
        return new ResponseHandler<>(inquiryService.add(param));
    }

    @RequestMapping(path = "/inquiry/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(inquiryService.findAll());
    }

    @RequestMapping(path = "/inquiry/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody @Valid InquiryParam param) {
        return new ResponseHandler<>(inquiryService.findAll(param));
    }

    @RequestMapping(value = "/inquiry/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid InquiryParam param) throws Exception {
        return new ResponseHandler<>(inquiryService.update(param));
    }

    @RequestMapping(value = "/inquiry/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        return new ResponseHandler<>(inquiryService.delete(param));
    }

}
