package cz.jm.coder.user;

import cz.jm.coder.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private LoggedUserFacade userFacade;

    @GetMapping
    public UserInfo getUserInfo(){
        return new UserInfo(userFacade.getUserUsername());
    }
}
