package com.develop.Ansque.service;

import com.develop.Ansque.entity.PostEntity;

import java.util.Collection;
import java.util.Optional;

public interface PostService {

    Optional<PostEntity> getById(Long id);

    Collection<PostEntity> getAll();

    PostEntity save(PostEntity post);

    void delete(PostEntity post);
}