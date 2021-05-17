package cz.jm.coder.chat.websocket;

import cz.jm.coder.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketChatPusher {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void chatMessageAdded(ChatMessage message){
        ChatMessageWS messageWS = ChatMessageWS.builder()
                .message(message.getUserName())
                .toUserName(message.getToUserName())
                .userName(message.getUserName())
                .type(ChatMessageEventType.ADDED)
                .build();
        simpMessagingTemplate.convertAndSendToUser(message.getToUserName(), "/topic/chat", messageWS);
    }


}
