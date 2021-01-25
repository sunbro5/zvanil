package cz.jm.coder.security.service;

import cz.jm.coder.exception.DefaultException;
import cz.jm.coder.exception.ResourceNotFoundException;
import cz.jm.coder.security.model.UserRegistrationRequest;
import cz.jm.coder.user.UserRepository;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void registerNewUser(UserRegistrationRequest registrationRequest){
        Optional.ofNullable(userRepository.getUser(registrationRequest.getUsername()))
                .ifPresent(u -> {
                    throw new DefaultException("User already registered");//todo handle user already exists
                });
        User user = new User(registrationRequest.getUsername(), encoder.encode(registrationRequest.getPassword()));
        userRepository.addUser(user);
    }

    public UserInfo getUser(String userName){
        var user = userRepository.getUser(userName);
        if(user == null){
            throw new ResourceNotFoundException("Uzivatel neexistuje");
        }
        return new UserInfo(user.getUsername());
    }

    public List<UserInfo> getAllUsers(){
        return userRepository.getAllUser().stream()
                .map( user -> new UserInfo(user.getUsername()))
                .collect(Collectors.toList());
    }
}
