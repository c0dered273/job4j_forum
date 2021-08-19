package ru.job4j.forum.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.user.User;
import ru.job4j.forum.user.UserService;

@Controller
public class AuthController {

    private final UserService users;
    private final AuthorityService authorities;

    public AuthController(
            @Autowired UserService users,
            @Autowired AuthorityService authorities) {
        this.users = users;
        this.authorities = authorities;
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
        user.setAuthority(authorities.getAuthorityByName("USER")
                .orElse(Authority.of("NONE")));
        users.create(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "auth/login";
    }

}
