package com.enicom.board.kyoritsu.api.service.category;

import com.enicom.board.kyoritsu.api.param.CategoryParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Category;

public interface CategoryService {


    ResponseDataValue<?> add(CategoryParam param);
    PageVO<Category> findAll();

//    PageVO<Category> findAllDetail(CategoryParam param);
    ResponseDataValue<?> delete(CategoryParam param);

}
