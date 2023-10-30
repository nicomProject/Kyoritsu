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
    public PageVO<Inquiry> findAll(InquiryParam param) {
        return PageVO.builder(inquiryRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Transactional
    @Override
    public PageVO<Inquiry> findAll() {
        return PageVO.builder(inquiryRepository.findAllByDeleteDateNull()).build();
    }

    @Override
    public ResponseDataValue<?> add(InquiryParam param) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MemberDetail member = securityUtil.getCurrentUser();

        Optional<Inquiry> inquiryOptional = inquiryRepository.findByRecKey(Long.valueOf(param.getKey()));
        if(!inquiryOptional.isPresent()){
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다").build();
        }

        Inquiry inquiry = inquiryOptional.get();
        param.applyTo(inquiry);
        inquiry.setAnswer(param.getAnswer());
        inquiry.setAnswerUser(member.getId());
        inquiry.setAnswerDate(LocalDateTime.now());

        inquiryRepository.save(inquiry);

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {
        MemberDetail member = securityUtil.getCurrentUser();
        MultipleType type = param.getType();
        LocalDateTime deleteTime = LocalDateTime.now();

        if (type.equals(MultipleType.ONE)) {
            Optional<Inquiry> inquiryOptional = inquiryRepository.findByRecKey(Long.valueOf(param.getId()));
            if (inquiryOptional.isPresent()) {
                Inquiry inquiry = inquiryOptional.get();
                inquiry.setDeleteDate(LocalDateTime.now());
                inquiry.setDeleteUser(member.getId());
            }
        }
        else if (type.equals(MultipleType.LIST)) {
            inquiryRepository.deleteListContent(param);
        }
        else if(type.equals(MultipleType.SPECIFIC)){
            inquiryRepository.deleteALLContent();
        }

        return ResponseDataValue.builder(200).build();
    }

}