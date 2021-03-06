package com.ssp.travel.dao;

import com.ssp.travel.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    //根据用户名查询用户
    User findByUsername(String username);

    //注册用户
    void save(User user);

}
