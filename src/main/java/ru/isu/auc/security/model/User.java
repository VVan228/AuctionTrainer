package ru.isu.auc.security.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Empty password")
    String password;
    @NotBlank(message = "Empty email")
    String email;
    @NotBlank(message = "Empty username")
    String username;
    String currentRefreshTokenHash;
    Role role;

}