package com.yjy.cbcollection_server.dao.strategy;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.yjy.cbcollection_server.pojo.StrategyNameList;
import com.yjy.cbcollection_server.pojo.UserStrategy;
import com.yjy.cbcollection_server.pojo.GlobalStrategy;
import com.yjy.cbcollection_server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StrategyDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加全局策略
     * @param globalStrategy
     */
    public void insertGlobalStrategy(GlobalStrategy globalStrategy){
        mongoTemplate.insert(globalStrategy);
    }

    /**
     * 查询全局策略
     * @return
     */
    public List<GlobalStrategy> findGlobalStrategy(){
        return mongoTemplate.findAll(GlobalStrategy.class);
    }

    /**
     * 查找用户策略列表
     * @param user
     * @return
     */
    public List<UserStrategy> findStrategyList(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user.getName()));
        query.addCriteria(Criteria.where("userId").is(user.getId()));
        query.fields().include("strategy.name");
        query.fields().include("strategy.createDate");
        return mongoTemplate.find(query, UserStrategy.class);
    }


    /**
     * 查询用户当前使用的详细策略
     * @param user
     * @return
     */
    public UserStrategy findStrategyInfo(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user.getName()));
        query.addCriteria(Criteria.where("userId").is(user.getId()));
        query.addCriteria(Criteria.where("status").is(1));
        return mongoTemplate.findOne(query, UserStrategy.class);
    }

    /**
     * 根据用户名和策略名称查询该用户是否存在此策略
     * @param userName
     * @param strategyName
     * @return
     */
    public UserStrategy findStrategyByuserAndName(String userName, String strategyName){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("strategy.name").is(strategyName));
        return mongoTemplate.findOne(query, UserStrategy.class);
    }

    /**
     * 用户添加新策略
     * 1、将mongodb中该用户所有策略状态设置为0
     * 2、插入新策略
     * @param userStrategy
     */
    @Transactional
    public void insertNewUserStrategy(UserStrategy userStrategy){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userStrategy.getUserName()));
        query.addCriteria(Criteria.where("userId").is(userStrategy.getUserId()));
        Update update = new Update();
        update.set("status", 0);
        mongoTemplate.updateMulti(query, update, UserStrategy.class);
        mongoTemplate.insert(userStrategy);
    }

    /**
     * 根据策略名查询用户的策略详情
     * @param userName
     * @param strategyName
     * @return
     */
    public UserStrategy findHistoryStrategy(String userName, String strategyName){
        Query query = new Query();
        query.addCriteria(Criteria.where("strategy.name").is(strategyName));
        query.addCriteria(Criteria.where("userName").is(userName));
        return mongoTemplate.findOne(query, UserStrategy.class);
    }
}
