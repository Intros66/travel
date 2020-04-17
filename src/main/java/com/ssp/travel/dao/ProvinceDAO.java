package com.ssp.travel.dao;

import com.ssp.travel.entity.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceDAO extends BaseDAO<Province,String> {
}
