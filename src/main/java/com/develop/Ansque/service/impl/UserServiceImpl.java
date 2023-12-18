package com.develop.Ansque.service.impl;

import com.develop.Ansque.entity.Enum.Role;
import com.develop.Ansque.entity.UserEntity;
import com.develop.Ansque.repository.UserRepo;
import com.develop.Ansque.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


    @Override
    public UserEntity registerUser(UserEntity user) {
        // Реализация регистрации пользователя
        // Это может включать в себя проверку уникальности email, хеширование пароля и сохранение в базе данных

        // Проверка уникальности email
//        if (userRepo.findByEmail(user.getEmail()) != null) {
//            throw new RuntimeException("Пользователь с таким email уже существует");
//        }

        // Здесь вы можете добавить логику для хеширования пароля, например, с использованием BCryptPasswordEncoder
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String encodedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(encodedPassword);
        user.setActive(true);
        System.err.println("Email: " + user.getEmail());
        System.err.println("Пароль: " + user.getPassword());
        // Сохранение пользователя
        UserEntity savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public UserEntity getUserByPrincipal(Principal principal) {
        // Реализация получения пользователя по Principal
        // Это может включать в себя получение пользователя из базы данных по email
        return userRepo.findByEmail(principal.getName());
    }

}
