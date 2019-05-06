package com.jk.util;
 
import java.io.IOException;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 类: HttpClient <br>
 * 描述: httpclient工具类 <br>
 * 作者: song<br>
 * 时间: 2017年7月21日 下午3:15:27
 */
public class HttpClientUtil {
	
	static CloseableHttpClient client = null;
	static {
		//1、创建HttpClient实例
		client = HttpClients.createDefault();
	}
	
	/**
	 * 
	 * 方法: get <br>
	 * 描述: get请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:15:25
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String get(String url,HashMap<String, Object> params){
		try {
			//2、创建HttpGet方法请求
			HttpGet httpGet = new HttpGet();
			Set<String> keySet = params.keySet();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
			for (String key : keySet) {
				stringBuffer.append("&").append(key).append("=").append(params.get(key));
			}
			httpGet.setURI(new URI(stringBuffer.toString()));
			//3、调用execute方法执行
			CloseableHttpResponse execute = client.execute(httpGet);
			//4、获取响应的状态码，判断请求是否成功
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			//5、获取请求成功的信息
			return EntityUtils.toString(execute.getEntity(), "utf-8");
			
		}catch (Exception e) {
			//6、关闭资源
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	/**
	 * 
	 * 方法: post <br>
	 * 描述: post请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:20:31
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url,HashMap<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
				parameters.add(e);
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			//6、关闭资源
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	
	public static String main() {
		String url = "http://t.weather.sojson.com/api/weather/city/101010100";
		HashMap<String, Object> params = new HashMap<>();
		String returnStr = get(url, params);
		JSONObject parseObject = JSON.parseObject(returnStr);
		int status = parseObject.getIntValue("status");
		String high="";
		//请求成功或者失败
		if(status!=200){
			System.out.println("请求失败");
		}else{
			JSONObject jsonObject2 = parseObject.getJSONObject("cityInfo");
			JSONObject data = parseObject.getJSONObject("data");
			JSONArray forecast = data.getJSONArray("forecast");
			JSONObject jsonObject = forecast.getJSONObject(0);
			 high += jsonObject2.getString("city")+" ";
				high+= jsonObject.getString("ymd")+" ";
				high+= jsonObject.getString("week")+" "+"温度"  ;
				high+=data.getString("wendu")+"℃ ";
				high+= jsonObject.getString("high")+" ";
				high+= jsonObject.getString("low")+" ";
				high+= jsonObject.getString("type")+" ";
				high+= jsonObject.getString("notice")+" ";
			System.out.println("今天的最高温度：==="+high);
			return high;
		}
		return "";
	}
	
	
}
