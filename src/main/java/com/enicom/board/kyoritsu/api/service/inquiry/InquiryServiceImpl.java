package com.enicom.board.kyoritsu.api.service.inquiry;

import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.dao.repository.inquiry.InquiryRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    @Transactional
    @Override
    public PageVO<Inquiry> findAll(Long key) {
        Optional<Inquiry> inquiryOptional = inquiryRepository.findByRecKey(key);

        if(inquiryOptional.isPresent()){
            Inquiry inquiry = inquiryOptional.get();
            inquiry.setHit(inquiry.getHit() + 1);
            inquiryRepository.save(inquiry);

            return PageVO.builder(inquiry).build();
        }

        return PageVO.builder(inquiryRepository.findAllByRecKey(key)).build();
    }

    public PageVO<Inquiry> findAllSelfPwd(Long key) {
        return PageVO.builder(inquiryRepository.findAllByRecKey(key)).build();
    }

    @Transactional
    @Override
    public PageVO<Inquiry> findAll() {
        return PageVO.builder(inquiryRepository.findAllByDeleteDateNullOrderByCreateDateDesc()).build();
    }

    @Override
    public ResponseDataValue<?> add(InquiryParam param) {

        Inquiry inquiry = param.create();
        param.applyTo(inquiry);
        inquiry.setCreateDate(LocalDateTime.now());

        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> update(InquiryParam param) {

        Optional<Inquiry> inquiryOptional = inquiryRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!inquiryOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Inquiry inquiry = inquiryOptional.get();
        param.applyTo(inquiry);
        inquiry.setUpdateDate(LocalDateTime.now());

        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {

        Optional<Inquiry> contentOptional = inquiryRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!contentOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Inquiry inquiry = contentOptional.get();
        inquiry.setDeleteUser(contentOptional.get().getInquiryName());
        inquiry.setDeleteDate(LocalDateTime.now());

        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }

}