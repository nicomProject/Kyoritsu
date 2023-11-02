package com.enicom.board.kyoritsu.api.service.category;

import com.enicom.board.kyoritsu.api.param.CategoryParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.admin.Category;

public interface CategoryService {


    ResponseDataValue<?> add(CategoryParam param);
    PageVO<Category> findAll();
    ResponseDataValue<?> delete(CategoryParam param);

}
