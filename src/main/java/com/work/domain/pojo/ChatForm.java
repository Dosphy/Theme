package com.work.domain.pojo;

import lombok.Data;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:38
 */
@Data
public class ChatForm {
    //TODO添加userid作为区分
    private String memoryId;
    private String userMessage;
}