package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.login.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IntroductionsRepository extends CrudRepository<Manager, Long> {



}
