package com.spoon.demo.controller;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return new File("uploads/").getAbsolutePath();
    }
}
