package com.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.business.entities.User;
import com.business.repositories.UserRepository;
import com.business.services.UserService;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testUserInstance() {
        User newUser = new User();

        assertInstanceOf(User.class, newUser);
    }

    @Test
    void testStringName() {
        String stringName = "aaa";

        assertEquals("aaa", stringName);
    }
    
}
