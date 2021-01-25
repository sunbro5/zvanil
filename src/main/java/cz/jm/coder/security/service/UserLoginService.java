package cz.jm.coder.security.service;

import cz.jm.coder.security.model.UserLoginAuth;
import cz.jm.coder.user.UserRepository;
import cz.jm.coder.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLoginAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new UserLoginAuth(user.getUsername(), user.getPassword());
    }

}
