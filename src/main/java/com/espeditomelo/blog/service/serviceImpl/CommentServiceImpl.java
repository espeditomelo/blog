package com.espeditomelo.blog.service.serviceImpl;

import com.espeditomelo.blog.model.Comment;
import com.espeditomelo.blog.model.repository.CommentRepository;
import com.espeditomelo.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

}
