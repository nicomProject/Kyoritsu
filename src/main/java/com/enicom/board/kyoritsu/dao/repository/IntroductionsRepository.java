package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IntroductionsRepository extends CrudRepository<Content, Long> {

    List<Content> findAllBy();

    List<Content> findAllByRecKey(Long recKey);

    Optional<Content> findByRecKey(Long recKey);

    void deleteByRecKey(Long recKey);
    void deleteByRecKeyIn(List<Long> recKey);

    //void deleteAllByRecKey(List<String> recKey);

}
