package com.ssp.travel.test;

import com.ssp.travel.TravelApplication;
import com.ssp.travel.entity.User;
import com.ssp.travel.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("sun");
        user.setPassword("123");
        user.setEmail("123@qq.com");
        userService.register(user);
    }
}
