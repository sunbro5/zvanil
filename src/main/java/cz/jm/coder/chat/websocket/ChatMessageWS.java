package cz.jm.coder.chat.websocket;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChatMessageWS {
    String messageId;
    ChatMessageEventType type;
}
