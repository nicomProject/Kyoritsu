package com.enicom.board.kyoritsu.dao.repository;


import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.entity.admin.Menu;
import com.enicom.board.kyoritsu.dao.type.MenuType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainMenuRepository extends CrudRepository<MainMenu, String> {
    List<MainMenu> findAllByOrderByRecKey();
    List<MainMenu> findAllByType(MenuType type);

    Optional<MainMenu> findByRecKey(Long recKey);

    @Override
    void deleteAll();
}
