package com.uala.twitter.repository;

import com.uala.twitter.db.Account;
import com.uala.twitter.db.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository  extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Follow f JOIN Account a ON f.followingId = a.userId WHERE f.userId = ?1 ORDER BY dateTweet DESC")
    public List<Account> getTweets(Integer userId);
}
