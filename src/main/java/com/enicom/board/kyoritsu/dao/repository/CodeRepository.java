package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.dao.entity.Code;
import com.enicom.board.kyoritsu.dao.id.CodeId;
import com.enicom.board.kyoritsu.dao.type.CodeGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, CodeId> {
    Optional<Code> findByGroupAndCode(CodeGroup group, String code);
}
