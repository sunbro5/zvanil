package cz.jm.coder.chat;

import cz.jm.coder.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addChatMessage(@Valid @RequestBody ChatMessage message) {
        chatService.addChatMessage(message);
    }

    @GetMapping("/{id}")
    public ChatMessage getMessage(@PathVariable int id) {
        return chatService.getChatById(id);
    }

    @GetMapping
    public List<ChatMessage> getUserChat(@RequestParam String withUsername) {
        return chatService.getUserChat(withUsername);
    }

}
