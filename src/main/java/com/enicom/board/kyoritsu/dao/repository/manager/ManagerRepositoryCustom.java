package com.enicom.board.kyoritsu.dao.repository.manager;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;

public interface ManagerRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

}
