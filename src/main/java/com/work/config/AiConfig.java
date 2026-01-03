package com.work.config;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Dosphy
 * @Date 2026/1/3 14:25
 */
@Configuration
public class AiConfig {
    @Value("${langchain4j.community.dashscope.chat-model.api-key}")
    private String apiKey;

    @Value("${langchain4j.community.dashscope.chat-model.model-name}")
    private String model;

    @Bean
    public QwenChatModel qwenModel() {
        return QwenChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .maxTokens(512)
                .build();
    }

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    ChatMemoryProvider chatMemoryProviderXiaoBao() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
