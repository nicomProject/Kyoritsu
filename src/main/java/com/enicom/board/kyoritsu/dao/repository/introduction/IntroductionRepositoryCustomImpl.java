package com.enicom.board.kyoritsu.dao.repository.introduction;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class IntroductionRepositoryCustomImpl implements IntroductionRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Long deleteListContent(MultipleParam param) {
        QContent qContent = QContent.content1;

        // select
        List<Content> contents = factory.select(qContent).from(qContent).fetch();

        return factory.update(qContent)
                .set(qContent.deleteDate, LocalDateTime.now())
                .where(qContent.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        System.out.println("deleteALLContent");
        return null;
    }
}
