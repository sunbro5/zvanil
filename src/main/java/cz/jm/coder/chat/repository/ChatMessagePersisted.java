package cz.jm.coder.chat.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("chatMessage")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessagePersisted {

    @Id
    private String id;
    private String key;
    private String userName;
    private String toUserName;
    private String message;

}
