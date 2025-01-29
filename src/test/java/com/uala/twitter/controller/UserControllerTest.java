package com.uala.twitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.twitter.db.Users;
import com.uala.twitter.interfaces.IUsers;
import com.uala.twitter.utils.ResponseValidationsDTO;
import com.uala.twitter.utils.ValidationsUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUsers iUsers;

    @MockBean
    private ValidationsUtils validationsUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUserWithValidData() throws Exception {
        Users user = new Users("John", "Doe", "john.doe@example.com", "johndoe");
        ResponseValidationsDTO validationResponse = new ResponseValidationsDTO(true, "Valid data");

        when(validationsUtils.validateUserData(any(Users.class))).thenReturn(validationResponse);
        when(iUsers.createUser(any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    void testCreateUserWithInvalidData() throws Exception {
        Users invalidUser = new Users("", "Doe", "invalid-email", "");
        ResponseValidationsDTO validationResponse = new ResponseValidationsDTO(false, "Email is invalid");

        when(validationsUtils.validateUserData(any(Users.class))).thenReturn(validationResponse);

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Email is invalid"));
    }
}