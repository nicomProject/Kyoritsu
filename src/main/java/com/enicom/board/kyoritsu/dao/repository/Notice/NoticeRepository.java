package com.enicom.board.kyoritsu.dao.repository.Notice;

import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends CrudRepository<Notice, Long>, NoticeRepositoryCustom {

    List<Notice> findAllByDeleteDateNull();

    Optional<Notice> findByRecKey(Long recKey);

    List<Notice> findAllByRecKey(Long recKey);

}
