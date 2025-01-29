package com.uala.twitter.controller;

import com.uala.twitter.db.Account;
import com.uala.twitter.interfaces.IAccount;
import com.uala.twitter.interfaces.IFollow;
import com.uala.twitter.utils.ResponseValidationsDTO;
import com.uala.twitter.utils.ValidationsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAccount mockIAccount;

    @MockBean
    private IFollow mockIFollow;

    @MockBean
    private ValidationsUtils validationsUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    //@Test
    void testMakeTweetValidTweet() throws Exception {
        Account validTweet = new Account();
        validTweet.setId(1);

        ResponseValidationsDTO response = new ResponseValidationsDTO(true, "Valid tweet");

        Mockito.when(validationsUtils.validateTweetInformation(any(Account.class))).thenReturn(response);
        Mockito.when(mockIAccount.makeTweet(any(Account.class))).thenReturn(true);

        mockMvc.perform(post("/account/makeTweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Your tweet has been published"));
    }

    @Test
    void testMakeTweetInvalidTweet() throws Exception {
        Account invalidTweet = new Account();
        invalidTweet.setId(1);

        ResponseValidationsDTO response = new ResponseValidationsDTO(false, "Tweet is empty");

        Mockito.when(validationsUtils.validateTweetInformation(any(Account.class))).thenReturn(response);

        mockMvc.perform(post("/account/makeTweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}")
                )
                .andExpect(status().isAccepted())
                .andExpect(content().string("Tweet is empty"));
    }
}