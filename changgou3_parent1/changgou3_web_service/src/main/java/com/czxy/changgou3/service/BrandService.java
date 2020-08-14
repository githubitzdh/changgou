package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.BrandMapper;
import com.czxy.changgou3.pojo.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@Service
@Transactional
public class BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 查询所有
     *
     * @param categoryId
     * @return
     */
    public List<Brand> findall(Integer categoryId) {
        //查询
        List<Brand> blist = brandMapper.findAll(categoryId);
        //返回
        return blist;
    }
}
