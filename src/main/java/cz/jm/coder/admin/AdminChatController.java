package cz.jm.coder.admin;

import cz.jm.coder.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO secure this
@RestController
@RequestMapping("/api/admin/chat")
public class AdminChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/remove/all")
    public void removeAll(){
        chatService.removeAll();
    }




}
