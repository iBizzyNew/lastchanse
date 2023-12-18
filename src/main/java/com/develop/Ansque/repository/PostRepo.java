package com.develop.Ansque.repository;

import com.develop.Ansque.entity.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {

    Collection<PostEntity> findAllByOrderByCreationDateDesc();

    Optional<PostEntity> findById(Long id);
}