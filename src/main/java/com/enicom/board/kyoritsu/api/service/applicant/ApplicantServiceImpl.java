package com.enicom.board.kyoritsu.api.service.applicant;

import com.enicom.board.kyoritsu.api.param.ApplicantParam;
import com.enicom.board.kyoritsu.api.param.InquiryParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.config.EmailConfiguration;
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
        return PageVO.builder(applicantRepository.findAllByRecKey(key)).build();
    }

    @Override
    public ResponseDataValue<?> add(ApplicantParam param) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MemberDetail member = securityUtil.getCurrentUser();

        Optional<Applicant> applicantOptional = applicantRepository.findByRecKey(Long.valueOf(param.getKey()));
        if(!applicantOptional.isPresent()){
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다").build();
        }

        Applicant applicant = applicantOptional.get();
        applicant.setContentAnswer(param.getContentsAnswer());
        applicant.setFormTag(param.getFormTag());
        applicant.setPassYn(param.getPassYn());
        applicant.setAnswerId(member.getId());

        applicantRepository.save(applicant);

        EmailConfiguration.sendMail(applicant.getEmail(), param.getContentsAnswer());

        return ResponseDataValue.builder(200).build();
    }
}