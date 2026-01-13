package com.work.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("tb_question")
public class Question {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title; // 题目内容
    private String description; // 题目描述（可选）
    private Integer type; // 题目类型：1-单选题，2-多选题，3-判断题，4-填空题，5-简答题
    private Integer difficulty; // 难度：1-简单，2-中等，3-困难
    private Long categoryId; // 分类ID
    private Integer score; // 分值
    private Integer status; // 状态：0-禁用，1-启用
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 非数据库字段，用于前端展示选项
    @TableField(exist = false)
    private List<Option> options;
}