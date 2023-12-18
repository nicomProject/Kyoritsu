package com.enicom.board.kyoritsu.dao.repository.category;

import com.enicom.board.kyoritsu.api.param.CategoryParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.Category;
//import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;

//    @Override
//    public List<Category> findeDetail(CategoryParam param) {
//        return null;
//    }

//    @Override
//    public Long findeDetail(Category param) {
//        QContent qContent = QContent.content1;
//        MemberDetail member = securityUtil.getCurrentUser();
//
////        return factory.update(qContent)
////                .set(qContent.deleteDate, LocalDateTime.now())
////                .set(qContent.deleteUser, member.getId())
////                .where(qContent.recKey.in(param.getIdListLong())).execute();
//    }

//    @Override
//    public Long deleteALLContent() {
//        System.out.println("deleteALLContent");
//        QContent qContent = QContent.content1;
//        MemberDetail member = securityUtil.getCurrentUser();
//
//        return factory.update(qContent)
//                .set(qContent.deleteDate, LocalDateTime.now())
//                .set(qContent.deleteUser, member.getId())
//                .execute();
//    }

}
