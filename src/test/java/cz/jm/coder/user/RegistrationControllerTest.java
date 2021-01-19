package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RegistrationControllerTest extends AbstractIntegrationTest {

    @Test
    public void register() throws Exception {
        User user = new User().builder()
                .username("testUser")
                .password("test")
                .build();
        successfulCall(post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(user)));
        successfulCall(post("/login_process")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "testUser")
                .param("password", "test")
        );
    }
}