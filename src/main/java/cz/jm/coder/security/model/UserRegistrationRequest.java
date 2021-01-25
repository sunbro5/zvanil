package cz.jm.coder.security.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRegistrationRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
