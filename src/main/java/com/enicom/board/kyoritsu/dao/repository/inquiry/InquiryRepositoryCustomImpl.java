package com.enicom.board.kyoritsu.dao.repository.inquiry;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.QInquiry;
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
        QInquiry qInquiry = QInquiry.inquiry;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qInquiry)
                .set(qInquiry.deleteDate, LocalDateTime.now())
                .set(qInquiry.deleteUser, member.getId())
                .where(qInquiry.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        QInquiry qInquiry = QInquiry.inquiry;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qInquiry)
                .set(qInquiry.deleteDate, LocalDateTime.now())
                .set(qInquiry.deleteUser, member.getId())
                .execute();
    }
}
