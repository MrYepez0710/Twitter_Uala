package com.uala.twitter.service;

import com.uala.twitter.db.Account;
import com.uala.twitter.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testMakeTweet_Success() {
        Account tweet = new Account();
        tweet.setId(null);
        tweet.setDateTweet(null);

        Account savedTweet = new Account();
        savedTweet.setId(1);
        savedTweet.setDateTweet(LocalDateTime.now());

        when(accountRepository.saveAndFlush(any(Account.class))).thenReturn(savedTweet);

        boolean result = accountService.makeTweet(tweet);

        assertTrue(result);
    }

}