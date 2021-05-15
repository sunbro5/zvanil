package cz.jm.coder.user.repository;

import cz.jm.coder.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserPersisted, String> {

}
