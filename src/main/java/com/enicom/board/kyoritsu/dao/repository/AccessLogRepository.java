package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.admin.AccessLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends CrudRepository<AccessLog, Long> {
    @Override
    List<AccessLog> findAll();
}
