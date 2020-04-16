package com.ssp.travel.service.impl;

import com.ssp.travel.dao.UserDAO;
import com.ssp.travel.entity.User;
import com.ssp.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        if (userDAO.findByUsername(user.getUsername())==null){
            userDAO.save(user);
        }else {
            throw new RuntimeException("用户名已存在");
        }

    }

    @Override
    public User login(User user) {
        User byUsername = userDAO.findByUsername(user.getUsername());
        if (byUsername != null){
            if (byUsername.getPassword().equals(user.getPassword())){
                return byUsername;
            }
            throw new RuntimeException("密码错误");
        }else {
            throw  new RuntimeException("用户名错误");
        }
    }
}
