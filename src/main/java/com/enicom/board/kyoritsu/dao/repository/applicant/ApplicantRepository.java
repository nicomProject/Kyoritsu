package com.enicom.board.kyoritsu.dao.repository.applicant;

import com.enicom.board.kyoritsu.dao.entity.Applicant;
import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import com.enicom.board.kyoritsu.dao.repository.inquiry.InquiryRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends CrudRepository<Applicant, Long>, InquiryRepositoryCustom {

    Optional<Applicant> findByRecKey(Long recKey);

    List<Applicant> findAllByRecKey(Long recKey);


}
