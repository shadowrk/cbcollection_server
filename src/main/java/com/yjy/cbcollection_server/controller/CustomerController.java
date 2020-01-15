package com.yjy.cbcollection_server.controller;

import com.yjy.cbcollection_server.interfaceutil.UserLoginToken;
import com.yjy.cbcollection_server.pojo.Customer;
import com.yjy.cbcollection_server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @UserLoginToken
    @GetMapping("/customers")
    List<Customer> getAllCustomer( String userName){
        return customerService.findAll(userName);
    }


}
