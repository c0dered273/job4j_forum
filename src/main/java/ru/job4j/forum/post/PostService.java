package ru.job4j.forum.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepo;

    public PostService(@Autowired PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        postRepo.findAll().forEach(posts::add);
        return posts;
    }

    public List<Post> getAllByUsername(String username) {
        return postRepo.findAllByUserUsername(username);
    }

    public Optional<Post> getPostById(String strId) {
        Optional<Post> post = Optional.empty();
        try {
            long id = Long.parseLong(strId);
            post = postRepo.findById(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return post;
    }

    public void save(Post post) {
        postRepo.save(post);
    }
}
