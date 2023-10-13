package com.enicom.board.kyoritsu.dao.repository.introduction;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;

public interface IntroductionRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

}
