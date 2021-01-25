package cz.jm.coder.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
