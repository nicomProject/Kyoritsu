package com.enicom.board.kyoritsu.dao.repository.notice;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;

public interface NoticeRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

}
