package cz.jm.coder.chat.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessagePersisted {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String key;
    private String userName;
    private String toUserName;
    //@Lob
    @Column(columnDefinition="TEXT")
    private String message;

}
