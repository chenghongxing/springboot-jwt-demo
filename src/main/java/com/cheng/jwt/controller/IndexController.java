package com.cheng.jwt.controller;

import com.cheng.jwt.entity.User;
import com.cheng.jwt.serivce.UserService;
import com.cheng.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenghx on 2020/12/21 11:36
 */
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Object login(User user){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            User userInfo = userService.login(user);
            Map<String,String> payload = new HashMap<>();
            payload.put("userid",userInfo.getUserid());
            payload.put("username",userInfo.getUsername());
            String token = JwtUtil.getToken(payload);
            resultMap.put("state",true);
            resultMap.put("msg","认证成功");
            resultMap.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("state",false);
            resultMap.put("msg",e.getMessage());
        }
        return resultMap;
    }

    @PostMapping("/user/test")
    public Object test(){
        Map<String,Object> resultMap = new HashMap<>();
        //处理业务逻辑
        resultMap.put("state",true);
        resultMap.put("msg","请求成功！");
        return resultMap;
    }

}
