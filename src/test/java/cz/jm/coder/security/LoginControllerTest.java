package cz.jm.coder.security;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.security.model.JwtResponse;
import cz.jm.coder.security.model.UserLoginRequest;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.model.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LoginControllerTest extends AbstractIntegrationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void authenticateUser() throws Exception {
        User user = new User().builder()
                .username("testLoginUser")
                .password(passwordEncoder.encode("test"))
                .build();
        doReturn(user).when(userRepository).getUser("testLoginUser");

        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username("testLoginUser")
                .password("test")
                .build();

        JwtResponse response = successfulCallForObject(post("/api/auth")
                .content(objectToJson(userLoginRequest))
                .contentType(MediaType.APPLICATION_JSON), JwtResponse.class);
        assertEquals("testLoginUser", response.getUsername());
    }

    @Test
    public void getSecuredResource() throws Exception {
        User user = new User().builder()
                .username("testLoginUser")
                .password(passwordEncoder.encode("test"))
                .build();
        doReturn(user).when(userRepository).getUser("testLoginUser");

        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username("testLoginUser")
                .password("test")
                .build();

        JwtResponse response = successfulCallForObject(post("/api/auth")
                .content(objectToJson(userLoginRequest))
                .contentType(MediaType.APPLICATION_JSON), JwtResponse.class);
        assertEquals("testLoginUser", response.getUsername());

        String token = response.getToken();

        UserInfo userInfo = successfulCallForObject(get("/api/user/testLoginUser")
                        .header("Authorization", "Bearer " + token)
                , UserInfo.class);
        assertEquals("testLoginUser", userInfo.getUsername());
    }

}