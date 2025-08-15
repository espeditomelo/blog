package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
