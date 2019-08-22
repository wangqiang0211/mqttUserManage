package com.cdzic.mqttUserManage.cache.impl;

import com.cdzic.mqttUserManage.cache.abs.IRedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService extends IRedisService<Object> {
    @Value("${redis.token.space}")
    private String redisSpace;

    @Override
    protected String getRedisKey() {
        return redisSpace;
    }
}
