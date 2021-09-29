package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
