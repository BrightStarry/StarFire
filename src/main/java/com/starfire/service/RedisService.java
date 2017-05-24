package com.starfire.service;
/**
 *redis service 接口 
 */
public interface RedisService {
	void set(String key, Object value);
	
	void set(String key, Object value,long timeOut);

	Object get(String key);

}
