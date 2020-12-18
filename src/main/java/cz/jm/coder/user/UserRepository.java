package cz.jm.coder.user;

import cz.jm.coder.user.User;

public interface UserRepository {

    void addUser(User user);

    User getUser(String username);
}
