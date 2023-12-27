package com.enicom.board.kyoritsu.dto;

import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.entity.QContent;
import com.enicom.board.kyoritsu.dao.entity.QMainMenu;
import com.enicom.board.kyoritsu.dao.type.MenuTarget;
import com.enicom.board.kyoritsu.dao.type.MenuType;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MainMenuRepositoryCustomImpl implements MainMenuRepositoryCustom {
    private final JPAQueryFactory factory;
    private final SecurityUtil securityUtil;
    private final EntityManager entityManager;

    @Override
    public List<MainMenu> findAllName() {
        QMainMenu qMainMenu = QMainMenu.mainMenu;

        List<MainMenu> menuList = factory  // EntityManager 사용
                .select(
                        qMainMenu.recKey,
                        qMainMenu.createDate,
                        qMainMenu.createUser,
                        qMainMenu.deleteDate,
                        qMainMenu.deleteUser,
                        qMainMenu.editDate,
                        qMainMenu.editUser,
                        qMainMenu.order,
                        qMainMenu.target,
                        qMainMenu.type,
                        qMainMenu.url,
                        qMainMenu.use,
                        qMainMenu.name,
                        qMainMenu.menu.recKey // menu 필드의 recKey 값
//                        qMainMenu.menu
                )
                .from(qMainMenu)
                .where(qMainMenu.deleteDate.isNull())
                .fetch()
                .stream()
                .map(tuple -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setRecKey(tuple.get(qMainMenu.recKey));
                    mainMenu.setCreateDate(tuple.get(qMainMenu.createDate));
                    mainMenu.setCreateUser(tuple.get(qMainMenu.createUser));
                    mainMenu.setDeleteDate(tuple.get(qMainMenu.deleteDate));
                    mainMenu.setDeleteUser(tuple.get(qMainMenu.deleteUser));
                    mainMenu.setEditDate(tuple.get(qMainMenu.editDate));
                    mainMenu.setEditUser(tuple.get(qMainMenu.editUser));
                    mainMenu.setOrder(tuple.get(qMainMenu.order));
                    mainMenu.setTarget(tuple.get(qMainMenu.target));
                    mainMenu.setType(tuple.get(qMainMenu.type));
                    mainMenu.setUrl(tuple.get(qMainMenu.url));
                    mainMenu.setUse(tuple.get(qMainMenu.use));
                    mainMenu.setName(tuple.get(qMainMenu.name));
                    MainMenu menu = new MainMenu();
                    menu.setRecKey(tuple.get(qMainMenu.menu.recKey));
                    mainMenu.setMenu(menu);
                    return mainMenu;
                })
                .collect(Collectors.toList());

        return menuList;
    }

    @Override
    public List<MainMenu> findAllNameEnglish() {

        QMainMenu qMainMenu = QMainMenu.mainMenu;

        List<MainMenu> menuList = factory  // EntityManager 사용
                .select(
                        qMainMenu.recKey,
                        qMainMenu.createDate,
                        qMainMenu.createUser,
                        qMainMenu.deleteDate,
                        qMainMenu.deleteUser,
                        qMainMenu.editDate,
                        qMainMenu.editUser,
                        qMainMenu.order,
                        qMainMenu.target,
                        qMainMenu.type,
                        qMainMenu.url,
                        qMainMenu.use,
                        qMainMenu.nameEnglish,
                        qMainMenu.menu.recKey // menu 필드의 recKey 값
//                        qMainMenu.menu
                )
                .from(qMainMenu)
                .where(qMainMenu.deleteDate.isNull())
                .fetch()
                .stream()
                .map(tuple -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setRecKey(tuple.get(qMainMenu.recKey));
                    mainMenu.setCreateDate(tuple.get(qMainMenu.createDate));
                    mainMenu.setCreateUser(tuple.get(qMainMenu.createUser));
                    mainMenu.setDeleteDate(tuple.get(qMainMenu.deleteDate));
                    mainMenu.setDeleteUser(tuple.get(qMainMenu.deleteUser));
                    mainMenu.setEditDate(tuple.get(qMainMenu.editDate));
                    mainMenu.setEditUser(tuple.get(qMainMenu.editUser));
                    mainMenu.setOrder(tuple.get(qMainMenu.order));
                    mainMenu.setTarget(tuple.get(qMainMenu.target));
                    mainMenu.setType(tuple.get(qMainMenu.type));
                    mainMenu.setUrl(tuple.get(qMainMenu.url));
                    mainMenu.setUse(tuple.get(qMainMenu.use));
                    mainMenu.setNameEnglish(tuple.get(qMainMenu.nameEnglish));
                    MainMenu menu = new MainMenu();
                    menu.setRecKey(tuple.get(qMainMenu.menu.recKey));
                    mainMenu.setMenu(menu);
                    return mainMenu;
                })
                .collect(Collectors.toList());

        return menuList;
    }

    @Override
    public List<MainMenu> findAllnameJapanese() {
        QMainMenu qMainMenu = QMainMenu.mainMenu;

        List<MainMenu> menuList = factory  // EntityManager 사용
                .select(
                        qMainMenu.recKey,
                        qMainMenu.createDate,
                        qMainMenu.createUser,
                        qMainMenu.deleteDate,
                        qMainMenu.deleteUser,
                        qMainMenu.editDate,
                        qMainMenu.editUser,
                        qMainMenu.order,
                        qMainMenu.target,
                        qMainMenu.type,
                        qMainMenu.url,
                        qMainMenu.use,
                        qMainMenu.nameJapanese,
                        qMainMenu.menu.recKey // menu 필드의 recKey 값
//                        qMainMenu.menu
                )
                .from(qMainMenu)
                .where(qMainMenu.deleteDate.isNull())
                .fetch()
                .stream()
                .map(tuple -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setRecKey(tuple.get(qMainMenu.recKey));
                    mainMenu.setCreateDate(tuple.get(qMainMenu.createDate));
                    mainMenu.setCreateUser(tuple.get(qMainMenu.createUser));
                    mainMenu.setDeleteDate(tuple.get(qMainMenu.deleteDate));
                    mainMenu.setDeleteUser(tuple.get(qMainMenu.deleteUser));
                    mainMenu.setEditDate(tuple.get(qMainMenu.editDate));
                    mainMenu.setEditUser(tuple.get(qMainMenu.editUser));
                    mainMenu.setOrder(tuple.get(qMainMenu.order));
                    mainMenu.setTarget(tuple.get(qMainMenu.target));
                    mainMenu.setType(tuple.get(qMainMenu.type));
                    mainMenu.setUrl(tuple.get(qMainMenu.url));
                    mainMenu.setUse(tuple.get(qMainMenu.use));
                    mainMenu.setNameJapanese(tuple.get(qMainMenu.nameJapanese));
                    MainMenu menu = new MainMenu();
                    menu.setRecKey(tuple.get(qMainMenu.menu.recKey));
                    mainMenu.setMenu(menu);
                    return mainMenu;
                })
                .collect(Collectors.toList());

        return menuList;
    }
}


