package com.develop.Ansque.service;


import com.develop.Ansque.entity.UserEntity;

import java.security.Principal;

public interface UserService  {

    UserEntity findByEmail(String email);
    UserEntity findById(Long id);
    UserEntity saveUser(UserEntity user);
    void deleteUser(Long id);

    UserEntity registerUser(UserEntity user);

    UserEntity getUserByPrincipal(Principal principal);
}
