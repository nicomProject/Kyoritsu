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
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class IntroductionsServiceImpl implements IntroductionsService {
    private final SecurityUtil securityUtil;
    private final IntroductionsRepository introductionsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public IntroductionsServiceImpl(IntroductionsRepository introductionsRepository, SecurityUtil securityUtil) {
        this.introductionsRepository = introductionsRepository;
        this.securityUtil = securityUtil;
    }

    @Override
    public PageVO<Content> findAll() {
        System.out.println(introductionsRepository.findAllByDeleteDateNull() + "introductionsRepository.findAllByCreateDateNotNull()");
        return PageVO.builder(introductionsRepository.findAllByDeleteDateNull()).build();
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
        MemberDetail member = securityUtil.getCurrentUser();
        MultipleType type = param.getType();
        LocalDateTime deleteTime = LocalDateTime.now();

        if (type.equals(MultipleType.ONE)) {
            Optional<Content> contentOptional = introductionsRepository.findByRecKey(Long.valueOf(param.getId()));
            if (contentOptional.isPresent()) {
                Content content = contentOptional.get();
                content.setDeleteDate(LocalDateTime.now());
                content.setCreateUser(member.getId());
            }
        }
        else if (type.equals(MultipleType.LIST)) {
            introductionsRepository.deleteListContent(param.getIdListLong(), deleteTime, member.getId());
        }
        else if(type.equals(MultipleType.SPECIFIC)){
            introductionsRepository.deleteALLContent(deleteTime, member.getId());
        }

        return ResponseDataValue.builder(200).build();
    }
}