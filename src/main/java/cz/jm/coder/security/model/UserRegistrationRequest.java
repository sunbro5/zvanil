package cz.jm.coder.security.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegistrationRequest {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", flags = Pattern.Flag.UNICODE_CASE)
    private String username;
    @NotEmpty
    private String password;
}
