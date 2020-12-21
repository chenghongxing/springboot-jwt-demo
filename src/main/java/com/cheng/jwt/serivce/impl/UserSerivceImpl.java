package com.cheng.jwt.serivce.impl;

import com.cheng.jwt.dao.UserDao;
import com.cheng.jwt.entity.User;
import com.cheng.jwt.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenghx on 2020/12/21 11:34
 */
@Service
public class UserSerivceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {
        User userInfo = userDao.getUserByUserInfo(user);
        if (null == userInfo){
            throw new RuntimeException("认证失败");
        }
        return userInfo;
    }
}
