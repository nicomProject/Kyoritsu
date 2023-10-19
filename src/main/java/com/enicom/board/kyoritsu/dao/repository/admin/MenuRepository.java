package com.enicom.board.kyoritsu.dao.repository.admin;


import com.enicom.board.kyoritsu.dao.entity.admin.Menu;
import com.enicom.board.kyoritsu.dao.type.admin.MenuPageType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<Menu, String> {

    List<Menu> findAllByOrderByOrderSeqAsc();

    Optional<Menu> findByCode(MenuPageType code);

    Optional<Menu> findByCodeDetail(String code);

    @Override
    void deleteAll();
}
