package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class IntroductionsRepositoryCustomImpl implements IntroductionsRepositoryCustom {

    private final SecurityUtil securityUtil;

    @Autowired
    public IntroductionsRepositoryCustomImpl(SecurityUtil securityUtil) {
        this.securityUtil = securityUtil;
    }

    @PersistenceContext

    private EntityManager entityManager;

    @Override
    public List<Content> deleteListContent(MultipleParam param) {
        LocalDateTime deleteTime = LocalDateTime.now();
        MemberDetail member = securityUtil.getCurrentUser();

        QContent content = QContent.content1;
        JPAUpdateClause update = new JPAUpdateClause(entityManager, content);
        update.set(content.deleteDate, deleteTime); // Set the deleteDate to the current date
        update.set(content.deleteUser, member.getId());
        update.where(content.recKey.in(param.getIdListLong()));

        update.execute();
    }

    @Override
    public List<Content> deleteALLContent() {
        System.out.println("deleteALLContent");
        return null;
    }
}
