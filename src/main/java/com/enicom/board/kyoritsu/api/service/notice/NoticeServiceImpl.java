package com.enicom.board.kyoritsu.api.service.notice;

import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.InfoVO;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import com.enicom.board.kyoritsu.dao.repository.notice.NoticeRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final SecurityUtil securityUtil;
    private final NoticeRepository noticeRepository;



    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, SecurityUtil securityUtil) {
        this.noticeRepository = noticeRepository;
        this.securityUtil = securityUtil;
    }


    @Override
    public ResponseDataValue<?> add(NoticeParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        System.out.println("1111111" + param);

        Notice notice = param.create();
        System.out.println("222222" + notice);
        notice.setCreateDate(LocalDateTime.now());
        System.out.println("333333333" + notice);
        notice.setCreateUser(member.getId());
        System.out.println("4444444" + notice);
        noticeRepository.save(notice);

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public PageVO<Notice> findAll() {
        System.out.println(noticeRepository.findAllByDeleteDateNullOrderByCreateDateDesc() + "introductionsRepository.findAllByCreateDateNotNull()");
        return PageVO.builder(noticeRepository.findAllByDeleteDateNullOrderByCreateDateDesc()).build();
    }

    @Override
    public PageVO<Notice> findAll(NoticeParam param) {
        return PageVO.builder(noticeRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Override
    public InfoVO<Notice> findBy(Long recKey) {
        Optional<Notice> noticeOptional = noticeRepository.findByRecKey(recKey);
        if(noticeOptional.isPresent()){
            Notice notice = noticeOptional.get();
            notice.setHit(notice.getHit()+1);
            noticeRepository.save(notice);

            return InfoVO.builder(notice).build();
        }

        return InfoVO.builder(Notice.builder().build()).build();
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {
        MemberDetail member = securityUtil.getCurrentUser();
        MultipleType type = param.getType();
        LocalDateTime deleteTime = LocalDateTime.now();

        if (type.equals(MultipleType.ONE)) {
            Optional<Notice> noticeOptional = noticeRepository.findByRecKey(Long.valueOf(param.getId()));
            if (noticeOptional.isPresent()) {
                Notice notice = noticeOptional.get();
                notice.setDeleteDate(LocalDateTime.now());
                notice.setDeleteUser(member.getId());
            }
        }
        else if (type.equals(MultipleType.LIST)) {
            noticeRepository.deleteListContent(param);
        }
        else if(type.equals(MultipleType.SPECIFIC)){
            noticeRepository.deleteALLContent();
        }

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> update(NoticeParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        Optional<Notice> noticeOptional = noticeRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!noticeOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Notice notice = noticeOptional.get();
        param.applyTo(notice);
        notice.setEditUser(member.getId());
        notice.setEditDate(LocalDateTime.now());

        noticeRepository.save(notice);

        return ResponseDataValue.builder(200).build();
    }

}