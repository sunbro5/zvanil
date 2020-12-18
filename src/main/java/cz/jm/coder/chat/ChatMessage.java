package cz.jm.coder.chat;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class ChatMessage {

    private String key;
    @SafeHtml
    @NotEmpty
    private String userName;
    @SafeHtml
    @NotEmpty
    private String toUserName;
    @SafeHtml
    @NotEmpty
    private String message;

}
