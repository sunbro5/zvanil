package cz.jm.coder.chat;

import cz.jm.coder.chat.model.ChatHelper;
import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatMessagePersisted;
import cz.jm.coder.chat.repository.ChatRepository;
import cz.jm.coder.security.LoggedUserFacade;
import cz.jm.coder.chat.websocket.WebSocketChatPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private LoggedUserFacade userFacade;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private WebSocketChatPusher webSocketChatPusher;

    public void addChatMessage(ChatMessage message) {
        message.setUserName(userFacade.getUserUsername());
        ChatHelper.populateKeyUsername(message);
        ChatMessagePersisted messageSaved = chatRepository.save(chatMessageMapper.chatMessageToChatMessagePersisted(message));
        webSocketChatPusher.chatMessageAdded(messageSaved);
    }


    public List<ChatMessage> getUserChat(String withUsername) {
        String username = userFacade.getUserUsername();
        String chatKey = ChatHelper.getChatKey(username, withUsername);
        return chatRepository.getChatMessagesByKey(chatKey).stream()
                .map(chatMessageMapper::chatMessagePersistedToChatMessage)
                .collect(Collectors.toList());
    }

    public ChatMessage getChatById(int id) {
        String username = userFacade.getUserUsername();
        return chatRepository.findById(id)
                .map(chatMessageMapper::chatMessagePersistedToChatMessage)
                .filter(message -> ChatHelper.userInConversation(message.getKey(), username))
                .orElse(null);
    }
}
