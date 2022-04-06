package cz.jm.coder.chat.websocket;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChatMessageWS {
    int messageId;
    ChatMessageEventType type;
}
