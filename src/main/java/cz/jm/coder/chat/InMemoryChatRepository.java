package cz.jm.coder.chat;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryChatRepository implements ChatRepository {

    private Map<String, List<ChatMessage>> chatMessages = new ConcurrentHashMap<>();

    @Override
    public void addChatMessage(ChatMessage message) {
        var messageList = chatMessages.get(message.getKey());
        if (messageList == null) {
            List<ChatMessage> chatMessageList = new ArrayList<>();
            chatMessageList.add(message);
            chatMessages.put(ChatHelper.getChatKey(message), chatMessageList);
        } else {
            messageList.add(message);
        }
    }

    @Override
    public List<ChatMessage> getChatMessagesWithUser(String username, String withUsername) {
        String key = ChatHelper.getChatKey(username, withUsername);
        return chatMessages.get(key);
    }
}
