package com.enicom.board.kyoritsu.dao.repository.introduction;

import com.enicom.board.kyoritsu.dao.entity.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IntroductionRepository extends CrudRepository<Content, Long>, IntroductionRepositoryCustom {

    List<Content> findAllByDeleteDateNull();

    List<Content> findAllByRecKey(Long recKey);

    Optional<Content> findByRecKey(Long recKey);

    void deleteByRecKey(Long recKey);

    List<Content> findAllByRecKeyIn(List<Long> recKey);
}
