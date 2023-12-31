package com.enicom.board.kyoritsu.api.service.manager;

import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerInfoParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerPasswordParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;

public interface ManagerService {
    PageVO<Manager> findAll();

    PageVO<Manager> findAll(ManagerInfoParam param);

    ResponseDataValue<?> add(ManagerInfoParam param);

    ResponseDataValue<?> modify(ManagerInfoParam param);

    ResponseDataValue<?> delete(MultipleParam param);

    ResponseDataValue<?> init(MultipleParam param);

    ResponseDataValue<?> changePassword(ManagerPasswordParam param);

}
