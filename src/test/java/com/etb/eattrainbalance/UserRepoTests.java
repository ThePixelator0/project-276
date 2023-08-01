package com.etb.eattrainbalance;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.etb.eattrainbalance.persistence.repository.UserRepository;
import com.etb.eattrainbalance.persistence.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTests {
    @Autowired
    private UserRepository userRepo;

    @Test
    public void UserRepo_SaveAll_ReturnSavedUsers() {
        User user = User.builder()
            .email("TestUser@email.com")
            .password("TestPassword")
            .name("testName").build();

        User savedUsers = userRepo.save(user);

        Assertions.assertThat(savedUsers).isNotNull();
    }

    @Test
    public void UserRepo_GetAll_ReturnMoreThanOneUser() {
        User user1 = User.builder()
            .email("TestUser@email.com")
            .password("TestPassword")
            .name("testName ").build();
        User user2 = User.builder()
            .email("AnotherTestUser@email.com")
            .password("AnotherTestPassword")
            .name("anotherTestName").build();

        userRepo.save(user1);
        userRepo.save(user2);

        List<User> userList = userRepo.findAll();

        Assertions.assertThat(userList).isNotNull();
    }

    @Test
    public void UserRepo_FindById_ReturnUser() {
        User user1 = User.builder()
            .email("TestUser@email.com")
            .password("TestPassword")
            .name("testName ").build();

        userRepo.save(user1);

        User userList = userRepo.findById(user1.getId()).get();

        Assertions.assertThat(userList).isNotNull();
    }

    @Test
    public void UserRepo_FindByEmail_ReturnUser() {
        User user1 = User.builder()
            .email("TestUser@email.com")
            .password("TestPassword")
            .name("testName ").build();

        userRepo.save(user1);

        User userList = userRepo.findByEmail(user1.getEmail()).get();

        Assertions.assertThat(userList).isNotNull();
    }
}