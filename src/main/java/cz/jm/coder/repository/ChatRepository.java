package cz.jm.coder.repository;

import cz.jm.coder.model.Post;

public interface ChatRepository {

    void addPost(Post post);

    Post getPostsWithUser(String userName);
}
