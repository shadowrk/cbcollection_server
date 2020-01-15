package com.yjy.cbcollection_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjy.cbcollection_server.dao")
public class CbcollectionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbcollectionServerApplication.class, args);
    }

}
