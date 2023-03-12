package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.repository.UserPersisted;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RegistrationControllerTest extends AbstractIntegrationTest {

    @AfterEach
    public void after(){
        userRepository.deleteAll();
    }

    @Test
    public void register() throws Exception {
        User user = User.builder()
                .username("testRegistrationUser")
                .password("test")
                .build();
        successfulCall(post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(user)));

        Optional<UserPersisted> userPersisted = userRepository.findById("testRegistrationUser");
        assertTrue(userPersisted.isPresent());
    }
}