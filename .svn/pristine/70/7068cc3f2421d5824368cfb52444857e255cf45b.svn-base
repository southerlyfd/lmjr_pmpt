package com.pmpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pmpt.entities.enums.Constant;

@Component
public class Timer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteBillNo() {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(Constant.INCR_BILL_NO, Constant.ZERO);
    }
}
