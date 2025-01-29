package com.uala.twitter.service;

import com.uala.twitter.repository.UserRepository;
import com.uala.twitter.db.Users;
import com.uala.twitter.interfaces.IUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServicesImpl implements IUsers {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users createUser(Users user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Users getUserById(Integer id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public Users getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
