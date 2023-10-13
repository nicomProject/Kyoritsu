package com.enicom.board.kyoritsu.dao.repository;


import com.enicom.board.kyoritsu.dao.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, String> {

    List<Menu> findAll();

    @Override
    void deleteAll();
}
