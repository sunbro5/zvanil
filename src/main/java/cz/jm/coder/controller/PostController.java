package cz.jm.coder.controller;

import cz.jm.coder.model.Post;
import cz.jm.coder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void addPost(Post post) {
        postRepository.addPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }
}
