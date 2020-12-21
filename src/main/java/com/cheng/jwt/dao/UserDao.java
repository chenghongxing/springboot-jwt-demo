package com.cheng.jwt.dao;

import com.cheng.jwt.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by chenghx on 2020/12/21 11:28
 */
@Mapper
public interface UserDao {
    User getUserByUserInfo(User user);
}
