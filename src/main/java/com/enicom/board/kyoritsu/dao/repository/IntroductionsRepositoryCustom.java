package com.enicom.board.kyoritsu.dao.repository;

import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Content;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IntroductionsRepositoryCustom {

    List<Content> deleteListContent(MultipleParam param);

    List<Content> deleteALLContent();

}
