package com.uala.twitter.service;

import com.uala.twitter.db.Users;
import com.uala.twitter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsersServicesImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersServicesImpl usersServicesImpl;

    public UsersServicesImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_successfulCreation() {
        Users userToSave = new Users("Juan", "Diaz", "juan.diaz@correo.com", "johnny.days");
        Users savedUser = new Users("Juan", "Diaz", "juan.diaz@correo.com", "johnny.days");
        savedUser.setId(1);

        when(userRepository.saveAndFlush(any(Users.class))).thenReturn(savedUser);

        Users result = usersServicesImpl.createUser(userToSave);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Juan", result.getFirstName());
        assertEquals("Diaz", result.getLastName());
        assertEquals("juan.diaz@correo.com", result.getEmail());
        assertEquals("johnny.days", result.getNickname());
    }

    @Test
    public void testCreateUser_nullNickname() {
        Users userToSave = new Users("Juan", "Diaz", "juan.diaz@correo.com", "");

        when(userRepository.saveAndFlush(userToSave)).thenThrow(new IllegalArgumentException("Nickname cannot be null"));

        try {
            usersServicesImpl.createUser(userToSave);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Nickname cannot be null", e.getMessage());
        }
    }
}