package com.example.service;


import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();

    public Optional<User> findById(int theId);

    public void save(User theUser);

    public void deleteById(int theId);
}
