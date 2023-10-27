package com.enicom.board.kyoritsu.dao.repository.notice;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.QNotice;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;

    @Override
    public Long deleteListContent(MultipleParam param) {
        QNotice qNotice = QNotice.notice;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qNotice)
                .set(qNotice.deleteDate, LocalDateTime.now())
                .set(qNotice.deleteUser, member.getId())
                .where(qNotice.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        QNotice qNotice = QNotice.notice;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qNotice)
                .set(qNotice.deleteDate, LocalDateTime.now())
                .set(qNotice.deleteUser, member.getId())
                .execute();
    }


}
