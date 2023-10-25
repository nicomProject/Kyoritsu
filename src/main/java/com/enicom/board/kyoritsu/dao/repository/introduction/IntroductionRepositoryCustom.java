package com.enicom.board.kyoritsu.dao.repository.introduction;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;

import java.util.List;

public interface IntroductionRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

//    List<Content> findAllByDeleteDateNull();

}
