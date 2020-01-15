package com.yjy.cbcollection_server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "global")
public class GlobalStrategy {
    //策略名称
    private String name;
    //TnR, T-R, R-T, P的集合顺序及每个集合对应的人数
    private Map<String, Integer> collectionOrder;
    //TnR顺序及比例
    private Map<String, Double> tAndROrder;
    //T-R顺序
    private List<String> tReduceROrder;
    //R-T顺序
    private RReduceT rReduceTOrder;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    //当前策略的使用状态, 1:正在使用；0:未使用
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getCollectionOrder() {
        return collectionOrder;
    }

    public void setCollectionOrder(Map<String, Integer> collectionOrder) {
        this.collectionOrder = collectionOrder;
    }

    public Map<String, Double> gettAndROrder() {
        return tAndROrder;
    }

    public void settAndROrder(Map<String, Double> tAndROrder) {
        this.tAndROrder = tAndROrder;
    }

    public List<String> gettReduceROrder() {
        return tReduceROrder;
    }

    public void settReduceROrder(List<String> tReduceROrder) {
        this.tReduceROrder = tReduceROrder;
    }

    public RReduceT getrReduceTOrder() {
        return rReduceTOrder;
    }

    public void setrReduceTOrder(RReduceT rReduceTOrder) {
        this.rReduceTOrder = rReduceTOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
