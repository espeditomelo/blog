package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
