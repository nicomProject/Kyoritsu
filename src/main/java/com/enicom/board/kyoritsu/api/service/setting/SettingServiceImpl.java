package com.enicom.board.kyoritsu.api.service.setting;

import com.enicom.board.kyoritsu.api.param.CodeParam;
import com.enicom.board.kyoritsu.api.type.InfoVO;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Code;
import com.enicom.board.kyoritsu.dao.repository.CodeRepository;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final SecurityUtil securityUtil;
    private final CodeRepository codeRepository;

    @Override
    public PageVO<RoleVO> getRoleList() {
        List<Role> roles = securityUtil.getRoleList();
        return PageVO.builder(Arrays.stream(Role.values())
                .filter(roles::contains)
                .map(role -> RoleVO.builder()
                        .key(role.name())
                        .rank(role.getRank())
                        .value(role.getAlias())
                        .build()
                ).collect(Collectors.toList())).build();
    }

    @Override
    public InfoVO<Code> getInitPwd() {
        return InfoVO.builder(securityUtil.getInitPwd()).build();
    }

    @Override
    public ResponseDataValue<?> setInitPwd(CodeParam param) {
        Code code = securityUtil.getInitPwd();

        code.setValue1(param.getValue());
        code.setEditDate(LocalDateTime.now());
        codeRepository.save(code);

        return ResponseDataValue.builder(200).build();
    }
}
