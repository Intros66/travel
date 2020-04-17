package com.ssp.travel.controller;

import com.alibaba.fastjson.JSON;
import com.ssp.travel.entity.Province;
import com.ssp.travel.entity.Result;
import com.ssp.travel.service.ProvinceService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 保存省份信息
     * @param province
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("保存省份信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("保存失败");
        }

        return result;
    }

    /**
     * 查全
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows){
        page = page==null?1:page;
        rows = rows==null?4:rows;
        HashMap<String,Object> map = new HashMap<>();
        //分页处理
        List<Province> provinces = provinceService.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals%rows ==0 ? totals/rows :totals/rows+1;
        map.put("province",provinces);
        map.put("totals",totals);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }
}
