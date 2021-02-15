package org.chess.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean comparePassword(String password, String dbPassword) {
        return encoder.matches(password, dbPassword);
    }
}
