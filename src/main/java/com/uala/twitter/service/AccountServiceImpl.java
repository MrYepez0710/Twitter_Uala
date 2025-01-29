package com.uala.twitter.service;

import com.uala.twitter.db.Account;
import com.uala.twitter.interfaces.IAccount;
import com.uala.twitter.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccount {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean makeTweet(Account tweet){
        tweet.setDateTweet(LocalDateTime.now());
        Account response = accountRepository.saveAndFlush(tweet);
        return response.getId() != null;
    }

    @Override
    public List<Account> getTweets(Integer id) {
        return accountRepository.getTweets(id);
    }


}
