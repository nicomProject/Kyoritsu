package com.enicom.board.kyoritsu.dao.repository;


import com.enicom.board.kyoritsu.dao.entity.Menu;
import com.enicom.board.kyoritsu.dao.entity.admin.MenuAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAdminRepository extends CrudRepository<MenuAdmin, String> {
    //@Override
    //List<Menu> findAll();

    //Optional<Menu> findByCode(String code);

    //List<Menu> findAllByReadRoleInOrderByOrderAsc(List<Role> managerRole);

    // List<Menu> findAllByReadRoleInOrderByOrderAsc(List<Role> readRoles);

    List<MenuAdmin> findAll();

    //Optional<Menu> findByCodeAndEditRoleIn(String code, List<Role> managerRole);

    //Optional<Menu> findByCodeAndReadRoleIn(String code, List<Role> managerRole);

    @Override
    void deleteAll();
}
