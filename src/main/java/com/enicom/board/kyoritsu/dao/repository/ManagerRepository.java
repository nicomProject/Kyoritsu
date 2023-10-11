package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.login.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
    Optional<Manager> findByUserId(String userId);

    List<Manager> findAllByRoleIn(List<Role> roles);

    List<Manager> findAllByUserIdIn(List<String> ids);

    void deleteByUserId(String userId);

    void deleteAllByUserIdIn(List<String> ids);
}
