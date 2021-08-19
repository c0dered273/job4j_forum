package ru.job4j.forum.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.job4j.forum.system.Init;

@Service
public class AuthorityService {

    public final List<Authority> authorities;

    public AuthorityService() {
        this.authorities = Init.getAllAuthorities();
    }

    public Optional<Authority> getAuthorityByName(String name) {
        return authorities.stream()
                .filter(authority -> name.equals(authority.getName()))
                .findAny();
    }
}
