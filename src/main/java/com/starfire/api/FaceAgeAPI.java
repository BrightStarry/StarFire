package com.starfire.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.starfire.util.HttpUtils;


/**
 *人脸年龄识别api 
 */
public class FaceAgeAPI {
	
	private static final String APP_CODE = "6482a80903a74a0693da327dee9121d8";
	
	/**
	 * 获取人脸年龄
	 * @throws Exception 
	 */
	public static String getFaceAge(String base64) throws Exception{
		
		String host = "https://dm-23.data.aliyun.com";
	    String path = "/rest/160601/face/age_detection.json";
	    String method = "POST";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE "+APP_CODE);
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+ base64 +"\"}}]}";
		
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	//获取response的body 
	    	String jsonStr = EntityUtils.toString(response.getEntity());
	    	//转成josn
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			String str = jsonObject.getJSONArray("outputs").getJSONObject(0).
					getJSONObject("outputValue").getString("dataValue");
			//又要转成json。。因为接口传回来的那个dataValue是字符串。。日
			String age = JSON.parseObject(str).getJSONArray("age").getString(0);
			return age != null && !age.equals("") ? age : null;
		
		
	}
	
	
	/*
	 CloseableHttpResponse httpResponse = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpPost httpPost = new HttpPost(REQUEST_URL);
			//设置header
			httpPost.setHeader("Authorization","APPCODE "+APP_CODE);
			//设置参数
			String params = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+ base64 +"\"}}]}";
			StringEntity stringEntity = new StringEntity(params,"UTF-8");
			stringEntity.setContentEncoding("UTF-8");  
	        stringEntity.setContentType("application/json");  
			httpPost.setEntity(stringEntity);
			//执行
			httpResponse = httpClient.execute(httpPost);
			//获取结果
			String jsonStr = httpResponse.getEntity().toString();
			System.out.println(jsonStr);
			//转成josn
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			String str = (String)jsonObject.getJSONObject("outputs").get("age");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 
	 */
	
	

}
