package com.starfire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfire.dao.TDictDao;
import com.starfire.domain.TDict;
import com.starfire.service.TDictService;
/**
 *字典服务 
 */
@Service
public class TDictServiceImpl implements TDictService{
	@Autowired
	private TDictDao tDictDao;
	
	/**
	 * 根据type查询所有字典
	 */
	@Override
	public List<TDict> queryAllByType(String type) {
		List<TDict> dicts = tDictDao.queryAllByType(type);
		return dicts != null && dicts.size() != 0 ? dicts : null;
	}
	
	/**
	 * 根据 type and key get value
	 */
	@Override
	public String queryByTypeAndKey(String type, String key) {
		return tDictDao.queryByTypeAndKey(type, key);
	}

}
