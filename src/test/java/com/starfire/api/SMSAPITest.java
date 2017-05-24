package com.starfire.api;


import org.junit.Test;


/**
 *SMSAPI短信接口测试类
 */
public class SMSAPITest {
	@Test
	public void testSendSMS(){
//		Map<String, String> params = new HashMap<>();
//		params.put("mobile", "17826824998");
//		params.put("templateid", "3055156");
//		SMSAPIResult result = SMSAPI.sendSMS(params);
//		System.out.println(result);
		
		
		//阿里短信测试
		String[] params = new String[2];
		params[0] = "1234";
		params[1] = "yy";
		boolean send = SMSAPI.send(17826824998L, 2,params);
		System.out.println(send);
	}
	
	//json格式的string转对象测试
	@Test
	public <T> void testStringToObject(){
//		String str = "{\"code\":200,\"msg\":\"101\",\"obj\":\"5268\"}";
//		SMSAPIResult result = JSON.parseObject(str,SMSAPIResult.class);
//		System.out.println(result);
//		WebSocketMessage<T> a = new WebSocketMessage<>();
//		BarrageResult b = new BarrageResult();
//		b.setHeadImage("111");
//		b.setMessage("222");
//		b.setState(1);
//		b.setName("333");
//		a.setType(1);
//		a.setData( (T) b);
//		
//		System.out.println(JSON.toJSONString(a));
		
		//{"data":{"headImage":"111","message":"222","name":"333","state":1},"type":1}
//		String str= "{\"data\":{\"headImage\":\"111\",\"message\":\"222\",\"name\":\"333\",\"state\":1},\"type\":1}";
//		WebSocketMessage<?> object = JSON.parseObject(str,WebSocketMessage.class);
	}
}
