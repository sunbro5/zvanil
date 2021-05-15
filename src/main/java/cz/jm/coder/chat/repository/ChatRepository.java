package cz.jm.coder.chat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<ChatMessagePersisted, String> {

    @Query("SELECT u FROM ChatMessagePersisted u WHERE u.userName = ?1 and u.toUserName = ?2")
    List<ChatMessagePersisted> getChatMessagesWithUser(String username, String withUsername);
}
