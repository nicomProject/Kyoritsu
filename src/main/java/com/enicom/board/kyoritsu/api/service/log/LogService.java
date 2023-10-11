package com.enicom.board.kyoritsu.api.service.log;

import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.dao.entity.admin.AccessLog;

public interface LogService {
    PageVO<AccessLog> getAccessLogList();
}
