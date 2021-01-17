package cz.jm.coder.user;

import cz.jm.coder.user.model.User;

public interface UserRepository {

    void addUser(User user);

    User getUser(String username);
}
