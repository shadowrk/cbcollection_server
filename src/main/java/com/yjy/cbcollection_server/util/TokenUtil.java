package com.yjy.cbcollection_server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yjy.cbcollection_server.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    public String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId())).sign(Algorithm.HMAC256(user.getPwd()));
        return token;
    }
}
