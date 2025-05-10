package com.spoon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spoon.demo.service.SessionService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        String response = sessionService.login(email, password, session);
        if (response.equals("Login successful")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response); // Unauthorized
        }
    }

    // Logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return ResponseEntity.ok("Logout successful");
    }

    // Check if the user is logged in
    @GetMapping("/check")
    public ResponseEntity<Object> checkSession(HttpSession session) {
        Object response = sessionService.checkSession(session);
        return ResponseEntity.ok(response);
    }
}
