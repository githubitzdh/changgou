package com.czxy.changgou3.service;

import com.alibaba.fastjson.JSON;
import com.czxy.changgou3.mapper.*;
import com.czxy.changgou3.pojo.Sku;
import com.czxy.changgou3.pojo.SkuPhoto;
import com.czxy.changgou3.pojo.Specification;
import com.czxy.changgou3.pojo.Spu;
import com.czxy.changgou3.vo.ESData;
import com.czxy.changgou3.vo.OneSkuResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/15 0015
 **/
@Transactional
@Service
public class SkuService {
    @Resource
    private SpuMapper spuMapper;
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private SkuCommentMappper skuCommentMappper;
    @Resource
    private SkuPhotoMapper skuPhotoMapper;
    @Resource
    private CategorysMapper categorysMapper;
    @Resource
    private SpecificationMapper specificationMapper;

    /**
     * 查询所有sku,将数据库查询结果转换ESData
     *
     * @return
     */
    public List<ESData> findESData() {
        //1 查询所有详情sku
        List<Sku> list = skuMapper.findAllSkus();
        //2.将数据库查询结果转换ESData
        // 2.1) 创建结合,用于存放处理后的ESData
        List<ESData> returnList = new ArrayList<>();
        // 2.2)  遍历所有Sku,将sku相关信息封装到ESData
        for (Sku sku : list) {
            //1 创建ESData对象,用于封装数据
            ESData esData = new ESData();
            //2 根据接口规范,从spu,sku,SkuComment获得数据
            esData.setId(sku.getId());
            esData.setLogo(sku.getSpu().getLogo());
            esData.setSkuName(sku.getSkuName());
            //all: sku名称 + 规格 + 品牌名称
            esData.setAll(sku.getSkuName() + sku.getSpecInfoIdTxt() + sku.getSpu().getBrand().getBrandName());

            esData.setOnSaleTime(new Date());
            esData.setBrandId(sku.getSpu().getBrandId());
            esData.setCatId(sku.getSpu().getCat3Id());

            //规格: 数据库 json字符创,将字符串转换
            esData.setSpecs(JSON.parseObject(sku.getSpecInfoIdTxt(), Map.class));
            esData.setPrice(sku.getPrice());
            esData.setSpuName(sku.getSpu().getSpuName());
            esData.setStock(sku.getStock());
            esData.setDescription(sku.getSpu().getDescription());
            esData.setPackages(sku.getSpu().getPackages());
            esData.setAftersale(sku.getSpu().getAftersale());

            //评论数
            Integer count = skuCommentMappper.findNumBySpuId(sku.getSpuId());
            esData.setCommentCount(count);
            esData.setSellerCount(10);

            //3 将新建的ESData,添加到returnList集合中
            returnList.add(esData);
        }

        return returnList;
    }

    /**
     * 查询详情
     * @param skuid
     * @return
     */
    public OneSkuResult findSkuByid(Integer skuid) {
       //0 提供对象
        OneSkuResult oneSkuResult = new OneSkuResult();
      //1 封装
        //1.1 sku基本信息
        Sku sku = skuMapper.selectByPrimaryKey(skuid);
        //赋值
        oneSkuResult.setSkuid(skuid);
        oneSkuResult.setSpuid(sku.getSpuId());
        //商品名称
        oneSkuResult.setGoodsName(sku.getSkuName());
        //商品价格
        oneSkuResult.setPrice(sku.getPrice());

        //1.2.spu基本信息
        Spu spu = spuMapper.findSpuById(sku.getSpuId());
        //上架时间
        oneSkuResult.setOnSaleDate(spu.getOnSaleTime());

        //1.3 评论数
        Integer commentcount = skuCommentMappper.findNumBySpuId(spu.getId());
        oneSkuResult.setCommentCount(commentcount);
        //评论级别
        Integer commentLevel = skuCommentMappper.findAvgStarBySkuId(sku.getId());
        oneSkuResult.setCommentLevel(commentLevel);

        //1.4三级分类
        //一级分类
        oneSkuResult.setCat1Info(categorysMapper.selectByPrimaryKey(spu.getCat1Id()));
        //二级分类
        oneSkuResult.setCat2Info(categorysMapper.selectByPrimaryKey(spu.getCat2Id()));
         //三级分类
        oneSkuResult.setCat3Info(categorysMapper.selectByPrimaryKey(spu.getCat3Id()));


        //1.5.图片
        //1.5.1第一张图片,来自spu logo属性(理论:大中小三种图片,实际;忽略)
        HashMap<String, String> logo = new HashMap<>();
       logo.put("smlogo",spu.getLogo());
       logo.put("biglogo",spu.getLogo());
       logo.put("xbiglogo",spu.getLogo());
       oneSkuResult.setLogo(logo);

       //1.5.2 通过Skuid查询对应的所有图片,其他,来自sku_photo
        List<SkuPhoto> skuPhotolist = skuPhotoMapper.findSkuPhotoBySkuId(sku.getId());
        List<Map> photos = new ArrayList<>();
        for (SkuPhoto sp : skuPhotolist) {
            HashMap<String, String> map = new HashMap<>();
            map.put("smimg",sp.getUrl());
            map.put("bigimg",sp.getUrl());
            map.put("xbigimg",sp.getUrl());
            photos.add(map);
        }
        oneSkuResult.setPhotos(photos);

       //商品描述
        oneSkuResult.setDescription(spu.getDescription());
        //售后保障
        oneSkuResult.setAftersale(spu.getAftersale());
        //库存量
        oneSkuResult.setStock(sku.getStock());

    //1.6 spu对应所有规格
        List<Specification> splist = specificationMapper.findall(spu.getCat3Id());
        System.out.println(splist+"-----------------------------------------------------------------------");
       oneSkuResult.setSpecList(splist);

       //1.7 sku对应规格信息
        HashMap<String, String> spec_info = new HashMap<>();
        //1:1|2:6|6:22
        spec_info.put("id_list",sku.getSpecInfoIdList());
        //规格列表码,格式：{"机身颜色":"白色","内存":"3GB","机身存储":"16GB"}
         spec_info.put("id_txt",sku.getSpecInfoIdTxt());
         oneSkuResult.setSpecInfo(spec_info);


         //1.8 spu对应的所有sku的规格信息
        List<Sku> skuBySpuIdlist = skuMapper.findSkuBySpuId(spu.getId());
        List<Map<String,String>> skuLlist = new ArrayList<>();
        for (Sku tempsku : skuBySpuIdlist) {
            HashMap<String, String> map = new HashMap<>();
          map.put("skuid",tempsku.getId().toString());
            //1:1|2:6|6:22
          map.put("id_list",tempsku.getSpecInfoIdList());
            skuLlist.add(map);
        }
        oneSkuResult.setSkuList(skuLlist);
         //返回结果
        return oneSkuResult;
    }

    /**
     * 更新商品数量
     * @param skuid
     * @param count
     */
    public void updateSkuNum(Integer skuid, Integer count) {
       //1.查询
         Sku sku=skuMapper.selectByPrimaryKey(skuid);
        //2.修改
         sku.setStock(sku.getStock()-count);
        //3.更新
         skuMapper.updateByPrimaryKey(sku);
    }
}
