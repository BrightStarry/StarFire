package com.starfire.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 *智能聊天 api 
 */
public class AIChatAPI {
	
	private static final String APP_CODE = "6482a80903a74a0693da327dee9121d8";
	private static final String URL = "http://jisuznwd.market.alicloudapi.com/iqa/query";
	/**
	 * 炸裂 返回 401 说是没有验证通过  ---后续。。怪我自己。没记清楚阿里云APPCODE的验证方式，需要 在appcode前加上appcode 才可以，，
	 * 发送消息 获取回复
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String openChat(String message) throws ClientProtocolException, IOException{
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		String resultMessage = null;
		try {
			client = HttpClients.createDefault();
			List<NameValuePair> nvps = new ArrayList<>();
			nvps.add(new BasicNameValuePair("question", message));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
			HttpGet get = new HttpGet(URL + "?"+ str);
			get.setHeader("Authorization","APPCODE "+ APP_CODE);
			response = client.execute(get);
			String jsonStr = EntityUtils.toString(response.getEntity());
			resultMessage = JSON.parseObject(jsonStr).getJSONObject("result").getString("content");
		}finally {
			response.close();
		}
		return resultMessage;
	}
	
	/*public static String openChat(String message) throws Exception{
	String host = "http://jisuznwd.market.alicloudapi.com";
    String path = "/iqa/query";
    String method = "GET";
    Map<String, String> headers = new HashMap<String, String>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", APP_CODE);
    Map<String, String> querys = new HashMap<String, String>();
    querys.put("question", message);


   HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
   System.out.println(EntityUtils.toString(response.getEntity()));
   
   return  null;
}*/

}
