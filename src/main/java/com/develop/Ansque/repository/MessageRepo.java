package com.develop.Ansque.repository;

import com.develop.Ansque.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<MessageEntity, Long> {
}
