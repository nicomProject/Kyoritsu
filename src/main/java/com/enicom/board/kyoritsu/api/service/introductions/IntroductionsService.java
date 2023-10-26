package com.enicom.board.kyoritsu.api.service.introductions;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerInfoParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerPasswordParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;

public interface IntroductionsService {

    PageVO<?> findAll();

    PageVO<Content> findAll(IntroductionsParam param);
    ResponseDataValue<?> add(IntroductionsParam param);
    ResponseDataValue<?> update(IntroductionsParam param);
    ResponseDataValue<?> delete(MultipleParam param);

    ResponseDataValue<?> check(MultipleParam param);


}
