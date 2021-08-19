package ru.job4j.forum.auth;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.forum.model.BaseEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends BaseEntity {

    private String name;

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
