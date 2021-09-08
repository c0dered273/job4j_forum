package ru.job4j.forum.auth;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Optional<Authority> getAuthorityByName(String name);

}
