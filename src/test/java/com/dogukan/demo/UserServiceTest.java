package com.dogukan.demo;

import com.dogukan.demo.entity.User;
import com.dogukan.demo.repository.UserRepository;
import com.dogukan.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUserWithException() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        assertThrows(RuntimeException.class, () -> userService.saveUserWithException(user));

        // Verify that the user was not saved
        assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void testSaveUserWithoutRollback() {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");

        assertThrows(RuntimeException.class, () -> userService.saveUserWithoutRollback(user));

        // Verify that the user was saved despite the exception
        assertEquals(1, userRepository.findAll().size());
    }
}
