package com.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.domain.pojo.Option;

import java.util.List;

public interface OptionService extends IService<Option> {
    List<Option> getOptionsByQuestionId(Long questionId);
}