package com.czxy.changgou3.service;

import com.czxy.changgou3.repository.SkuRepository;
import com.czxy.changgou3.vo.ReturnSku;
import com.czxy.changgou3.vo.SearchSku;
import com.czxy.changgou3.vo.SearchVo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@Service
public class SkuSearchService {
    @Resource
    private SkuRepository skuRepository;


    public Map search(SearchVo searchVo) {
        //1.拼凑多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //1.1分类
        boolQueryBuilder.must(QueryBuilders.termQuery("catId", searchVo.getCatId()));

        //1.2 品牌
        if (searchVo.getBrandId() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("brandId", searchVo.getBrandId()));
        }

        //1.3区间, 价格
        if (searchVo.getMinPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(searchVo.getMinPrice()));
        }
        if (searchVo.getMaxPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(searchVo.getMaxPrice()));
        }

        //1.4关键字查询
        if (StringUtils.isNotBlank(searchVo.getKeyword())) {
            //es库,存在all列,存放数据格式:       sku名称 + 规格 + 品牌名称
            boolQueryBuilder.must(QueryBuilders.matchQuery("all", searchVo.getKeyword()));


        }
        //1.5规格查询
        if (searchVo.getSpecList() != null) {
            for (String key : searchVo.getSpecList().keySet()) {
                //获得对应key的value
                String value = searchVo.getSpecList().get(key);
                boolQueryBuilder.must(QueryBuilders.termQuery("specs." + key + ".keyword", value));


            }
        }


        //2.条件查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2.1设置查询条件
        queryBuilder.withQuery(boolQueryBuilder);
        //2.2分页(从0开始)
        queryBuilder.withPageable(PageRequest.of(searchVo.getPageNum() - 1, searchVo.getPageSize()));
        //2.3排序
        //1)提供map,存放"前端标识"和"es列"
        HashMap<String, String> cacheMap = new HashMap<>();
        cacheMap.put("xl", "sellerCount");  //销量
        cacheMap.put("jg", "price");       //价格
        cacheMap.put("pl", "commentCount");  //评论
        cacheMap.put("sj", "onSaleTime");      //时间
        //2)通过sortBy获得前端标识,通过"标识"从map获得"es列"
        String sortBy = cacheMap.get(searchVo.getSortBy());

        //3)如果es列存在,将进行排序
        if (StringUtils.isNotBlank(sortBy)) {

            //4) 进行排序
            FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort(sortBy);
            //5)排序方式通过sortWay确定
            if ("desc".equals(searchVo.getSortWay())) {
                fieldSortBuilder.order(SortOrder.DESC);
            } else {
                fieldSortBuilder.order(SortOrder.ASC);
            }

            //6)组装
            queryBuilder.withSort(fieldSortBuilder);

        }


        //3.查询
        Page<SearchSku> page = skuRepository.search(queryBuilder.build());


        //4.准备数据
        //4.1 总条数
        long count = page.getTotalElements();
        //4.2 查询结果(SearchSku --> ReturnSku)
        List<ReturnSku> list = new ArrayList<>();
        for (SearchSku searchSku : page.getContent()) {
            //1) 创建ReturnSku对象
            ReturnSku returnSku = new ReturnSku();
            //2) 依次填充数据
            returnSku.setId(searchSku.getId());
            returnSku.setGoodsName(searchSku.getSkuName());
            returnSku.setPrice(searchSku.getPrice());
            returnSku.setMidlogo(searchSku.getLogo());
            returnSku.setCommentCount(searchSku.getCommentCount());
            //3.将新对象添加list中
            list.add(returnSku);
        }

        // 5.封装
        Map map = new HashMap();
        map.put("count", count);
        map.put("list", list);
        return map;
    }
}
