package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    public void getUserInfo() throws Exception {
        User user = new User().builder()
                .username("testUser")
                .password("test")
                .build();
        doReturn(user).when(userRepository).getUser("testUser");

        successfulCall(post("/login_process")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "testUser")
                .param("password", "test")
        );
    }

}