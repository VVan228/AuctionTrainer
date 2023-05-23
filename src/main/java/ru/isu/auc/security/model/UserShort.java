package ru.isu.auc.security.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class UserShort {
    String username;
    String email;
    Long id;

    public UserShort setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserShort setId(Long id) {
        this.id = id;
        return this;
    }

    public UserShort(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
