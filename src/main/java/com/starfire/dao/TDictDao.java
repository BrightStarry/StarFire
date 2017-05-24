package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TDict;

/**
 *字典表dao 
 */
@Repository
public interface TDictDao {
	/**
	 * 查询某个type所有记录
	 */
	List<TDict> queryAllByType(String type);
	
	/**
	 * 指定type和key，获取value
	 */
	String queryByTypeAndKey(@Param("type")String type,@Param("key")String key);
}
