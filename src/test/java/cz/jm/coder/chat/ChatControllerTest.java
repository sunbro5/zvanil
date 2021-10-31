package cz.jm.coder.chat;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.chat.model.ChatHelper;
import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatMessagePersisted;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ChatControllerTest extends AbstractIntegrationTest {



    @Test
    @WithMockUser(username = "spring")
    public void addChatMessage() throws Exception {
        doReturn("test").when(userFacade).getUserUsername();
        ChatMessage chatMessage = ChatMessage.builder()
                .userName("test")
                .toUserName("toTest")
                .message("testMessage")
                .build();
        successfulCall(post("/api/chat")
                .content(objectToJson(chatMessage))
                .contentType(MediaType.APPLICATION_JSON)
        );
        Optional<ChatMessagePersisted> chatMessagePersisted = chatRepository.getChatMessagesByKey(ChatHelper.getChatKey("test","toTest")).stream()
                .findFirst();
        assertTrue(chatMessagePersisted.isPresent());
        assertEquals("testMessage", chatMessagePersisted.get().getMessage());
    }

    @Test
    public void addChatMessageUnauthorized() throws Exception {
        ChatMessage chatMessage = ChatMessage.builder()
                .userName("test")
                .toUserName("toTest")
                .message("testMessage")
                .build();
        mockMvc.perform(post("/api/chat")
                .content(objectToJson(chatMessage))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testMessages")
    public void getUserChat() throws Exception {
        ChatMessagePersisted chatMessage = ChatMessagePersisted.builder()
                .key(ChatHelper.getChatKey("testMessages", "toTestMessages"))
                .userName("testMessages")
                .toUserName("toTestMessages")
                .message("testGetMessages")
                .build();
        chatRepository.save(chatMessage);
        List<ChatMessage> messages = successfulCallForListObject((get("/api/chat")
                .param("withUsername","toTestMessages")
        ), ChatMessage.class);
        assertEquals(1, messages.size());
        assertEquals("testGetMessages", messages.get(0).getMessage());
    }
}