package cz.jm.coder.user;

import cz.jm.coder.exception.DefaultException;
import cz.jm.coder.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(User user){
        Optional.ofNullable(userRepository.getUser(user.getUsername()))
                .ifPresent(u -> {
                    throw new DefaultException("User already registered");//todo handle user already exists
                });
        userRepository.addUser(user);
    }
}
