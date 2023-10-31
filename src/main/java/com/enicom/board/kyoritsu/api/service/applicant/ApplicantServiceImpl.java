package com.enicom.board.kyoritsu.api.service.applicant;

import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Applicant;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import com.enicom.board.kyoritsu.dao.repository.applicant.ApplicantRepository;
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
public class ApplicantServiceImpl implements ApplicantService {
    private final SecurityUtil securityUtil;
    private final ApplicantRepository applicantRepository;



    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, SecurityUtil securityUtil) {
        this.applicantRepository = applicantRepository;
        this.securityUtil = securityUtil;
    }


    @Transactional
    @Override
    public PageVO<Applicant> findAll(Long key) {
        System.out.println(applicantRepository.findAllByRecKey(key));
        return PageVO.builder(applicantRepository.findAllByRecKey(key)).build();
    }
}