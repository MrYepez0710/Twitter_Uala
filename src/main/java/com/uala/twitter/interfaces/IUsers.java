package com.uala.twitter.interfaces;

import com.uala.twitter.db.Users;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUsers {

    public Users createUser(Users user);

    public Users getUserById(Integer id);

    public Users getUserByNickname(String nickname);

    public List<Users> getUsers();
}
