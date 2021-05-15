package cz.jm.coder.user.service;

import cz.jm.coder.exception.DefaultException;
import cz.jm.coder.exception.ResourceNotFoundException;
import cz.jm.coder.security.model.UserRegistrationRequest;
import cz.jm.coder.user.repository.UserPersisted;
import cz.jm.coder.user.repository.UserRepository;
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
        userRepository.findById(registrationRequest.getUsername())
                .ifPresent(u -> {
                    throw new DefaultException("User already registered");//todo handle user already exists
                });
        UserPersisted user = new UserPersisted(registrationRequest.getUsername(), encoder.encode(registrationRequest.getPassword()));
        userRepository.save(user);
    }
}
