package com.work.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_option")
public class Option {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long questionId; // 所属题目ID
    private String content; // 选项内容
    private String code; // 选项编码（如A、B、C、D）
    private Integer isCorrect; // 是否正确答案：0-错误，1-正确
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}