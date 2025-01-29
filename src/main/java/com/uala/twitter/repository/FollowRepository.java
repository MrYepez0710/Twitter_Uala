package com.uala.twitter.repository;

import com.uala.twitter.db.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository  extends JpaRepository<Follow, Integer> {

    @Query("SELECT f FROM Follow f WHERE f.userId = ?1 AND f.followingNickname = ?2")
    public Follow findFollowByUserIdAndFollowingNickname(Integer userId, String followingNickname);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.userId = ?1 AND f.state = true")
    public Integer countFollowingsByUserId(Integer userId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followingId = ?1 AND f.state = true")
    public Integer countFollowersById(Integer id);
}
