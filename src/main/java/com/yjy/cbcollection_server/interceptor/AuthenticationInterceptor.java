package com.yjy.cbcollection_server.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yjy.cbcollection_server.interfaceutil.PassToken;
import com.yjy.cbcollection_server.interfaceutil.UserLoginToken;
import com.yjy.cbcollection_server.pojo.User;
import com.yjy.cbcollection_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
        String token = request.getHeader("Authorization");

        //如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod))
            return true;

        HandlerMethod handlerMethod = (HandlerMethod)  object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，如果有，则直接跳过认证
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required())
                return true;
        }

        //检查有没有需要用户权限的注释
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                //执行认证
                if(token == null){
                    throw new RuntimeException("无token,请重新登录");
                }
                //获取token中的user id
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (JWTDecodeException j){
                    throw new RuntimeException("401");
                }
                User user = userService.findUserById(Integer.valueOf(userId));
                if(user == null)
                    throw new RuntimeException("用户不存在，请重新登录");
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
                try{
                    jwtVerifier.verify(token);

                }catch (JWTVerificationException j){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
