package com.enicom.board.kyoritsu.api.service.introductions;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerInfoParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerPasswordParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.service.manager.ManagerService;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.repository.ManagerRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IntroductionsServiceImpl implements IntroductionsService {

    @Override
    public ResponseDataValue<?> add(IntroductionsParam param) {
        return null;
    }
}