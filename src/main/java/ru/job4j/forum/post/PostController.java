package ru.job4j.forum.post;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.forum.user.UserService;

@Controller
public class PostController {

    private final PostService posts;
    private final UserService users;

    public PostController(
            @Autowired PostService posts,
            @Autowired UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping("/post")
    public String newPostForm(Model model) {
        var user = users.getLoggedUser();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("post_id", "new");
        return "post/edit";
    }

    @PostMapping("/post")
    public String newPost(@ModelAttribute Post post) {
        var user = users.getLoggedUser();
        post.setId(0L);
        post.setUser(users.getByName(user.getUsername())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        post.setCreated(LocalDateTime.now());
        posts.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String editPost(@PathVariable(name = "id") String id, Model model) {
        var user = users.getLoggedUser();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("post_id", id);
        var post = posts.getPostById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PutMapping("/post/{id}")
    public String editPost(@ModelAttribute Post post,
                                @PathVariable(value = "id") Long id) {
        var user = users.getLoggedUser();
        post.setId(id);
        post.setUser(users.getByName(user.getUsername())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        post.setCreated(LocalDateTime.now());
        posts.save(post);
        return "redirect:/";
    }
}
