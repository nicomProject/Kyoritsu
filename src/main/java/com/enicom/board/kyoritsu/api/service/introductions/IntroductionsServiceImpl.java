package com.enicom.board.kyoritsu.api.service.introductions;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.admin.Manager;
import com.enicom.board.kyoritsu.dao.repository.IntroductionsRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.Role;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IntroductionsServiceImpl implements IntroductionsService {
    private final SecurityUtil securityUtil;
    private final IntroductionsRepository introductionsRepository;

    @Autowired
    public IntroductionsServiceImpl(IntroductionsRepository introductionsRepository, SecurityUtil securityUtil) {
        this.introductionsRepository = introductionsRepository;
        this.securityUtil = securityUtil;
    }

    @Override
    public PageVO<Content> findAll() {
        return PageVO.builder(introductionsRepository.findAllBy()).build();
    }

    @Override
    public PageVO<Content> findAll(IntroductionsParam param) {
        return PageVO.builder(introductionsRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Override
    public ResponseDataValue<?> add(IntroductionsParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        System.out.println("1111111" + param);

        Content content = param.create();
        System.out.println("222222" + content);
        content.setCreateDate(LocalDateTime.now());
        System.out.println("333333333" + content);
        content.setCreateUser(member.getId());
        System.out.println("4444444" + content);
        introductionsRepository.save(content);

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> update(IntroductionsParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        Optional<Content> contentOptional = introductionsRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!contentOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Content content = contentOptional.get();
        param.applyTo(content);
        content.setEditUser(member.getId());
        content.setEditDate(LocalDateTime.now());

        introductionsRepository.save(content);

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {
        MultipleType type = param.getType();

        System.out.println("param" + param);
        System.out.println("type" + type);
        System.out.println("paramId" + param.getId());

        if (type.equals(MultipleType.ONE)) {
            System.out.println("11111111111111111111111111");
            introductionsRepository.deleteByRecKey(Long.valueOf(param.getId()));
            System.out.println("22222222222222222222222222");
        }
        else if (type.equals(MultipleType.LIST)) {
            introductionsRepository.deleteByRecKeyIn(param.getIdListLong());
        }
//        else if (type.equals(MultipleType.SPECIFIC)) {
//            introductionsRepository.deleteAllByRecKey(param.getIdList());
//        }

        return ResponseDataValue.builder(200).build();
    }
}