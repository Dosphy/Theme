package com.work.controller;

import com.work.assistant.XiaoBaoAgent;
import com.work.domain.pojo.ChatForm;
import com.work.domain.pojo.User;
import com.work.utils.UserContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:13
 */
@RestController
@Tag(name = "小包AI助手")
@RequestMapping("/api/xiaobao")
public class XiaoBaoController {
    @Autowired
    private XiaoBaoAgent xiaoBaoAgent;

    @Operation(summary = "对话功能")
    @PostMapping(value = "/chat",produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        // 组合userId和memoryId，格式: userId:memoryId
        Long userId = UserContextHolder.getCurrentUserId();
        String combinedMemoryId = userId + ":" + chatForm.getMemoryId();
        return xiaoBaoAgent.chat(combinedMemoryId, chatForm.getUserMessage());
    }

}
