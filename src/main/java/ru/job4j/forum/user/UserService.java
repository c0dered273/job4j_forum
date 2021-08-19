package ru.job4j.forum.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.forum.system.Init;

@Service
public class UserService {

    private final List<User> users;
    private final InMemoryUserDetailsManager manager;

    public UserService(@Autowired UserDetailsService manager) {
        this.users = Init.getAllUsers();
        this.manager = (InMemoryUserDetailsManager) manager;
    }

    public User getLoggedUser() {
        UserDetails loggedUser =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getByName(loggedUser.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public Optional<User> getByName(String name) {
        return users.stream().filter(u -> name.equals(u.getUsername())).findAny();
    }

    public boolean existsUserByName(String name) {
        return users.stream().anyMatch(u -> name.equals(u.getUsername()));
    }

    public void create(User user) {
        users.add(user);
        addUserToMemory(user);
    }

    private void addUserToMemory(User user) {
        manager.createUser(org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getAuthority().getName())
                .build());
    }
}
