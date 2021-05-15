package cz.jm.coder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.jm.coder.chat.repository.ChatRepository;
import cz.jm.coder.security.LoggedUserFacade;
import cz.jm.coder.user.repository.UserRepository;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration
public abstract class AbstractIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgressContainer.getInstance();

    @Autowired
    protected MockMvc mockMvc;

    @SpyBean
    protected LoggedUserFacade userFacade;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ChatRepository chatRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

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
