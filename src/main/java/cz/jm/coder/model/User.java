package cz.jm.coder.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
