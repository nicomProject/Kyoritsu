package com.enicom.board.kyoritsu.dao.repository.introduction;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.enicom.board.kyoritsu.dao.entity.QMainMenu;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class IntroductionRepositoryCustomImpl implements IntroductionRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;

    @Override
    public Long deleteListContent(MultipleParam param) {
        QContent qContent = QContent.content1;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qContent)
                .set(qContent.deleteDate, LocalDateTime.now())
                .set(qContent.deleteUser, member.getId())
                .where(qContent.recKey.in(param.getIdListLong())).execute();
    }

    @Override
    public Long deleteALLContent() {
        System.out.println("deleteALLContent");
        QContent qContent = QContent.content1;
        MemberDetail member = securityUtil.getCurrentUser();

        return factory.update(qContent)
                .set(qContent.deleteDate, LocalDateTime.now())
                .set(qContent.deleteUser, member.getId())
                .execute();
    }

//    @Override
//    public List<Content> findAllByDeleteDateNull() {
//        QContent qContent = QContent.content1;
//        QMainMenu qMainMenu = new QMainMenu("qMainMenuCategory");
//        QMainMenu qMainMenuSub = new QMainMenu("qMainMenuSubcategory");
//
//        List<Tuple> result = factory
//                .select(
//                        qContent.recKey,
//                        qContent.title,
//                        qContent.subtitle,
//                        qMainMenu.name,
//                        qMainMenuSub.name,
//                        qContent.content,
//                        qContent.hit,
//                        qContent.createUser,
//                        qContent.editUser,
//                        qContent.deleteUser,
//                        qContent.createDate,
//                        qContent.editDate,
//                        qContent.deleteDate
//                )
//                .from(qContent)
//                .leftJoin(qMainMenu).on(qContent.category.eq(qMainMenu.recKey.stringValue()))
//                .leftJoin(qMainMenuSub).on(qContent.subcategory.eq(qMainMenuSub.recKey.stringValue()))
//                .where(qContent.deleteDate.isNull())
//                .fetch();
//
//        List<Content> contentList = result.stream()
//                .map(tuple -> {
//                    Content content = new Content();
//                    content.setRecKey(tuple.get(qContent.recKey));
//                    content.setTitle(tuple.get(qContent.title));
//                    content.setSubtitle(tuple.get(qContent.subtitle));
//                    content.setCategory(tuple.get(qMainMenu.name));
//                    content.setSubcategory(tuple.get(qMainMenuSub.name));
//                    content.setContent(tuple.get(qContent.content));
//                    content.setHit(tuple.get(qContent.hit));
//                    content.setCreateUser(tuple.get(qContent.createUser));
//                    content.setEditUser(tuple.get(qContent.editUser));
//                    content.setDeleteUser(tuple.get(qContent.deleteUser));
//                    content.setCreateDate(tuple.get(qContent.createDate));
//                    content.setEditDate(tuple.get(qContent.editDate));
//                    content.setDeleteDate(tuple.get(qContent.deleteDate));
//
//                    return content;}).collect(Collectors.toList());
//
//        return contentList;
//    }
}
