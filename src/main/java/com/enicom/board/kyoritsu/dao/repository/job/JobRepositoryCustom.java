package com.enicom.board.kyoritsu.dao.repository.job;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;

public interface JobRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

}
