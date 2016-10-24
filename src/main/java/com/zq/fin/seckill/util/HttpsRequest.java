package com.zq.fin.seckill.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HttpsRequest {
	
	private static Logger logger = Logger.getLogger(HttpsRequest.class.getName());

	public static String XMLhttpRequest(String requestUrl, String requestMethod, String cookie) {
		String xml = "";
		HttpsURLConnection httpUrlConn = null;
		StringBuffer buffer = new StringBuffer();
		System.setProperty ("jsse.enableSNIExtension", "false");
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("Cookie", cookie);
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gb2312");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			xml = buffer.toString();
		} catch (ConnectException ce) {
			logger.info("Weixin server connection timed out.");
		} catch (Exception e) {
			logger.info("https request error:{}");
		} finally {
			if(ObjectUtil.isNotEmpty(httpUrlConn)){
				httpUrlConn.disconnect();
				httpUrlConn = null;
			}
		}
		return xml;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 * @throws ConnectException 
	 */
	public static String httpRequestString(String requestUrl, String requestMethod, String outputStr, String cookie) throws ConnectException {
		String result = "";
		HttpsURLConnection httpUrlConn = null;
		System.setProperty ("jsse.enableSNIExtension", "false");
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			
			httpUrlConn.setRequestProperty("ContentType", "application/json;charset=gb2312"); 
			
//			httpUrlConn.setConnectTimeout(10000);
			if(ObjectUtil.isNotEmpty(cookie)){
				httpUrlConn.setRequestProperty("Cookie", cookie.toString());
			}
			
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gb2312");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			
			httpUrlConn.disconnect();
			result = buffer.toString();
		} catch (ConnectException ce) {
			logger.info("Weixin server connection timed out.");
			throw ce;
		} catch (Exception e) {
			logger.info("https request error:{}");
		} finally {
			if(ObjectUtil.isNotEmpty(httpUrlConn)){
				httpUrlConn = null;
			}
		}
		return result;
	}
	
	/**
	 *
	 * @param strUrl 请求地址
	 * @param params 请求参数
	 * @param method 请求方法
	 * @return  网络请求字符串
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String net(String strUrl, Map params,String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = "";
		try {
			StringBuffer sb = new StringBuffer();
			if(method==null || method.equals("GET")){
				strUrl = strUrl+"?"+urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if(method==null || method.equals("GET")){
				conn.setRequestMethod("GET");
			}else{
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
			conn.setUseCaches(false);
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params!= null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
						out.writeBytes(urlencode(params));
				} catch (Exception e) {
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}
	
	//将map型转为请求参数型
	@SuppressWarnings("rawtypes") 
	private static String urlencode(Map<String, Object>data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.substring(0, sb.length()-1);
		}
}
