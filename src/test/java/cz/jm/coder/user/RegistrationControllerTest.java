package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.repository.UserPersisted;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RegistrationControllerTest extends AbstractIntegrationTest {

    @After
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