package com.develop.Ansque.service.impl;

import com.develop.Ansque.entity.PostEntity;
import com.develop.Ansque.repository.PostRepo;
import com.develop.Ansque.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Override
    public Optional<PostEntity> getById(Long id) {
        return postRepo.findById(id);
    }

    @Override
    public Collection<PostEntity> getAll() {
        return postRepo.findAllByOrderByCreationDateDesc();
    }

    @Override
    public PostEntity save(PostEntity post) {
        return postRepo.saveAndFlush(post);
    }

    @Override
    public void delete(PostEntity post) {
        postRepo.delete(post);
    }
}
