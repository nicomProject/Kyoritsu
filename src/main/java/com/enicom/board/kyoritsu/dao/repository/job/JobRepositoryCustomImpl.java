package com.enicom.board.kyoritsu.dao.repository.job;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.QJob;
import com.enicom.board.kyoritsu.dao.entity.QNotice;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class JobRepositoryCustomImpl implements JobRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;

    @Override
    public Long deleteListContent(MultipleParam param) {
        System.out.println("paramvalue" + param);
        System.out.println("deleteListContent123123");
        QJob qJob = QJob.job;
        MemberDetail member = securityUtil.getCurrentUser();
        // select
        // List<Content> contents = factory.select(qContent).from(qContent).fetch();

        return factory.update(qJob)
                .set(qJob.deleteDate, LocalDateTime.now())
                .set(qJob.deleteUser, member.getId())
                .where(qJob.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        System.out.println("deleteALLContent");
        QJob qJob = QJob.job;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qJob)
                .set(qJob.deleteDate, LocalDateTime.now())
                .set(qJob.deleteUser, member.getId())
                .execute();
    }


}
