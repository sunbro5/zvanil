package cz.jm.coder.user;

import cz.jm.coder.user.model.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);

    User getUser(String username);

    List<User> getAllUser();
}
