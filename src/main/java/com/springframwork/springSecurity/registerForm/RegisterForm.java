package com.springframwork.springSecurity.registerForm;

import com.springframwork.springSecurity.models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class RegisterForm {

    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder encoder){
        return new User(username,encoder.encode(password),email);
    }
}
