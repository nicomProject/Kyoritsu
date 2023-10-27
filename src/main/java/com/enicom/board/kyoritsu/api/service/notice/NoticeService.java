package com.enicom.board.kyoritsu.api.service.notice;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.InfoVO;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.Notice;

public interface NoticeService {


    ResponseDataValue<?> add(NoticeParam param);

    PageVO<Notice> findAll();

    PageVO<Notice> findAll(NoticeParam param);

    InfoVO<Notice> findBy(Long recKey);

    ResponseDataValue<?> delete(MultipleParam param);

    ResponseDataValue<?> update(NoticeParam param);

}
