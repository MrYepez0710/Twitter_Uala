package com.uala.twitter.repository;

import com.uala.twitter.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.id = ?1")
    public Users findByUserId(Integer id);

    @Query("SELECT u FROM Users u WHERE u.nickname = ?1")
    public Users findByNickname(String nickname);
}
