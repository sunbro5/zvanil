package cz.jm.coder.user.service;

import cz.jm.coder.security.model.UserLoginAuth;
import cz.jm.coder.user.repository.UserPersisted;
import cz.jm.coder.user.repository.UserRepository;
import cz.jm.coder.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLoginAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPersisted> user = userRepository.findById(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new UserLoginAuth(user.get().getUsername(), user.get().getPassword());
    }

}
