package cz.jm.coder.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document("user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPersisted {

    @Id
    private String username;
    private String password;
}
