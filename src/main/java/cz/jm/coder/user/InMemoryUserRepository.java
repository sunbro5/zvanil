package cz.jm.coder.user;

import cz.jm.coder.user.User;
import cz.jm.coder.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> users = new ConcurrentHashMap<>();


    @Override
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }
}
