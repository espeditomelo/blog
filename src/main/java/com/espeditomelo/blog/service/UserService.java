package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);

}
