package ru.job4j.forum.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.post.PostService;
import ru.job4j.forum.user.UserService;

@Controller
public class IndexController {

    private final PostService posts;
    private final UserService users;

    public IndexController(@Autowired PostService posts,
                           @Autowired UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        var user = users.getLoggedUser();
        model.addAttribute("posts", posts.getAllByUsername(user.getUsername()));
        model.addAttribute("username", user.getUsername());
        return "index";
    }
}
