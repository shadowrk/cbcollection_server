package com.yjy.cbcollection_server.service;


import com.yjy.cbcollection_server.pojo.StrategyNameList;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.pojo.UserStrategy;
import com.yjy.cbcollection_server.pojo.GlobalStrategy;

import java.util.List;

public interface StrategyService {
    //查询全部全局策略
    List<GlobalStrategy> findGlobalStrategy();
    //添加一个全局策略
    void insertGlobalStrategy(GlobalStrategy globalStrategy);
    //查找用户策略列表
    List<String> findUserStrategyList(User user);
    //查找用户当前使用的策略信息
    UserStrategy findStrategyInfo(User user);
    //根据用户名和策略名查询策略是否存在
    boolean StrategyNameIsExist(String userName, String strategyName);
    //用户添加新策略
    boolean insertUserStrategy(UserStrategy userStrategy);
    //根据策略名查询用户的策略详情
    UserStrategy findHistoryStrategy(String userName, String strategyName);
}
