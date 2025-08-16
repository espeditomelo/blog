package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
