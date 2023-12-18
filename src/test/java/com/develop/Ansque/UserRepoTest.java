package com.develop.Ansque;

import com.develop.Ansque.entity.PersonEntity;
import com.develop.Ansque.entity.UserEntity;
import com.develop.Ansque.repository.PersonRepo;
import com.develop.Ansque.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(true);
        userEntity.setEmail("test@mail.ru");
        userEntity.setPassword("testPassword");

        UserEntity savedUser = userRepo.save(userEntity);

        UserEntity existUser = entityManager.find(UserEntity.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(userEntity.getEmail());
    }
}

