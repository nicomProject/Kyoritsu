package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.job.JobService;
import com.enicom.board.kyoritsu.api.service.notice.NoticeService;
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
public class JobController {
    private final JobService jobService;

    @RequestMapping(value = "/job/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid JobParam param) throws Exception {
        return new ResponseHandler<>(jobService.add(param));
    }

    @RequestMapping(path = "/job/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(jobService.findAll());
    }

    @RequestMapping(path = "/job/findCategorySelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findCategorySelf(@RequestBody JobParam param) {
        return new ResponseHandler<>(jobService.findAllCategory(param));
    }

    @RequestMapping(path = "/job/findSelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> findSelf(@RequestBody @Valid JobParam param){
        return new ResponseHandler<>(jobService.findAll(param));
    }

    @RequestMapping(value = "/job/update", method = RequestMethod.POST)
    public ResponseHandler<?> update(@RequestBody @Valid JobParam param) throws Exception {
        return new ResponseHandler<>(jobService.update(param));
    }

    @RequestMapping(value = "/job/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid MultipleParam param) throws Exception {
        return new ResponseHandler<>(jobService.delete(param));
    }

    @RequestMapping(path = "/job/search", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> search(@RequestBody JobParam param) {
        return new ResponseHandler<>(jobService.findSearch(param));
    }


}
