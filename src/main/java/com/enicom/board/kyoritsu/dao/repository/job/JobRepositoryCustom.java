package com.enicom.board.kyoritsu.dao.repository.job;

import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.dao.entity.Job;

import java.util.List;

public interface JobRepositoryCustom {

    Long deleteListContent(MultipleParam param);

    Long deleteALLContent();
}
