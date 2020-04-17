package com.ssp.travel.test;

import com.ssp.travel.TravelApplication;
import com.ssp.travel.entity.Province;
import com.ssp.travel.entity.User;
import com.ssp.travel.service.ProvinceService;
import com.ssp.travel.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class TestProvinceService {

    @Autowired
    private ProvinceService provinceService;

    @Test
    public void testFindByPage(){
        List<Province> list = provinceService.findByPage(1, 5);
        list.forEach(pr->{
            System.out.println(pr);
        });
    }

}
