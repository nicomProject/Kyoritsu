package com.enicom.board.kyoritsu.dao.repository.admin;


import com.enicom.board.kyoritsu.dao.entity.admin.MenuGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuGroupRepository extends CrudRepository<MenuGroup, String> {
    @Override
    List<MenuGroup> findAll();

    @Override
    void deleteAll();
}
