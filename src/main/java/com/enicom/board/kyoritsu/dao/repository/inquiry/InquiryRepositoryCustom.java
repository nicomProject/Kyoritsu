package com.enicom.board.kyoritsu.dao.repository.inquiry;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;

public interface InquiryRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();

}
