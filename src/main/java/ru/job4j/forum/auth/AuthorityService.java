package ru.job4j.forum.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    public final AuthorityRepository authorityRepo;

    public AuthorityService(@Autowired AuthorityRepository authorityRepo) {
        this.authorityRepo = authorityRepo;
    }

    public Optional<Authority> getAuthorityByName(String name) {
        return authorityRepo.getAuthorityByName(name);
    }
}
