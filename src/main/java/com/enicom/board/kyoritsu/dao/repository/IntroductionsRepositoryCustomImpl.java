package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class IntroductionsRepositoryCustomImpl implements IntroductionsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Content> deleteListContent(MultipleParam param) {
//        JPAQuery<Content> query = new JPAQuery<>(entityManager);
//        QContent content = QContent.content;
//        query.from(member).where(member.name.eq(name));
//        query.orderBy(member.id.desc());
//        query.limit(1);
//
//        return query.singleResult(member);
//        System.out.println("deleteListContent");
        return null;
    }

    @Override
    public List<Content> deleteALLContent() {
        System.out.println("deleteALLContent");
        return null;
    }
}
