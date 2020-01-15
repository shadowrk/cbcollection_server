package com.yjy.cbcollection_server.controller;

import com.yjy.cbcollection_server.dto.LoginResponse;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.service.UserService;
import com.yjy.cbcollection_server.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("/user/login")
    public LoginResponse login(@RequestBody User user) {
        LoginResponse loginResponse = new LoginResponse();
        User userForBase = userService.findUserByNameAndPwd(user.getName(), user.getPwd());
        if(userForBase == null){
            loginResponse.setCode(0);
            loginResponse.setMessage("用户不存在，请重新登录");

        }else{
            if(!userForBase.getPwd().equals(user.getPwd())){
                loginResponse.setCode(0);
                loginResponse.setMessage("登录失败，密码错误");

            }else{
                String token = tokenUtil.getToken(userForBase);
                loginResponse.setCode(1);
                loginResponse.setMessage("登录成功");
                loginResponse.setToken(token);
            }
        }
        return loginResponse;
    }



}
