package com.starfire.service;

import java.util.List;

import com.starfire.domain.TDict;

/**
 *字典 服务 接口 
 */
public interface TDictService {
	/**
	 * 根据tpye查询底下所有记录
	 */
	List<TDict> queryAllByType(String type);
	
	/**
	 * 根据type and key get value
	 */
	String queryByTypeAndKey(String type,String key);
}
