package com.lsc.springcloud.controller;

import com.lsc.springcloud.entities.CommonResult;
import com.lsc.springcloud.service.ConfigTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController(value = "payment")
@Slf4j
public class PaymentController {
    @Autowired
    private ConfigTestService service;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    DefaultRedisScript<String> redisScript;

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
        String key=String.valueOf("goods");
        String value = UUID.randomUUID().toString();
        boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value, 5L, TimeUnit.SECONDS);

        String args="22222"+"-"+System.currentTimeMillis();
        Object res = stringRedisTemplate.execute((RedisConnection connection) -> connection.eval(
                redisScript.getScriptAsString().getBytes(),
                ReturnType.INTEGER,
                1,
                key.getBytes(),
                args.getBytes()));
        Long result= (Long) res;
        if(result==1){
            String unitAmountStr = (String) stringRedisTemplate.opsForHash().get("red_packet_" + key,"unit_amount");
            Double unitAmount = Double.valueOf(unitAmountStr);
            stringRedisTemplate.delete(key);
        }


        return stringRedisTemplate.opsForValue().get("goods");
    }
}
