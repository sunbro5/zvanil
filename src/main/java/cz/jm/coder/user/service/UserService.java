package cz.jm.coder.user.service;

import cz.jm.coder.exception.ResourceNotFoundException;
import cz.jm.coder.user.model.User;
import cz.jm.coder.user.model.UserInfo;
import cz.jm.coder.user.repository.UserPersisted;
import cz.jm.coder.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfo getUser(String userName) {
        Optional<UserPersisted> user = userRepository.findById(userName);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("Uzivatel neexistuje");
        }
        return new UserInfo(user.get().getUsername());
    }

    public List<UserInfo> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> new UserInfo(user.getUsername()))
                .collect(Collectors.toList());
    }

    public void removeUser(String id){
        userRepository.deleteById(id);
    }
}
