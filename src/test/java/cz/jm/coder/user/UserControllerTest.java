package cz.jm.coder.user;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.user.model.UserInfo;
import cz.jm.coder.user.repository.UserPersisted;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserControllerTest extends AbstractIntegrationTest {

    private static String TEST_USER = "anotherUser";

    @AfterEach
    public void after(){
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "spring")
    public void getUser() throws Exception {
        UserPersisted user = UserPersisted.builder()
                .username(TEST_USER)
                .password("test")
                .build();
        userRepository.save(user);
        UserInfo userInfo = successfulCallForObject(get("/api/user/" + TEST_USER), UserInfo.class);
        assertEquals(TEST_USER, userInfo.getUsername());
    }

    @Test
    @WithMockUser(username = "spring")
    public void getAllUserEmpty() throws Exception {
        List<UserInfo> userInfos = successfulCallForListObject(get("/api/user/all"), UserInfo.class);
        assertTrue(userInfos.isEmpty());
    }

    @Test
    @WithMockUser(username = "spring")
    public void getAllUser() throws Exception {
        UserPersisted user = UserPersisted.builder()
                .username(TEST_USER)
                .password("test")
                .build();
        userRepository.save(user);
        List<UserInfo> userInfos = successfulCallForListObject(get("/api/user/all"), UserInfo.class);
        assertFalse(userInfos.isEmpty());
        assertEquals(TEST_USER, userInfos.get(0).getUsername());
    }
}