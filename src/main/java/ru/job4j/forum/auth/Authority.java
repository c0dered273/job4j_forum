package ru.job4j.forum.auth;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import ru.job4j.forum.user.User;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "authorities")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public static Authority of(String name) {
        Authority authority = new Authority();
        authority.setName(name);
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority authority)) {
            return false;
        }
        return getId() == authority.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
