package com.zq.fin.seckill.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
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
}
