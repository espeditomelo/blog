package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    List<Category> findAllByNameAsc();
}
