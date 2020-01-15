package com.yjy.cbcollection_server.service;

import com.yjy.cbcollection_server.pojo.User;

public interface UserService {
    User findUserById(Integer id);
    User findUserByNameAndPwd(String name, String pwd);
    User findUserByName(String name);
}
