package com.starfire.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfire.dao.TBarrageDao;
import com.starfire.service.TBarrageService;

/**
 *弹幕服务实现类 
 */
@Service
public class TBarrageServiceImpl implements TBarrageService{
	private Logger logger = LoggerFactory.getLogger(TBarrageServiceImpl.class);
	@Autowired
	private TBarrageDao tBarrageDao;
	
	/**
	 * 增加弹幕
	 */
	@Override
	public void addBarrage(Long userId, String content) {
		if(userId == null)
			userId = 0L;
		try {
			tBarrageDao.addBarrage(userId, content);
		} catch (Exception e) {
			logger.warn("弹幕存入数据库失败！发送者id:" + userId +e.getMessage());
		}
	}
	
}
