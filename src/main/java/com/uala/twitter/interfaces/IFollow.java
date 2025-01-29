package com.uala.twitter.interfaces;

public interface IFollow {

    public boolean followUser(Integer id, String nicknameUser);

    public boolean unfollowUser(Integer id, String nicknameUser);

    public Integer getFollowers(Integer id);

    public Integer getFollowings(Integer id);
}
