package com.starfire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.starfire.domain.TChatRecord;

/**
 *聊天记录 dao 
 */
@Repository
public interface TChatRecordDao {
	
	/**
	 * 查询某个用户 向另一用户 发起的聊天记录
	 * 完整的需要查两遍
	 */
	List<TChatRecord> queryChatReocrdByPairUserId(@Param("userIdA")Long userIdA,@Param("userIdB")Long userIdB);
	
	/**
	 * 添加聊天记录
	 */
	Integer addChatRecord(TChatRecord tChatRecord);
	

}
