package com.yjy.cbcollection_server.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "strategies")
public class StrategyNameList {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
