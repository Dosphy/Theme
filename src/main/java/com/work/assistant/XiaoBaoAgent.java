package com.work.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * @Author Dosphy
 * @Date 2026/1/7 11:16
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoBao",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaoBaoPinecone"
)
public interface XiaoBaoAgent {
    @SystemMessage(fromResource = "xiaobao-prompt-template.txt")
    Flux<String> chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
