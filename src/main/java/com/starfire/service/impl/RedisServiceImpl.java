package com.starfire.service.impl;


import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.starfire.service.RedisService;
/**
 *redis service
 */
@Service
public class RedisServiceImpl implements RedisService{
	@Resource(name="redisTemplate")
	private  ValueOperations<String, Object> vOps;
	@Resource(name="redisTemplate")
	private  RedisTemplate<String, Object> redisTemplate;
	//默认超时时间
	private static final long DEFAULT_TIMEOUT = 60;
	
	/**
	 * set 数据 使用默认超时时间  60s
	 */
	@Override
	public void set(String key, Object value) {
		vOps.set(key, value);
		redisTemplate.expire(key, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}
	
	/**
	 * get 数据
	 */
	@Override
	public Object get(String key) {
		return vOps.get(key);
	}
	
	/**
	 * set 数据 使用指定超时时间
	 */
	@Override
	public void set(String key, Object value, long timeOut) {
		vOps.set(key, value);
		redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
	}

}
