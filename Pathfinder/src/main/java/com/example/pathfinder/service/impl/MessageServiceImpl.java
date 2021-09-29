package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.MessageRepository;
import com.example.pathfinder.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
