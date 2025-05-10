package com.spoon.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @GetMapping("/about")
    public String aboutus() {
        return "aboutus.html";
    }

    @GetMapping("/submit")
    public String article() {
        return "submitrecipe.html";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profil.html";
    }

    @GetMapping("/liked")
    public String liked() {
        return "liked.html";
    }

    @GetMapping("/recipes")
    public String Recipes() {
        return "recipes.html";
    }

    @GetMapping("/blog")
    public String blog() {
        return "page-blog.html";
    }

    @GetMapping("/update")
    public String update() {
        return "update.html";
    }

    @GetMapping("/appetizers")
    public String appetizers() {
        return "appetizers.html";
    }

    @GetMapping("/recipe")
    public String recipe() {
        return "classicBruschetta.html";
    }
}
