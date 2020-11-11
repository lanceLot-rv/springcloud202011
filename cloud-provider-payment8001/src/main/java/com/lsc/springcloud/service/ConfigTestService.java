package com.lsc.springcloud.service;

import com.lsc.springcloud.entities.ConfigTestPo;
import com.lsc.springcloud.mapper.ConfigTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigTestService {
    @Autowired
    private ConfigTestMapper configTestMapper;

    public int updateBatch(){
        List<ConfigTestPo> pos = new ArrayList<>();
        ConfigTestPo po1 = new ConfigTestPo();
        po1.setId(1L);
        po1.setStatus("xxx");
        po1.setType(1);
        ConfigTestPo po2 = new ConfigTestPo();
        po2.setId(2L);
        po2.setStatus("22x");
        po2.setType(1);
        ConfigTestPo po3 = new ConfigTestPo();
        po3.setId(3L);
        po3.setStatus("xx1");
        po3.setType(1);
        pos.add(po1);
        pos.add(po2);
        pos.add(po3);
        return configTestMapper.updateBatch(pos);
    }
}
