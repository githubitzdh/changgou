package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.CategorysMapper;
import com.czxy.changgou3.pojo.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/2 0002
 **/
@Service
@Transactional
public class CategoryService {
    @Resource
    private CategorysMapper categorysMapper;

    /**
     * 查询所有分类(一级=>二级分类=>三级分类)
     *
     * @return
     */
    public List<Category> findAll() {
        //1.查询所有(安排父分类id排序)先查询到一级分类
        Example example = new Example(Category.class);
        example.setOrderByClause("parent_id asc");
        List<Category> list = categorysMapper.selectByExample(example);

        //2.组装数据
        //2 处理分类，将所有字符串添加到父分类List集合中
        //2.1,提供新集合,组装后的数据
        // 2.1 处理后的所有数据
        List<Category> retrunList = new ArrayList<>();
        //2.2.提供Map进行缓存,子分类可以快速找到父分类,,用于存父分类
        HashMap<Integer, Category> map = new HashMap<>();

        //2.3 组装
        // 2.3 遍历数据
        for (Category category : list) {
            //如果父分类id为0, 表示一级分类
            // 1) 将所有一级分类添加集合中
            if (category.getParentId() == 0) {
                //添加进新集合
                retrunList.add(category);
            }

            //将自己添加到map缓存中,方便子分类找到自己
            // 2) 子分类可以找到一级分类，保存一级分类
            map.put(category.getId(), category);
            //子分类,从缓存map获得父分类
          //  子分类获得父分类，将子分类添加到父分类集合中
            Category parentCategory = map.get(category.getParentId());
            if (parentCategory != null) {
                parentCategory.getChildren().add(category);
            }

        }
        //3.返回
        return retrunList;


    }
}
