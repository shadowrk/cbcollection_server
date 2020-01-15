package com.yjy.cbcollection_server.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "strategies")
public class UserStrategy {

    private String userName;
    private Integer userId;
    private GlobalStrategy strategy;
    //用户策略的使用状态, 1:正在使用; 0:未使用
    private Integer status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GlobalStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(GlobalStrategy strategy) {
        this.strategy = strategy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
