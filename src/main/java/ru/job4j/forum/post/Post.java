package ru.job4j.forum.post;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.forum.model.BaseEntity;
import ru.job4j.forum.user.User;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    private String name;
    private String desc;
    private User user;
    private LocalDateTime created;

    public static Post of(Long id, String name, String desc, User user, LocalDateTime created) {
        Post post = new Post();
        post.setId(id);
        post.setName(name);
        post.setDesc(desc);
        post.setUser(user);
        post.setCreated(created);
        return post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return getId() == post.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
