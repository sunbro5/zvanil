package cz.jm.coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.jm.coder.chat.ChatRepository;
import cz.jm.coder.security.service.LoggedUserFacade;
import cz.jm.coder.user.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @SpyBean
    protected LoggedUserFacade userFacade;

    @SpyBean
    protected UserRepository userRepository;

    @SpyBean
    protected ChatRepository chatRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();

    protected <T> List<T> successfulCallForListObject(RequestBuilder requestBuilder, Class<T> object) throws Exception {
        return jsonToListObject(
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn().getResponse().getContentAsString(), object);
    }

    protected <T> T successfulCallForObject(RequestBuilder requestBuilder, Class<T> object) throws Exception {
        return jsonToObject(
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn().getResponse().getContentAsString(), object);
    }

    protected MvcResult successfulCall(RequestBuilder requestBuilder) throws Exception {
        return mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    protected <T> List<T> jsonToListObject(String json, Class<T> object) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, object));
    }

    protected <T> T jsonToObject(String json, Class<T> object) throws IOException {
        return objectMapper.readValue(json, object);
    }

    protected <T> String objectToJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
