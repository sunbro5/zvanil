package cz.jm.coder.chat.websocket;

import cz.jm.coder.chat.repository.ChatMessagePersisted;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketChatPusher {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void chatMessageAdded(ChatMessagePersisted message) {
        ChatMessageWS messageWS = ChatMessageWS.builder()
                .toUserName(message.getToUserName())
                .userName(message.getUserName())
                .messageId(message.getId())
                .type(ChatMessageEventType.ADDED)
                .build();
        simpMessagingTemplate.convertAndSend("/topic/chat/" + message.getKey(), messageWS);
    }


}
