package cz.jm.coder.user;

import cz.jm.coder.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(users.values());
    }
}
