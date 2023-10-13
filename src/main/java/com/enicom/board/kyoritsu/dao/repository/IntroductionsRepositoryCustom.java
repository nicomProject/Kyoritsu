package com.enicom.board.kyoritsu.dao.repository;

import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IntroductionsRepositoryCustom {
    void deleteListContent(@Param("recKey") List<Long> recKey, @Param("deleteTime") LocalDateTime deleteTime, @Param("deleteId") String deleteId);
}
