package cz.jm.coder.model;

import lombok.Data;

@Data
public class Post {

    private int id;
    private String userName;
    private String message;
    private String toUserName;
}
