package com.enicom.board.kyoritsu.api.service.applicant;

import com.enicom.board.kyoritsu.api.param.ApplicantParam;
import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Applicant;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;

public interface ApplicantService {
    PageVO<Applicant> findAll(Long key);

    ResponseDataValue<?> add(ApplicantParam param);

}
