package com.enicom.board.kyoritsu.dao.repository.inquiry;

import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends CrudRepository<Inquiry, Long>, InquiryRepositoryCustom {

    Optional<Inquiry> findByRecKey(Long recKey);

    List<Inquiry> findAllByRecKey(Long recKey);

}
