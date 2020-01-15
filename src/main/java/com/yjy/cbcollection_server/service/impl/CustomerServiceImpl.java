package com.yjy.cbcollection_server.service.impl;

import com.yjy.cbcollection_server.dao.CustomerMapper;
import com.yjy.cbcollection_server.pojo.Customer;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.service.CustomerService;
import com.yjy.cbcollection_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    UserService userService;
    @Override
    public List<Customer> findAll(String userName) {
        User user = userService.findUserByName(userName);

        return customerMapper.findAll(user.getId());
    }
}
