package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.Content;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IntroductionsRepository extends CrudRepository<Content, Long>, IntroductionsRepositoryCustom {

    List<Content> findAllByDeleteDateNull();

    List<Content> findAllByRecKey(Long recKey);

    Optional<Content> findByRecKey(Long recKey);

    void deleteByRecKey(Long recKey);

    List<Content> findAllByRecKeyIn(List<Long> recKey);

//    @Modifying
//    @Query("UPDATE tb_content c " +
//            "SET c.deleteUser = :deleteId, c.deleteDate = :deleteTime " +
//            "WHERE c.recKey in :recKey")
//    void deleteListContent(@Param("recKey") List<Long> recKey, @Param("deleteTime") LocalDateTime deleteTime, @Param("deleteId") String deleteId);
//
//    @Modifying
//    @Query("UPDATE tb_content c " +
//            "SET c.deleteUser = :deleteId, c.deleteDate = :deleteTime " )
//    void deleteALLContent(@Param("deleteTime") LocalDateTime deleteTime, @Param("deleteId") String deleteId);
}
