package cz.jm.coder.security.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class UserRegistrationRequest {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", flags = Pattern.Flag.UNICODE_CASE)
    private String username;
    @NotEmpty
    private String password;
}
