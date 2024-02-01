package com.kelly.demoTest.data.repository;

import com.kelly.demoTest.data.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabaseTest {

    @Autowired
    UserRepository userRepository;
    static User user;
    User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setFirstName("A");
        testUser.setLastName("B");
    }

    @Test
    @Order(1)
    void createUser() {

        user = userRepository.save(testUser);

        assertNotNull(userRepository.findById(user.getId()).get().getFirstName());

    }


}