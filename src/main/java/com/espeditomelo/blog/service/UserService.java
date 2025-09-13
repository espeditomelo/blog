package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllEnabled();
    User findById(Long id);
    User save(User user);
    void logicalDeleteUser(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
