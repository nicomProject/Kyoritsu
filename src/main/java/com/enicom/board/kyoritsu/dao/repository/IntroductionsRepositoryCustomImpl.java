package com.enicom.board.kyoritsu.dao.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class IntroductionsRepositoryCustomImpl implements IntroductionsRepositoryCustom {
    @Override
    public void deleteListContent(List<Long> recKey, LocalDateTime deleteTime, String deleteId) {
    }
}
