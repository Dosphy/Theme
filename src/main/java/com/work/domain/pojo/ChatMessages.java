package com.work.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author Dosphy
 * @Date 2026/1/3 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")
public class ChatMessages {

    @Id
    private ObjectId messageId;

    private String memoryId;

    private String content;

//    @Override
//    public String toString() {
//        return "ChatMessages{" +
//                "messageId=" + messageId +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
