package com.starfire.util;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.starfire.dto.BarrageResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-redis.xml")
public class RedisTest {

	
	@Resource(name="redisTemplate")
	private ValueOperations<String, Object> vOps;
	
	@Test
	public void test() {
		// 创建弹幕返回对象
		BarrageResult barrageResult = new BarrageResult("zzz", 98989L, "郑星", "headImage/noLogin.jpg", (short) 1);
		vOps.set("zzz", barrageResult);
		System.out.println(vOps.get("zzz"));
		
	}

}
