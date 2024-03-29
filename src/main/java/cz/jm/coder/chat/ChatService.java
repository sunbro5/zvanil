package cz.jm.coder.chat;

import cz.jm.coder.chat.model.ChatHelper;
import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatMessagePersisted;
import cz.jm.coder.chat.repository.ChatRepository;
import cz.jm.coder.exception.UnauthorizedOperationException;
import cz.jm.coder.security.LoggedUserFacade;
import cz.jm.coder.chat.websocket.WebSocketChatPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(chatMessagePersisted -> chatMessageMapper.chatMessagePersistedToChatMessage(
                        chatMessagePersisted,
                        isAuthor(chatMessagePersisted, username)))
                .collect(Collectors.toList());
    }

    public String getUserChatKey(String withUsername) {
        String username = userFacade.getUserUsername();
        return ChatHelper.getChatKey(username, withUsername);
    }

    public ChatMessage getChatById(String id) {
        String username = userFacade.getUserUsername();
        Optional<ChatMessage> chatMessage = chatRepository.findById(id)
                .map(chatMessagePersisted -> chatMessageMapper.chatMessagePersistedToChatMessage(
                        chatMessagePersisted,
                        isAuthor(chatMessagePersisted, username)
                ))
                .filter(message -> ChatHelper.userInConversation(message.getKey(), username));
        chatMessage.ifPresent(message -> checkIsMessageWithUser(message, username));
        return chatMessage.orElse(null);
    }

    private void checkIsMessageWithUser(ChatMessage message, String username) {
        if (ChatHelper.getChatUsers(message.getKey()).stream().noneMatch(username::equals)) {
            throw new UnauthorizedOperationException("Message is not users.");
        }
    }

    public void removeAll() {
        chatRepository.deleteAll();
    }

    private boolean isAuthor(ChatMessagePersisted chatMessagePersisted, String userName) {
        return chatMessagePersisted.getUserName().equals(userName);
    }

}
