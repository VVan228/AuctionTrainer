package ru.isu.auc.security.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isu.auc.security.model.User;

@Service
public class UserService {

    UserRepo userRepo;

    @Autowired
    UserService(
            UserRepo userRepo
    ){
        this.userRepo = userRepo;
    }

    public User getByEmail(String email){
        return userRepo.getByEmail(email);
    }

    @Transactional
    public void replaceRefreshToken(Long id, String refreshToken){
        userRepo.replaceRefreshToken(id, refreshToken);
    }
    public void replaceRefreshToken(User user, String refreshToken){
        user.setCurrentRefreshTokenHash(refreshToken);
        userRepo.save(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
}
