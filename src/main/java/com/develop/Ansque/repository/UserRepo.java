package com.develop.Ansque.repository;

import com.develop.Ansque.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> { //во дура, надо было UserEntity, а не UserRepo
    UserEntity findByPassword(String password);
    UserEntity findByEmail(String email);
    Optional<UserEntity> findById(Long id);



}
