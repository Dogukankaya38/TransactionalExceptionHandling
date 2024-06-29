package com.dogukan.demo.controller;

import com.dogukan.demo.entity.User;
import com.dogukan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/withException")
    public ResponseEntity<String> createUserWithException(@RequestBody User user) {
        try {
            userService.saveUserWithException(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/withoutRollback")
    public ResponseEntity<String> createUserWithoutRollback(@RequestBody User user) {
        try {
            userService.saveUserWithoutRollback(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("User created successfully");
    }
}
