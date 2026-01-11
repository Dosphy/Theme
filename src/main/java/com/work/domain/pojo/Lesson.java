package com.work.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Dosphy
 * @Date 2026/1/7 23:42
 */
@Data
@TableName("lesson")
public class Lesson {
    private int id;
    private String subject;
    private String teacher;
    private String portrait;
}
