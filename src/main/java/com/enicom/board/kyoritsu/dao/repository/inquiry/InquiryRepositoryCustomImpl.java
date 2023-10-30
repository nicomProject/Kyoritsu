package com.enicom.board.kyoritsu.dao.repository.inquiry;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.QJob;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class InquiryRepositoryCustomImpl implements InquiryRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;

    @Override
    public Long deleteListContent(MultipleParam param) {
        QJob qJob = QJob.job;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qJob)
                .set(qJob.deleteDate, LocalDateTime.now())
                .set(qJob.deleteUser, member.getId())
                .where(qJob.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        QJob qJob = QJob.job;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qJob)
                .set(qJob.deleteDate, LocalDateTime.now())
                .set(qJob.deleteUser, member.getId())
                .execute();
    }


}
