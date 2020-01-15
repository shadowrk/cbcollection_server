package com.yjy.cbcollection_server.controller;

import com.yjy.cbcollection_server.dto.AddStrategyResponse;
import com.yjy.cbcollection_server.dto.SearchStrategyNameReponse;
import com.yjy.cbcollection_server.interfaceutil.UserLoginToken;
import com.yjy.cbcollection_server.pojo.GlobalStrategy;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.pojo.UserStrategy;
import com.yjy.cbcollection_server.service.StrategyService;
import com.yjy.cbcollection_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    StrategyService strategyService;
    @Autowired
    UserService userService;
    @UserLoginToken
    @PostMapping ("/global")
    public void insertGlobalStrategy(GlobalStrategy globalStrategy){
        strategyService.insertGlobalStrategy(globalStrategy);
    }
    @UserLoginToken
    @GetMapping("/global")
    public List<GlobalStrategy> getGlobalStrategy(){
        return strategyService.findGlobalStrategy();

    }
    @UserLoginToken
    @PostMapping("/user")
    public void insertUserStrategy(UserStrategy userStrategy){
        strategyService.insertUserStrategy(userStrategy);
    }
    @UserLoginToken
    @GetMapping("/strategylist")
    public List<String> getStrategyList(String name){
        User user = userService.findUserByName(name);
        List<String> userStrategyList = strategyService.findUserStrategyList(user);
        Collections.reverse(userStrategyList);
        return userStrategyList;
    }
    @UserLoginToken
    @GetMapping("/info")
    public UserStrategy getUserStrategyInfo(String name){
        User user = userService.findUserByName(name);
        return strategyService.findStrategyInfo(user);
    }
    @UserLoginToken
    @GetMapping("/name")
    public SearchStrategyNameReponse strategyIsExist(String userName, String strategyName){
        SearchStrategyNameReponse searchStrategyNameReponse = new SearchStrategyNameReponse();
        if(!strategyService.StrategyNameIsExist(userName, strategyName)){
            searchStrategyNameReponse.setExist(false);
        }else{
            searchStrategyNameReponse.setExist(true);
            searchStrategyNameReponse.setMsg("策略名称已存在");
        }
        return searchStrategyNameReponse;
    }
    @UserLoginToken
    @PostMapping("/add")
    public AddStrategyResponse addNewStrategy(@RequestBody UserStrategy strategy){
        AddStrategyResponse response = new AddStrategyResponse();
        User user = userService.findUserByName(strategy.getUserName());
        strategy.setStatus(1);
        strategy.getStrategy().setCreateDate(new Date());
        strategy.setUserId(user.getId());
        try{
            strategyService.insertUserStrategy(strategy);
            response.setCode(1);
            response.setMsg("策略添加成功");
        }catch (Exception e){
            response.setCode(0);
            response.setMsg(e.getMessage());
        }

        return response;
    }
    @UserLoginToken
    @GetMapping("/history")
    public UserStrategy getHistoryStrategy(String userName, String item){
        String[] split = item.split("-");
        return strategyService.findHistoryStrategy(userName, split[0]);
    }

}
