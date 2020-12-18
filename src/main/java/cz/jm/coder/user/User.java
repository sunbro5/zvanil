package cz.jm.coder.user;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    @NotBlank
    @SafeHtml
    private String username;
    @NotBlank
    @SafeHtml
    private String password;
}
