package cz.jm.coder.chat;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.chat.model.ChatHelper;
import cz.jm.coder.chat.model.ChatMessage;
import cz.jm.coder.chat.repository.ChatMessagePersisted;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ChatControllerTest extends AbstractIntegrationTest {

    private static final String LONG_MESSAGE = "04142&label=gen173nr-1FCAEoggI46AdIM1gEaDqIAQGYAQm4ARfIAQzYAQHoAQH4AQuIAgGoAgO4ArvkopIGwAIB0gIkODEwZGFhYTMtOWE1ZS00ZDNjLWJlZjktODllMWMyNWZiOTZj2AIG4AIB&sid=38205b243fc22c7619c81eb8b8d12377&all_sr_blocks=370254401_233261066_2_0_0&checkin=2022-05-11&checkout=2022-05-14&dest_id=-850553&dest_type=city&dist=0&group_adults=2&group_children=0&hapos=24&highlighted_blocks=370254401_233261066_2_0_0&hpos=24&matching_block_id=370254401_233261066_2_0_0&nflt=ht_id%3D201%3Bpri%3D1%3Bht_id%3D204%3Bpri%3D2%3Bdistance%3D5000%3Bht_id%3D216%3Broomfacility%3D38&no_rooms=1&req_adults=2&req_children=0&room1=A%2CA&sb_price_type=total&sr_order=price&sr_pri_blocks=370254401_233261066_2_0_0__14542&srepoch=1648931609&srpvid=bb9e9060dd690034&type=total&ucfs=1&activeTab=main;";

    @Test
    @WithMockUser(username = "spring")
    public void addChatMessage() throws Exception {
        doReturn("test").when(userFacade).getUserUsername();
        ChatMessage chatMessage = ChatMessage.builder()
                .userName("test")
                .toUserName("toTest")
                .message(LONG_MESSAGE)
                .build();
        successfulCall(post("/api/chat")
                .content(objectToJson(chatMessage))
                .contentType(MediaType.APPLICATION_JSON)
        );
        Optional<ChatMessagePersisted> chatMessagePersisted = chatRepository.getChatMessagesByKey(ChatHelper.getChatKey("test","toTest")).stream()
                .findFirst();
        assertTrue(chatMessagePersisted.isPresent());
        assertEquals(LONG_MESSAGE, chatMessagePersisted.get().getMessage());
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