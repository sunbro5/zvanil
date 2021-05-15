package cz.jm.coder.chat;

import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatMessagePersisted;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    ChatMessagePersisted chatMessageToChatMessagePersisted(ChatMessage message);

    ChatMessage chatMessagePersistedToChatMessage(ChatMessagePersisted chatMessagePersisted);
}
