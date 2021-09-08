package ru.job4j.forum.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(value = "user_with_authorities")
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
