package com.enicom.board.kyoritsu.api.service.job;

import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.Notice;

public interface JobService {


    ResponseDataValue<?> add(JobParam param);

    PageVO<Job> findAll();

    PageVO<Job> findAllCategory(JobParam param);

    PageVO<Job> findAll(JobParam param);

    ResponseDataValue<?> delete(MultipleParam param);

    ResponseDataValue<?> update(JobParam param);
}
