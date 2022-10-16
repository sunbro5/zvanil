package cz.jm.coder.user.repository;

import cz.jm.coder.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserPersisted, String> {

}
