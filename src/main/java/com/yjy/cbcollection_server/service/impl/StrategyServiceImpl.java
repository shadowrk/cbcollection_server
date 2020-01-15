package com.yjy.cbcollection_server.service.impl;

import com.yjy.cbcollection_server.dao.strategy.StrategyDao;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.pojo.UserStrategy;
import com.yjy.cbcollection_server.pojo.GlobalStrategy;
import com.yjy.cbcollection_server.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    StrategyDao strategyDao;
    @Override
    public List<GlobalStrategy> findGlobalStrategy() {
        return strategyDao.findGlobalStrategy();
    }

    @Override
    public void insertGlobalStrategy(GlobalStrategy globalStrategy) {
        strategyDao.insertGlobalStrategy(globalStrategy);
    }


    @Override
    public List<String> findUserStrategyList(User user) {
        List<UserStrategy> users = strategyDao.findStrategyList(user);
        List<String> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(UserStrategy strategy: users){
            list.add(strategy.getStrategy().getName()+"-"+format.format(strategy.getStrategy().getCreateDate()));
        }
        return list;
    }

    @Override
    public UserStrategy findStrategyInfo(User user) {
        return strategyDao.findStrategyInfo(user);
    }

    @Override
    public boolean StrategyNameIsExist(String userName, String strategyName) {
        UserStrategy userStrategy = strategyDao.findStrategyByuserAndName(userName, strategyName);
        if(userStrategy == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean insertUserStrategy(UserStrategy userStrategy) {
        try{
            strategyDao.insertNewUserStrategy(userStrategy);
        }catch (Exception e){
            throw new RuntimeException("策略插入失败");
        }

        return false;
    }

    @Override
    public UserStrategy findHistoryStrategy(String userName, String strategyName) {
        return strategyDao.findHistoryStrategy(userName, strategyName);
    }
}
