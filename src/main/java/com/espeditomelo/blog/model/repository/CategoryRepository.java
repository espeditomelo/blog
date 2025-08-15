package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
