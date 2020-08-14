package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.SpecificationOption;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@org.apache.ibatis.annotations.Mapper
public interface SpecificationOptionMapper extends Mapper<SpecificationOption> {

@Select("select * from tb_specification_option where spec_id=#{id}")
  @Results({
          @Result(property = "id",column = "id"),
          @Result(property = "specId",column = "spec_id"),
          @Result(property = "optionName",column = "option_name"),
  })
    List<SpecificationOption>findAll(@Param("id")Integer id);
}
