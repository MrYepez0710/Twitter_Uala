package com.uala.twitter.service;

import com.uala.twitter.db.Follow;
import com.uala.twitter.db.Users;
import com.uala.twitter.interfaces.IFollow;
import com.uala.twitter.interfaces.IUsers;
import com.uala.twitter.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements IFollow {

    @Autowired
    private IUsers iUsers;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean followUser(Integer id, String nicknameUser) {

        Users followUser = iUsers.getUserByNickname(nicknameUser);

        if(followUser != null){
            Follow newFollow = new Follow(id, followUser.getId(), followUser.getNickname(), true);
            followRepository.saveAndFlush(newFollow);
            return true;
        }
        return false;
    }

    @Override
    public boolean unfollowUser(Integer id, String nicknameUser) {

        Follow followUser = followRepository.findFollowByUserIdAndFollowingNickname(id, nicknameUser);

        if(followUser != null){
            followUser.setState(false);
            followRepository.saveAndFlush(followUser);
            return true;
        }
        return false;
    }

    @Override
    public Integer getFollowers(Integer id) {
        return followRepository.countFollowersById(id);
    }

    @Override
    public Integer getFollowings(Integer id) {
        return followRepository.countFollowingsByUserId(id);
    }
}
