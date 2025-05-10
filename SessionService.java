package com.spoon.demo.service;

import com.spoon.demo.model.User;
import com.spoon.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("user", user);
                return "Login successful";
            } else {
                return "Invalid email or password";
            }
        } else {
            return "Invalid email or password";
        }
    }

    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }

    public Object checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return user;
        } else {
            return "No user is currently logged in.";
        }
    }
}
