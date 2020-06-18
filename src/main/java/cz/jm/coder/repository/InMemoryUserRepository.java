package cz.jm.coder.repository;

import cz.jm.coder.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private volatile Map<String, User> users = new ConcurrentHashMap<>();


    @Override
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }
}
