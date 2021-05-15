package cz.jm.coder.user;

import cz.jm.coder.user.model.User;
import cz.jm.coder.user.repository.UserPersisted;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userToUserPersisted(UserPersisted userPersisted);

    UserPersisted userPersistedToUser(User user);
}
