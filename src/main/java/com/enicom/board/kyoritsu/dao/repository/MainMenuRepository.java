package com.enicom.board.kyoritsu.dao.repository;


import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainMenuRepository extends CrudRepository<MainMenu, String> {
    List<MainMenu> findAll();

    @Override
    void deleteAll();
}
