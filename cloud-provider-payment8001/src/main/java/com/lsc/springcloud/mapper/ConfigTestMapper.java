package com.lsc.springcloud.mapper;

import com.lsc.springcloud.entities.ConfigTestPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ConfigTestMapper{

    @Update({"<script>",
                "<foreach  collection='list' item = 'item' index='index' separator=';'>",
                "update config_test set status = #{item.status} where id = #{item.id}",
                "</foreach>",
            "</script>"
    })
    int updateBatch(List<ConfigTestPo> list);
}
