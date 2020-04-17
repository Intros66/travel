package com.ssp.travel.service.impl;

import com.ssp.travel.dao.ProvinceDAO;
import com.ssp.travel.dao.UserDAO;
import com.ssp.travel.entity.Province;
import com.ssp.travel.entity.User;
import com.ssp.travel.service.ProvinceService;
import com.ssp.travel.service.UserService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page-1) * rows;
        return provinceDAO.findByPage(start,rows);
    }

    @Override
    public Integer findTotals() {
        return provinceDAO.findTotals();
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0);//景点个数
        provinceDAO.save(province);
    }
}
