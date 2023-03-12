package cz.jm.coder.security.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
