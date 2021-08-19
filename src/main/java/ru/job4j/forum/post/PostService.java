package ru.job4j.forum.post;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.BaseEntity;
import ru.job4j.forum.system.Init;

@Service
public class PostService {

    private final List<Post> posts;

    public PostService() {
        this.posts = Init.getAllPosts();
    }

    public List<Post> getAll() {
        return posts;
    }

    public List<Post> getAllByUsername(String username) {
        return posts.stream()
                .filter(post -> username.equals(post.getUser().getUsername()))
                .toList();
    }

    public Optional<Post> getPostById(String strId) {
        Optional<Post> post = Optional.empty();
        try {
            long id = Long.parseLong(strId);
            post = posts.stream().filter(p -> id == p.getId()).findAny();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return post;
    }

    public void save(Post post) {
        if (post.getId() == 0L) {
            post.setId(getLastId() + 1);
            posts.add(post);
        } else {
            var index = posts.indexOf(post);
            posts.set(index, post);
        }
    }

    private long getLastId() {
        return posts.stream().mapToLong(BaseEntity::getId).max().getAsLong();
    }
}
