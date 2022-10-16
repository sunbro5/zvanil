package cz.jm.coder.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private String id;
    private String key;
    private String userName;
    @NotEmpty
    private String toUserName;
    @NotEmpty
    private String message;
    private boolean isAuthor;

}
