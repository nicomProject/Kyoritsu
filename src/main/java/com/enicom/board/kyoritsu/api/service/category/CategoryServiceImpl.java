package com.enicom.board.kyoritsu.api.service.category;

import com.enicom.board.kyoritsu.api.param.CategoryParam;
import com.enicom.board.kyoritsu.api.param.JobParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleParam;
import com.enicom.board.kyoritsu.api.param.type.MultipleType;
import com.enicom.board.kyoritsu.api.type.PageVO;
import com.enicom.board.kyoritsu.api.type.ResponseDataValue;
import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.admin.Category;
import com.enicom.board.kyoritsu.dao.repository.category.CategoryRepository;
import com.enicom.board.kyoritsu.dao.repository.job.JobRepository;
import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final SecurityUtil securityUtil;
    private final CategoryRepository categoryRepository;



    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, SecurityUtil securityUtil) {
        this.categoryRepository = categoryRepository;
        this.securityUtil = securityUtil;
    }
    @Override
    public ResponseDataValue<?> add(CategoryParam param) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MemberDetail member = securityUtil.getCurrentUser();

        Category category = param.create();
        category.setCategoryName(param.getCategoryName());
        category.setCreateDate(LocalDateTime.now());
        category.setCreateUser(member.getId());
        categoryRepository.save(category);

        return ResponseDataValue.builder(200).build();
    }
    @Override
    public PageVO<Category> findAll() {
        return PageVO.builder(categoryRepository.findAllByDeleteDateNull()).build();
    }
    @Transactional
    @Override
    public ResponseDataValue<?> delete(CategoryParam param) {
        MemberDetail member = securityUtil.getCurrentUser();

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(param.getCategoryName());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setDeleteDate(LocalDateTime.now());
            category.setDeleteUser(member.getId());
        }

        return ResponseDataValue.builder(200).build();
    }


}