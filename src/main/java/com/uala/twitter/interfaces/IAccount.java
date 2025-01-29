package com.uala.twitter.interfaces;

import com.uala.twitter.db.Account;

import java.util.List;

public interface IAccount {

    public boolean makeTweet(Account tweet);

    public List<Account> getTweets(Integer id);

}
