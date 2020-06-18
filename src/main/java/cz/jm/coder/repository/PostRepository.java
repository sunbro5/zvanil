package cz.jm.coder.repository;

import cz.jm.coder.model.Post;

import java.util.List;

public interface PostRepository {

    void addPost(Post post);

    List<Post> getAllPosts();
}
