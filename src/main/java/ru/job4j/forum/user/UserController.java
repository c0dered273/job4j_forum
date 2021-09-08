package ru.job4j.forum.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService users;

    public UserController(
            @Autowired UserService users) {
        this.users = users;
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String newUser(@ModelAttribute User user, Model model) {
        if (users.existsUserByName(user.getUsername())) {
            String errorMessage = "User already exists";
            model.addAttribute("errorMessage", errorMessage);
            return "auth/register";
        }
        users.create(user);
        return "redirect:/login";
    }
}
