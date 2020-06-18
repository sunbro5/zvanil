package cz.jm.coder.repository;

import cz.jm.coder.model.Post;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryChatRepository implements ChatRepository{

    private volatile Queue<Post> posts = new ConcurrentLinkedQueue<>();

    @Override
    public void addPost(Post post) {
        
    }

    @Override
    public Post getPostsWithUser(String username) {
        return null;
    }
}
