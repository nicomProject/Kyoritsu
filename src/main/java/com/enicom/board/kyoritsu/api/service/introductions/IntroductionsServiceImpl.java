package com.enicom.board.kyoritsu.api.service.introductions;

import com.enicom.board.kyoritsu.api.param.IntroductionsParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Content;
import com.enicom.board.kyoritsu.dao.entity.MainMenu;
import com.enicom.board.kyoritsu.dao.repository.MainMenuRepository;
import com.enicom.board.kyoritsu.dao.repository.introduction.IntroductionRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IntroductionsServiceImpl implements IntroductionsService {
    private final SecurityUtil securityUtil;
    private final IntroductionRepository introductionsRepository;
    private final MainMenuRepository mainMenuRepository;



    @Autowired
    public IntroductionsServiceImpl(IntroductionRepository introductionsRepository, SecurityUtil securityUtil, MainMenuRepository mainMenuRepository) {
        this.introductionsRepository = introductionsRepository;
        this.securityUtil = securityUtil;
        this.mainMenuRepository = mainMenuRepository;
    }

//    @Override
//    public PageVO<Content> findAll() {
//        return PageVO.builder(introductionsRepository.findAllByDeleteDateNull()).build();
//    }

    @Transactional
    @Override
    public PageVO<Content> findAll() {
        return PageVO.builder(introductionsRepository.findAllByDeleteDateNull()).build();
    }

    @Transactional
    @Override
    public PageVO<Content> findAll(IntroductionsParam param) {
        return PageVO.builder(introductionsRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> add(IntroductionsParam param) {

        MemberDetail member = securityUtil.getCurrentUser();

        Content content = param.create();
        content.setCreateDate(LocalDateTime.now());
        content.setCreateUser(member.getId());
        introductionsRepository.save(content);

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
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
            introductionsRepository.deleteListContent(param);
        }
        else if(type.equals(MultipleType.SPECIFIC)){
            introductionsRepository.deleteALLContent();
        }

        return ResponseDataValue.builder(200).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> check(MultipleParam param) {

        Optional<Content> contentOptional = introductionsRepository.findByRecKey(param.getIdListLong().get(0));
        if (!contentOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }
        Optional<MainMenu> mainMenuOptional = mainMenuRepository.findByRecKey(Long.valueOf(contentOptional.get().getSubcategory()));
        if (!mainMenuOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }
        else {
            MainMenu mainMenu = mainMenuOptional.get();
            mainMenu.setContent(contentOptional.get());

            mainMenuRepository.save(mainMenu);
        }
        return ResponseDataValue.builder(200).build();
    }
}