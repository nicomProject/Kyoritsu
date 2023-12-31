package com.enicom.board.kyoritsu.api.service.inquiry;

import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;

public interface InquiryService {

    PageVO<Inquiry> findAll();

    PageVO<Inquiry> findAll(Long key);

    PageVO<Inquiry> findAllSelfPwd(Long key);

    ResponseDataValue<?> add(InquiryParam param);

    ResponseDataValue<?> update(InquiryParam param);

    ResponseDataValue<?> delete(MultipleParam param);


}
