package com.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.domain.pojo.QuestionCategory;
import com.work.mapper.QuestionCategoryMapper;
import com.work.service.QuestionCategoryService;
import org.springframework.stereotype.Service;

@Service
public class QuestionCategoryServiceImpl extends ServiceImpl<QuestionCategoryMapper, QuestionCategory> implements QuestionCategoryService {
}