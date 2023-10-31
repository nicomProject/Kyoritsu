package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.applicant.ApplicantService;
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
public class ApplicantController {
    private final ApplicantService applicantService;


    @RequestMapping(path = "/applicant/findSelf/{key}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?>  findSelf(@PathVariable Long key){
        System.out.println(applicantService.findAll(key));
        return new ResponseHandler<>(applicantService.findAll(key));
        // return new ResponseHandler<>(inquiryService.findAll(param));
    }
//    @RequestMapping(value = "/inquiry/add", method = RequestMethod.POST)
//    public ResponseHandler<?> Add(@RequestBody @Valid InquiryParam param) throws Exception {
//        return new ResponseHandler<>(inquiryService.add(param));
//    }
//    @RequestMapping(value = "/inquiry/delete", method = RequestMethod.POST)
//    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
//        return new ResponseHandler<>(inquiryService.delete(param));
//    }

}
