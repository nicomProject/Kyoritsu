package com.enicom.board.kyoritsu.api.service.job;

import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.NoticeParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import com.enicom.board.kyoritsu.dao.repository.job.JobRepository;
import com.enicom.board.kyoritsu.dao.repository.notice.NoticeRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final SecurityUtil securityUtil;
    private final JobRepository jobRepository;



    @Autowired
    public JobServiceImpl(JobRepository jobRepository, SecurityUtil securityUtil) {
        this.jobRepository = jobRepository;
        this.securityUtil = securityUtil;
    }


    @Override
    public ResponseDataValue<?> add(JobParam param) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MemberDetail member = securityUtil.getCurrentUser();

        Job job = param.create();
        job.setCreateDate(LocalDateTime.now());
        job.setCreateUser(member.getId());
        job.setFromDate(LocalDate.parse(param.getDate_from(), formatter).atStartOfDay());
        job.setToDate(LocalDate.parse(param.getDate_to(), formatter).atTime(23, 59, 59));
        jobRepository.save(job);

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public PageVO<Job> findAll() {
        return PageVO.builder(jobRepository.findAllByDeleteDateNull()).build();
    }

    @Override
    public PageVO<Job> findAll(JobParam param) {
        return PageVO.builder(jobRepository.findAllByRecKey(Long.valueOf(param.getKey()))).build();
    }

    @Override
    public PageVO<Job> findAllCategory(JobParam param) {
        if(param.getCategory().equals("total")){
            return PageVO.builder(jobRepository.findAllByDeleteDateNull()).build();
        }
        else {
            return PageVO.builder(jobRepository.findAllByDeleteDateNullAndCategory(param.getCategory())).build();
        }
    }

    @Transactional
    @Override
    public ResponseDataValue<?> delete(MultipleParam param) {
        MemberDetail member = securityUtil.getCurrentUser();
        MultipleType type = param.getType();
        LocalDateTime deleteTime = LocalDateTime.now();

        if (type.equals(MultipleType.ONE)) {
            Optional<Job> jobOptional = jobRepository.findByRecKey(Long.valueOf(param.getId()));
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setDeleteDate(LocalDateTime.now());
                job.setDeleteUser(member.getId());
            }
        }
        else if (type.equals(MultipleType.LIST)) {
            jobRepository.deleteListContent(param);
        }
        else if(type.equals(MultipleType.SPECIFIC)){
            jobRepository.deleteALLContent();
        }

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public ResponseDataValue<?> update(JobParam param) {

        MemberDetail member = securityUtil.getCurrentUser();
        Optional<Job> jobOptional = jobRepository.findByRecKey(Long.valueOf(param.getKey()));
        if (!jobOptional.isPresent()) {
            return ResponseDataValue.builder(210).desc("잘못된 등록번호입니다.").build();
        }

        Job job = jobOptional.get();
        param.applyTo(job);
        job.setEditUser(member.getId());
        job.setEditDate(LocalDateTime.now());

        jobRepository.save(job);

        return ResponseDataValue.builder(200).build();
    }

    @Override
    public PageVO<Job> findSearch(JobParam param) {
        return PageVO.builder(jobRepository.findByTitleContaining(param.getTitle())).build();
    }

}