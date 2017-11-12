package com.ralap.chat.service;


import com.ralap.chat.domain.UserModel;
import com.ralap.chat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ralap on 2017/11/9.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserModel getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public boolean addUser(UserModel user) {
        return userMapper.insertUser(user);
    }
}
