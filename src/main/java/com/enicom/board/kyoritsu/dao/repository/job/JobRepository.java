package com.enicom.board.kyoritsu.dao.repository.job;

import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends CrudRepository<Job, Long>, JobRepositoryCustom {

    List<Job> findAllByDeleteDateNull();

    Optional<Job> findByRecKey(Long recKey);

    List<Job> findAllByRecKey(Long recKey);

    List<Job> findAllByDeleteDateNullAndCategory(String category);

    List<Job> findByTitleContaining(String title);


}
