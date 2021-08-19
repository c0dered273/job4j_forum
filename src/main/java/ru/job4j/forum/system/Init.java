package ru.job4j.forum.system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.forum.auth.Authority;
import ru.job4j.forum.post.Post;
import ru.job4j.forum.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Init {

    public static List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        List<User> users = getAllUsers();
        posts.add(Post.of(1L, "Продаю машину ладу 01.", "2106", users.get(0), LocalDateTime.now()));
        posts.add(Post.of(2L, "Продаю машину ладу 02.", "2112", users.get(1), LocalDateTime.of(2012, 4, 11, 15, 43)));
        posts.add(Post.of(3L, "Продаю машину ладу 03.", "2109", users.get(1), LocalDateTime.of(1981, 12, 31, 21, 27)));
        return posts;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<Authority> authorities = getAllAuthorities();
        users.add(User.of("Admin", "admin", authorities.get(0)));
        users.add(User.of("User", "user", authorities.get(1)));
        return users;
    }

    public static List<UserDetails> getAllUserDetails() {
        List<UserDetails> userDetails = new ArrayList<>();
        for (User usr : getAllUsers()) {
            UserDetails user = org.springframework.security.core.userdetails.User
                    .withDefaultPasswordEncoder()
                    .username(usr.getUsername())
                    .password(usr.getPassword())
                    .roles(usr.getAuthority().getName())
                    .build();
            userDetails.add(user);
        }
        return userDetails;
    }

    public static List<Authority> getAllAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.of("ADMIN"));
        authorities.add(Authority.of("USER"));
        return authorities;
    }
}
