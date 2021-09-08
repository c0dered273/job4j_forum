package ru.job4j.forum.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.auth.Authority;
import ru.job4j.forum.auth.AuthorityRepository;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AuthorityRepository authRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepo,
                       @Autowired AuthorityRepository authRepo,
                       @Autowired PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authRepo = authRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails getLoggedUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Optional<User> getByName(String name) {
        return userRepo.findByUsername(name);
    }

    public boolean existsUserByName(String name) {
        return userRepo.existsByUsername(name);
    }

    public void create(User user) {
        Set<Authority> roles = new HashSet<>();
        roles.add(authRepo.getAuthorityByName("ROLE_USER").get());
        user.setAuthorities(roles);
        var passwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwd);
        userRepo.save(user);
    }
}
