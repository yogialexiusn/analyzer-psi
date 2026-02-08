package com.example.converteraudio.repository;

import com.example.converteraudio.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User findById(String id);

    User findByEmail(String email);

    List<User> findAll();

    void deleteById(String id);
}
