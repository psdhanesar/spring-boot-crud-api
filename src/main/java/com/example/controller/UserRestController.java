package com.example.controller;


import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId(0);
        userService.save(user);
        return user;
    }


    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        Optional<User> tempUser = userService.findById(userId);
        if(tempUser.isPresent()){
            userService.deleteById(userId);
            return "Deleted user id - " + userId;
        }
        return "user id not found - " + userId;
    }

}
