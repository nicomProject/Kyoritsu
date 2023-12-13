package com.enicom.board.kyoritsu.api.service.manager;

import com.enicom.board.kyoritsu.api.param.manager.ManagerInfoParam;
import com.enicom.board.kyoritsu.api.param.manager.ManagerPasswordParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.repository.manager.ManagerRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final SecurityUtil securityUtil;
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(SecurityUtil securityUtil, ManagerRepository managerRepository) {
        this.securityUtil = securityUtil;
        this.managerRepository = managerRepository;
    }

    @Override
    public PageVO<Manager> findAll() {
        return PageVO.builder(managerRepository.findAllByDeleteDateNull()).build();
    }

    @Override
    public PageVO<Manager> findAll(ManagerInfoParam param) {
        return PageVO.builder(managerRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Override
    public ResponseDataValue<?> add(ManagerInfoParam param) {
        Optional<Manager> managerOp = managerRepository.findByUserId(param.getId());
        MemberDetail member = securityUtil.getCurrentUser();
        if (managerOp.isPresent()) {
            return ResponseDataValue.builder(400).build();
        } else {
            Manager manager = Manager.builder()
                    .userId(param.getId())
                    .name(param.getName())
                    .role(param.getRole())
                    .enable(param.getEnable())
                    .createDate(LocalDateTime.now())
                    .createUser(member.getId())
                    .password(securityUtil.getEncodedInitPwd()).build();

            managerRepository.save(manager);
        }
        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> modify(ManagerInfoParam param) {
        Optional<Manager> managerOp = managerRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!managerOp.isPresent()) {
            return ResponseDataValue.builder(210).build();
        } else {
            Manager manager = managerOp.get();
            manager.setName(param.getName());
            manager.setRole(param.getRole());
            manager.setEnable(param.getEnable());
            manager.setEditDate(LocalDateTime.now());

            managerRepository.save(manager);
        }
        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {
        MultipleType type = param.getType();

        if (type.equals(MultipleType.ONE)) {
            managerRepository.deleteById(Long.valueOf(param.getKey()));
        }
        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> init(MultipleParam param) {
        String currentId = securityUtil.getCurrentUser().getUsername();
        List<Manager> managers = managerRepository.findAllByUserIdIn(param.getIdList());
        managers.forEach(manager -> {
            if (manager.getUserId().equals(currentId)) {
                managers.remove(manager);
            } else {
                manager.setPassword(securityUtil.getEncodedInitPwd());
                manager.setEnable(1);
                manager.setFailureCnt(0);
                manager.setEditDate(LocalDateTime.now());
            }
        });
        managerRepository.saveAll(managers);

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> changePassword(ManagerPasswordParam param) {
        MemberDetail current = securityUtil.getCurrentUser();
        if (current == null) {
            return ResponseDataValue.builder(421).build();
        }

        Manager manager = managerRepository.findByRecKey(Long.valueOf(param.getKey())).get();

        String password = param.getPassword();
        String newPassword = param.getNewPassword();
        String newPasswordConfirm = param.getNewPasswordConfirm();

        if (!securityUtil.match(param.getPassword(), manager.getPassword())) {
            return ResponseDataValue.builder(400).desc("비밀번호가 일치하지 않습니다.").build();
        } else if (password.equals(newPassword)) {
            return ResponseDataValue.builder(400).desc("기존과 동일한 비밀번호 입니다.").build();
        } else if (!newPassword.equals(newPasswordConfirm)) {
            return ResponseDataValue.builder(400).desc("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.").build();
        }

        manager.setPassword(securityUtil.encode(newPassword));
        manager.setEditDate(LocalDateTime.now());
        managerRepository.save(manager);

        return ResponseDataValue.builder(200).desc("비밀번호가 성공적으로 수정되었습니다!").build();
    }
}
