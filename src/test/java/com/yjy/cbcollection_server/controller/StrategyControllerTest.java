package com.yjy.cbcollection_server.controller;

import com.yjy.cbcollection_server.dao.strategy.StrategyDao;
import com.yjy.cbcollection_server.pojo.GlobalStrategy;
import com.yjy.cbcollection_server.pojo.RReduceT;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.pojo.UserStrategy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StrategyControllerTest {
    @Autowired
    StrategyController strategyController;
    @Autowired
    StrategyDao strategyDao;

    @Test
    void insertUserStrategy() throws ParseException {

        UserStrategy userStrategy = new UserStrategy();
        userStrategy.setUserName("催收员1号");
        userStrategy.setUserId(1);
        GlobalStrategy strategy = new GlobalStrategy();
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("TnR", 6);
        map.put("R-T", 5);
        map.put("P", 3);
        map.put("T-R", 1);
        strategy.setCollectionOrder(map);
        Map<String, Double> map1 = new LinkedHashMap<>();
        map1.put("通话记录", 0.3);
        map1.put("通讯录", 0.7);
        strategy.settAndROrder(map1);
        List<String> list = new ArrayList<>();
        list.add("朋友");
        list.add("同事");
        list.add("母亲");
        list.add("父亲");
        list.add("儿子");
        list.add("妻子");
        list.add("女儿");

        strategy.settReduceROrder(list);
        RReduceT rReduceT = new RReduceT();
        rReduceT.setArea("同市");
        rReduceT.setOrder(new ArrayList<String>(){
            {
                add("频次");
                add("时长");
            }
        });
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(str);

        rReduceT.setStartDate(formatter.parse("2019-09-10 00:00:00"));
        rReduceT.setEndDate(new Date());
        strategy.setrReduceTOrder(rReduceT);
        strategy.setCreateDate(new Date());
        strategy.setName("策略一");
        userStrategy.setStrategy(strategy);
        userStrategy.setStatus(0);
        strategyController.insertUserStrategy(userStrategy);
    }
}