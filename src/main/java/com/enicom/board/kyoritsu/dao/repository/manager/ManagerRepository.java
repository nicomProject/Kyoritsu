package com.enicom.board.kyoritsu.dao.repository.manager;

import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.repository.job.JobRepositoryCustom;
import com.enicom.board.kyoritsu.login.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends CrudRepository<Manager, Long>{
    Optional<Manager> findByUserId(String userId);

    List<Manager> findAllByRoleIn(List<Role> roles);

    List<Manager> findAllByDeleteDateNull();

    List<Manager> findAllByUserIdIn(List<String> ids);

    List<Manager> findAllByRecKey(Long recKey);

    Optional<Manager> findByRecKey(Long recKey);

    void deleteByRecKeyIn(List<Long> recKey);

    void deleteByUserId(String userId);

    void deleteAllByUserIdIn(List<String> ids);
}
