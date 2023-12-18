package com.develop.Ansque.service;

import com.develop.Ansque.entity.MessageEntity;

public interface MessageService {

    MessageEntity save(MessageEntity message);

    void delete(MessageEntity message);

}