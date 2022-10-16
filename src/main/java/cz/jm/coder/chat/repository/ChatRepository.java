package cz.jm.coder.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatMessagePersisted, String> {

    @Query("{ 'key' : ?0 }")
    List<ChatMessagePersisted> getChatMessagesByKey(String key);

    @Query("{ 'userName' : ?0 }")
    List<ChatMessagePersisted> getUserChatWith(String username);
}
