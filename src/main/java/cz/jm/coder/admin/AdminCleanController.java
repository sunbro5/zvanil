package cz.jm.coder.admin;

import cz.jm.coder.chat.ChatService;
import cz.jm.coder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO secure this
@RestController
@RequestMapping("/api/admin")
public class AdminCleanController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping("/chat/remove/all")
    public void removeAllChat(){
        chatService.removeAll();
    }

    @GetMapping("/user/remove/{id}")
    public void removeAllUser(@PathVariable String id){
        userService.removeUser(id);
    }




}
