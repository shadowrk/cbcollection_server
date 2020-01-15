package com.yjy.cbcollection_server.service.impl;

import com.yjy.cbcollection_server.dao.UserMapper;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByNameAndPwd(String name, String pwd) {
        return userMapper.findUserByNameAndPwd(name, pwd);
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

}
