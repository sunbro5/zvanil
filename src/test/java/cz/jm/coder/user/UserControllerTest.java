package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.model.UserInfo;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    @WithMockUser(username = "spring")
    public void getUser() throws Exception {
        User user = new User().builder()
                .username("anotherUser")
                .password("test")
                .build();
        doReturn(user).when(userRepository).getUser("anotherUser");
        UserInfo userInfo = successfulCallForObject(get("/api/user/anotherUser"), UserInfo.class);
        assertEquals("anotherUser", userInfo.getUsername());
    }

}