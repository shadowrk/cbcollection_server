package com.yjy.cbcollection_server.dao;

import com.yjy.cbcollection_server.pojo.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User findUserById(Integer id);
    User findUserByNameAndPwd(String name, String pwd);
    User findUserByName(String name);
}
