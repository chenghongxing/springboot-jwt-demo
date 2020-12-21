package com.cheng.jwt.serivce;

import com.cheng.jwt.entity.User;

/**
 * Created by chenghx on 2020/12/21 11:32
 */
public interface UserService {
    User login(User user);
}
