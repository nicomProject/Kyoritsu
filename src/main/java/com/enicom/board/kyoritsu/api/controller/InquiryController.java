package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
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


    @RequestMapping(path = "/inquiry/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(inquiryService.findAll());
    }

    @RequestMapping(path = "/inquiry/findSelf/{key}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@PathVariable Long key) {
        return new ResponseHandler<>(inquiryService.findAll(key));
    }
    @RequestMapping(value = "/inquiry/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid InquiryParam param) throws Exception {
        System.out.println(param);
        return new ResponseHandler<>(inquiryService.add(param));
    }
//    @RequestMapping(value = "/inquiry/delete", method = RequestMethod.POST)
//    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
//        return new ResponseHandler<>(inquiryService.delete(param));
//    }

}
