package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RegistrationControllerTest extends AbstractIntegrationTest {

    @Test
    public void register() throws Exception {
        User user = new User().builder()
                .username("testRegistrationUser")
                .password("test")
                .build();
        successfulCall(post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(user)));
        verify(userRepository, times(1))
                .addUser(argThat(argument -> argument.getUsername().equals("testRegistrationUser")));
    }
}