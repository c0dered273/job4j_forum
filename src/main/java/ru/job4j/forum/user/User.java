package ru.job4j.forum.user;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.forum.auth.Authority;
import ru.job4j.forum.model.BaseEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    private String username;
    private String password;
    private Authority authority;
    private boolean enabled = true;

    public static User of(String username, String password, Authority authority) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAuthority(authority);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
