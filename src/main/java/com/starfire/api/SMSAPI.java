package com.starfire.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 验证码短信发送API 阿里大于
 */
public class SMSAPI {
	private static Logger logger = LoggerFactory.getLogger(SMSAPI.class);
	private static final String SERVER_URL = "http://gw.api.taobao.com/router/rest";
	private static final String APP_KEY = "23691867";
	private static final String APP_SECRET = "17ea38d6d321b6d20c86cea4e45499af";
	private static final String SMS_TYPE = "normal";
	private static final String SMS_FREE_SIGN_NAME = "星火";
	private static final String SMS_TEMPLATE_CODE_ONE = "SMS_53770150";// 通知类模版id
	private static final String SMS_TEMPLATE_CODE_TWO = "SMS_53860172";// 验证码模版id

	/**
	 * 发送短信 extend :消息返回时会传回该参数，通常保存用户id. sms_type :短信类型，传入值请填写normal
	 * sms_free_sign_name ：签名 星火 sms_param：
	 * "{\"code\":\"1234\",\"product\":\"alidayu\"}" rec_num ： 号码
	 * sms_template_code： 短信模版id
	 * 
	 * type:1通知类 2验证码
	 */
	public static boolean send(long phone, int type, String[] params) {
		try {
			TaobaoClient client = new DefaultTaobaoClient(SERVER_URL, APP_KEY, APP_SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			// 设置参数
			req.setSmsType(SMS_TYPE);
			req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);
			req.setRecNum(String.valueOf(phone));
			if (type == 1) {
				// 通知短信
				req.setSmsTemplateCode(SMS_TEMPLATE_CODE_ONE);
				String smsParam = "{\"name1\":" + params[0] + ",\"name2\":" + params[1] + "}";
				req.setSmsParamString(smsParam);

			} else {
				// 验证码短信
				req.setSmsTemplateCode(SMS_TEMPLATE_CODE_TWO);
				String smsParam = "{\"checkCode\": \"" + params[0] + "\"}";
				req.setSmsParamString(smsParam);

			}
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);

			// 将返回过来的json形式的字符串转换成json对象，然后从中取出错误码，如果为0，表示发送成功
			JSONObject jsonObject = JSON.parseObject(rsp.getBody())
					.getJSONObject("alibaba_aliqin_fc_sms_num_send_response").getJSONObject("result");
			if (jsonObject.getString("err_code").equals("0")) {
				return true;
			} else {
				throw new ApiException("错误码是" + jsonObject.getString("err_code"));
			}
		} catch (Exception e) {
			System.out.println("短信发送错误！" + e.getMessage());
			logger.warn("短信发送错误！" + e.getMessage());

			return false;
		}

	}

	public static void main(String[] args){

	}

	// 因为网易云信充值金额必须大于2000才能使用所以弃用，
	/*
	 * 
	 * private static final String APP_KEY = "4a94608909bce324e2f5000ce18debee";
	 * 
	 * private static final String APP_SECRET = "9ad1c7fa7226";
	 * 
	 * private static final String URL =
	 * "https://api.netease.im/sms/sendcode.action";// 请求路径
	 * 
	 * private static final String NONCE = "zhengxing";// 临时随机数（128字符以内）
	 * 
	 * public static final String TEMPLATE_ID ="3055156";//验证码模版id
	 *//**
		 * 调用 网易云信 的短信接口
		 * 
		 * param： 发送验证码：* mobile 手机号 ; templateid 模版id;codeLen 验证码长度 默认4;
		 * 发送模版短信： *templateid 模版编号;*moniles ["xx","xx"] 手机号码;params ["xx","xx"]
		 * 
		 * 发送验证码 or 发送模版短信 result:{"code":200,"msg":"101","obj":"5268"}
		 */
/*		  public static SMSAPIResult sendSMS(Map<String, String> params){
		  SMSAPIResult result = null; 
		  String resultStr = null;
		  CloseableHttpClient httpClient = null; CloseableHttpResponse response = null; 
		  try { 
		  //创建httpclient对象 
		  httpClient = HttpClients.createDefault(); 
		  //创建post请求方式的对象
		  HttpPost httpPost = new HttpPost(SMSAPI.URL); 
		  //设置参数
		  List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
		  //遍历params,依次设置参数 
		  for(Map.Entry<String,String> entry : params.entrySet()){ 
		  	nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
		  } 
		  //设置参数到请求对象中
		  httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
		  //设置header信息 String curTime =
		  String.valueOf(System.currentTimeMillis());
		  httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		  httpPost.setHeader("AppKey",SMSAPI.APP_KEY);
		  httpPost.setHeader("Nonce",SMSAPI.NONCE);
		  httpPost.setHeader("CurTime",curTime);
		  httpPost.setHeader("CheckSum",SignUtil.getSHA1(SMSAPI.APP_SECRET,SMSAPI.NONCE,curTime)); //执行请求操作，并拿到结果（同步阻塞） response =
		  httpClient.execute(httpPost); 
		  //取出结果实体 HttpEntity entity = response.getEntity(); if(entity != null) { 
		  	//按指定编码转换结果实体为String类型
	 		resultStr = EntityUtils.toString(entity,"UTF-8"); 
	 		//result(json)转对象
		  if(resultStr != null && !"".equals(resultStr)){ result =
		  JSON.parseObject(resultStr,SMSAPIResult.class); } } } catch
		  (Exception e) { if(params.get("mobile") != null){
		  LOGGER.error(params.get("mobile") + "短信发送错误!" + e.getMessage() ,e);
		  }else{ LOGGER.error(params.get("mobiles") + "短信发送错误!" +
		 e.getMessage() ,e); } }finally{ //关闭连接 try { response.close();
		  httpClient.close(); } catch (Exception e) {
		  LOGGER.error(e.getMessage(),e); } }

	return result;
}
*/
}
