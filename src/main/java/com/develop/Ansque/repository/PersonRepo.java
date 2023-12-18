package com.develop.Ansque.repository;

import com.develop.Ansque.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonEntity, Long> { //во дура, надо было UserEntity, а не UserRepo

}
