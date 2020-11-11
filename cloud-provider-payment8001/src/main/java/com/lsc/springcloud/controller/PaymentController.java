package com.lsc.springcloud.controller;

import com.lsc.springcloud.entities.CommonResult;
import com.lsc.springcloud.service.ConfigTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController(value = "payment")
@Slf4j
public class PaymentController {
    @Autowired
    private ConfigTestService service;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "create")
    public CommonResult create() {
        AtomicInteger in = new AtomicInteger(1);
        in.addAndGet(1);
        return new CommonResult(200, "success");
    }

    @GetMapping(value = "get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        return new CommonResult(200, "success");
    }

    @GetMapping(value = "update")
    public int update(){
        return service.updateBatch();
    }

    @GetMapping("redis")
    public String redis(){
        return stringRedisTemplate.opsForValue().get("goods");
    }
}
