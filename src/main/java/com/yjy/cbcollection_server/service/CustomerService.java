package com.yjy.cbcollection_server.service;

import com.yjy.cbcollection_server.pojo.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    /**
     * 获取全部客户名称
     * @return
     */
    List<Customer> findAll(String userName);
}
