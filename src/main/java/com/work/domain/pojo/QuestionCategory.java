package com.work.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_question_category")
public class QuestionCategory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name; // 分类名称
    private String description; // 分类描述
    private Integer status; // 状态：0-禁用，1-启用
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    @TableField(exist = false) // 非数据库字段，用于存储该分类下的题目数量
    private Integer questionCount;
}