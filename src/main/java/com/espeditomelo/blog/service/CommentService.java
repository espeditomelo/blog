package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.Comment;

import java.util.List;


public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findAll();
}
