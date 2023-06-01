package ru.isu.auc.auction.model.dto.mappers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isu.auc.auction.model.dto.response.UserDTO;
import ru.isu.auc.common.api.DTOMapper;
import ru.isu.auc.security.model.User;

@Service
public class UserMapper implements DTOMapper<UserDTO, User> {
    @Override
    public User mapFromDto(UserDTO userDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDTO mapToDto(User user) {
        return new UserDTO()
            .setEmail(user.getEmail())
            .setId(user.getId())
            .setUsername(user.getUsername());
    }
}
