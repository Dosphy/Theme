package com.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.domain.pojo.Option;
import com.work.mapper.OptionMapper;
import com.work.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {
    @Override
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return this.lambdaQuery()
                .eq(Option::getQuestionId, questionId)
                .list();
    }
}