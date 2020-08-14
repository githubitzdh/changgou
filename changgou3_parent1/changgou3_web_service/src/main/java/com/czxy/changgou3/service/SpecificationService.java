package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.SpecificationMapper;
import com.czxy.changgou3.mapper.SpecificationOptionMapper;
import com.czxy.changgou3.pojo.Specification;
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
public class SpecificationService {
@Resource
    private SpecificationMapper specificationMapper;
@Resource
    private SpecificationOptionMapper specificationOptionMapper;


    /**
     * 查询指定分类的所有规格
     * @param categoryId
     * @return
     */
    public List<Specification> specificationService(Integer categoryId) {
    List<Specification>list=specificationMapper.findall(categoryId);
    return  list;
    }
}
