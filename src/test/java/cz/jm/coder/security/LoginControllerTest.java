package cz.jm.coder.security;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.security.model.JwtResponse;
import cz.jm.coder.security.model.UserLoginRequest;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.model.UserInfo;
import cz.jm.coder.user.repository.UserPersisted;
import org.junit.After;
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

    private static final String TEST_USER = "testLoginUser";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @After
    public void after(){
        userRepository.deleteAll();
    }

    @Test
    public void authenticateUser() throws Exception {
        UserPersisted user = UserPersisted.builder()
                .username(TEST_USER)
                .password(passwordEncoder.encode("test"))
                .build();
        userRepository.save(user);

        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(TEST_USER)
                .password("test")
                .build();

        JwtResponse response = successfulCallForObject(post("/api/auth")
                .content(objectToJson(userLoginRequest))
                .contentType(MediaType.APPLICATION_JSON), JwtResponse.class);
        assertEquals(TEST_USER, response.getUsername());
    }

    @Test
    public void getSecuredResource() throws Exception {
        UserPersisted user = UserPersisted.builder()
                .username(TEST_USER)
                .password(passwordEncoder.encode("test"))
                .build();
        userRepository.save(user);

        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(TEST_USER)
                .password("test")
                .build();

        JwtResponse response = successfulCallForObject(post("/api/auth")
                .content(objectToJson(userLoginRequest))
                .contentType(MediaType.APPLICATION_JSON), JwtResponse.class);
        assertEquals(TEST_USER, response.getUsername());

        String token = response.getToken();

        UserInfo userInfo = successfulCallForObject(get("/api/user/"+ TEST_USER)
                        .header("Authorization", "Bearer " + token)
                , UserInfo.class);
        assertEquals(TEST_USER, userInfo.getUsername());
    }

}