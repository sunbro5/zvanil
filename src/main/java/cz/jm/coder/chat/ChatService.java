package cz.jm.coder.chat;

import cz.jm.coder.chat.model.ChatHelper;
import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatRepository;
import cz.jm.coder.security.LoggedUserFacade;
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

    public void addChatMessage(ChatMessage message) {
        message.setUserName(userFacade.getUserUsername());
        ChatHelper.populateKeyUsername(message);
        chatRepository.save(chatMessageMapper.chatMessageToChatMessagePersisted(message));
    }


    public List<ChatMessage> getUserChat(String withUsername) {
        String username = userFacade.getUserUsername();
        return chatRepository.getChatMessagesWithUser(username, withUsername).stream()
                .map(chatMessageMapper::chatMessagePersistedToChatMessage)
                .collect(Collectors.toList());
    }
}
