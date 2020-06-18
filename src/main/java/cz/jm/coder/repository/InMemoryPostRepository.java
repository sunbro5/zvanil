package cz.jm.coder.repository;

import cz.jm.coder.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class InMemoryPostRepository implements PostRepository {

    private volatile Queue<Post> posts = new ConcurrentLinkedQueue<>();

    @Override
    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }
}
