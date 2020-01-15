package com.yjy.cbcollection_server.dao;

import com.yjy.cbcollection_server.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> findAll(Integer u_id);
}
