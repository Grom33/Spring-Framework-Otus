package ru.otus.gromov.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "UserName", nullable = false, length = 100)
    private String UserName;

    @NotBlank
    @Column(name = "password", nullable = false, length = 100)
    private String password;

/*    @NotBlank
    @Column(name = "roles", nullable = false, length = 100)
    private Set<String> roles;*/

    public User() {
    }

    public User(@NotBlank String userName, @NotBlank String password) {
        UserName = userName;
        this.password = password;
    }
}
