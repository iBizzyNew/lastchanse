package com.develop.Ansque.service.impl;

import com.develop.Ansque.entity.MessageEntity;
import com.develop.Ansque.repository.MessageRepo;
import com.develop.Ansque.service.MessageService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public MessageEntity save(MessageEntity message) {
        return messageRepo.saveAndFlush(message);
    }

    @Override
    public void delete(MessageEntity message) {
        messageRepo.delete(message);
    }

    @Override
    public List<MessageEntity> getAll() {
        return messageRepo.findAll();
    }

    @Override
    public MessageEntity getById(Long messageId) {
        return messageRepo.getById(messageId);
    }
}