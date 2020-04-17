package com.ssp.travel.service;

import com.ssp.travel.entity.Province;

import java.util.List;

public interface ProvinceService {

    //当前页，每页显示记录数
    List<Province> findByPage(Integer page, Integer rows);

    //查询总条数
    Integer findTotals();

    //保存省份
    void save(Province province);
}
