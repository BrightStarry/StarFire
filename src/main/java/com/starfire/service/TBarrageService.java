package com.starfire.service;

/**
 *弹幕 服务接口
 */
public interface TBarrageService {
	
	/**
	 *增加弹幕 
	 */
	void addBarrage(Long userId,String content);

}
