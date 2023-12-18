package com.develop.Ansque.service;

import com.develop.Ansque.entity.MessageEntity;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    MessageEntity save(MessageEntity message);

    void delete(MessageEntity message);

    List<MessageEntity>  getAll();

    MessageEntity getById(Long messageId);
}