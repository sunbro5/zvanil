package cz.jm.coder.chat;

import cz.jm.coder.security.service.LoggedUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private LoggedUserFacade userFacade;

    @Autowired
    private ChatRepository chatRepository;

    public void addChatMessage(ChatMessage message) {
        message.setUserName(userFacade.getUserUsername());
        ChatHelper.populateKeyUsername(message);
        chatRepository.addChatMessage(message);
    }


    public List<ChatMessage> getUserChat(String withUsername) {
        var username = userFacade.getUserUsername();
        return chatRepository.getChatMessagesWithUser(username, withUsername);
    }
}
