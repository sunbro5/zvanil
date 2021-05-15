package cz.jm.coder.user;

import cz.jm.coder.security.LoggedUserFacade;
import cz.jm.coder.user.service.RegistrationService;
import cz.jm.coder.user.model.UserInfo;
import cz.jm.coder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private LoggedUserFacade userFacade;

    @Autowired
    private UserService userService;

    @GetMapping
    public UserInfo getUserInfo(){
        return new UserInfo(userFacade.getUserUsername());
    }

    @GetMapping("/{userName}")
    public UserInfo getSpecificUserInfo(@PathVariable String userName){
        return userService.getUser(userName);
    }

    @GetMapping("/all")
    public List<UserInfo> getAllUsersInfo(){
        return userService.getAllUsers();
    }

}
