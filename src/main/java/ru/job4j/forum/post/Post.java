package ru.job4j.forum.post;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.forum.user.User;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "description")
    private String desc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
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
