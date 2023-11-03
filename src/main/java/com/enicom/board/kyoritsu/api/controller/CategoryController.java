package com.enicom.board.kyoritsu.api.controller;


import com.enicom.board.kyoritsu.api.param.CategoryParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.service.category.CategoryService;
import com.enicom.board.kyoritsu.api.service.job.JobService;
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
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public ResponseHandler<?> Add(@RequestBody @Valid CategoryParam param) throws Exception {
        return new ResponseHandler<>(categoryService.add(param));
    }

    @RequestMapping(path = "/category/find", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseHandler<?> find() {
        return new ResponseHandler<>(categoryService.findAll());
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    public ResponseHandler<?> delete(@RequestBody @Valid CategoryParam param) throws Exception {
        return new ResponseHandler<>(categoryService.delete(param));
    }
}
