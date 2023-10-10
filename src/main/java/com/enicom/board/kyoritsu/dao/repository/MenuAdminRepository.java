package com.enicom.board.kyoritsu.dao.repository;


import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAdminRepository extends CrudRepository<MenuAdmin, String> {

    List<MenuAdmin> findAll();

    @Override
    void deleteAll();
}
