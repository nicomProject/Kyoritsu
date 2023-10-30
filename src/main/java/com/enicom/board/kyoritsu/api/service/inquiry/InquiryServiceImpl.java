package com.enicom.board.kyoritsu.api.service.inquiry;

import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import com.enicom.board.kyoritsu.dao.repository.inquiry.InquiryRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {
    private final SecurityUtil securityUtil;
    private final InquiryRepository inquiryRepository;


    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository, SecurityUtil securityUtil) {
        this.inquiryRepository = inquiryRepository;
        this.securityUtil = securityUtil;
    }


    @Override
    public ResponseDataValue<?> add(InquiryParam param) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MemberDetail member = securityUtil.getCurrentUser();
        System.out.println("1111111" + param);

        Inquiry inquiry = param.create();
        inquiry.setCreateDate(LocalDateTime.now());
        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }


    @Override
    public PageVO<Inquiry> findAll(InquiryParam param) {
        return PageVO.builder(inquiryRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Override
    public ResponseDataValue<?> update(InquiryParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        Optional<Inquiry> inquiryOptional = inquiryRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!inquiryOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Inquiry inquiry = inquiryOptional.get();
        param.applyTo(inquiry);

        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }

}