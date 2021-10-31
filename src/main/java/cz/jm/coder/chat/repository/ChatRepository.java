package cz.jm.coder.chat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<ChatMessagePersisted, Integer> {

    @Query("SELECT u FROM ChatMessagePersisted u WHERE u.key = ?1")
    List<ChatMessagePersisted> getChatMessagesByKey(String key);

    @Query("SELECT u.toUserName FROM ChatMessagePersisted u WHERE u.userName = ?1")
    List<String> getUserChatWith(String username, String withUsername);
}
