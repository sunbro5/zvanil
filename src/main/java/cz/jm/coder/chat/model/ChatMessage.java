package cz.jm.coder.chat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
