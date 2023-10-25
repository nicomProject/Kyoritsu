package com.enicom.board.kyoritsu.dao.repository.image;

import com.enicom.board.kyoritsu.dao.entity.Image;
import com.enicom.board.kyoritsu.dao.entity.admin.AccessLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByKey(String key);

}
