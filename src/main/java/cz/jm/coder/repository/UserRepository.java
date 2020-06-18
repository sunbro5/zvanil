package cz.jm.coder.repository;

import cz.jm.coder.model.User;

public interface UserRepository {

    void addUser(User user);

    User getUser(String username);
}
