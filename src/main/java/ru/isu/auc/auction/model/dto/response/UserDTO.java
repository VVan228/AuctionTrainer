package ru.isu.auc.auction.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.isu.auc.security.model.User;

@Data
@NoArgsConstructor
public class UserDTO {
    Long id;
    String email;
    String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
