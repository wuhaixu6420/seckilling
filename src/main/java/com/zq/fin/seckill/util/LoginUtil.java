package com.zq.fin.seckill.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zq.fin.seckill.common.BaseConstant;


public class LoginUtil
{
	private static Logger logger = LoggerFactory
			.getLogger(LoginUtil.class);
	
    public static Map<String, DefaultHttpClient> httpclientMap = new HashMap<String, DefaultHttpClient>();
//    public static DefaultHttpClient httpclient = getHttpClient();

    //模拟登陆
    public static String index_url = "https://trade.glsc.com.cn/";
    public static String login_url = "https://trade.glsc.com.cn/Default.aspx";
    public static String img_path_url = "https://trade.glsc.com.cn/usercenter/checkcode.aspx?0.09639477171003819";
    public static String image_save_path = "code.jpg";
    
    //取得历史成交记录
    public static String lscjcx_url = "https://trade.glsc.com.cn/WTCX/Lscjcx.aspx";
    
    

    
    public static class ResponsObj {
    	
    	private String modulus;
    	private String cookie;
    	private String __VIEWSTATE;
    	private String __VIEWSTATEGENERATOR;
    	private String __EVENTVALIDATION;
    	
    	public ResponsObj(){}
    	
		public String getModulus() {
			return modulus;
		}
		public void setModulus(String modulus) {
			this.modulus = modulus;
		}
		public String getCookie() {
			return cookie;
		}
		public void setCookie(String cookie) {
			this.cookie = cookie;
		}
		public String get__VIEWSTATE() {
			return __VIEWSTATE;
		}
		public void set__VIEWSTATE(String __VIEWSTATE) {
			this.__VIEWSTATE = __VIEWSTATE;
		}
		public String get__VIEWSTATEGENERATOR() {
			return __VIEWSTATEGENERATOR;
		}
		public void set__VIEWSTATEGENERATOR(String __VIEWSTATEGENERATOR) {
			this.__VIEWSTATEGENERATOR = __VIEWSTATEGENERATOR;
		}
		public String get__EVENTVALIDATION() {
			return __EVENTVALIDATION;
		}
		public void set__EVENTVALIDATION(String __EVENTVALIDATION) {
			this.__EVENTVALIDATION = __EVENTVALIDATION;
		}
    	
    }
    
    //首页
    public static ResponsObj pageinit(String userid)
    {

        BufferedReader in = null;

        HttpResponse response = null;
        
        //用map保持多用户多sessionid连接
        if(!httpclientMap.containsKey(userid)){
        	httpclientMap.put(userid, new DefaultHttpClient());
        }
        DefaultHttpClient httpclient = httpclientMap.get(userid);
        
        try
        {	
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            HttpGet httpGet = new HttpGet(index_url);
            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            response = httpclient.execute(httpGet);

            String charset = "";  
            String contentType = response.getEntity().getContentType().getValue();  
            if(StringUtil.isNotEmpty(contentType)&&contentType.indexOf("charset")>0){  
                 charset = contentType.substring(contentType.indexOf("charset=")+"charset=".length());  
            }  
            if(StringUtil.isEmpty(charset)){  
                 charset = "UTF-8";  
            }  
            StringBuilder sb = new StringBuilder();  
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset));  
            String line;  
            while ((line = br.readLine()) != null) {  
                 sb.append(line);  
            }  
            String content = sb.toString();  
            Document document = Jsoup.parse(content);  
            org.jsoup.select.Elements eles = document.select("input");
            
            //返回值
            ResponsObj responsObj = new ResponsObj();
            
            //asp.net属性
            for(Element ele : eles){
            	//System.out.println(ele);
            	
            	String name = ele.attr("name");
            	String value = ele.attr("value");
            	
            	if ("__VIEWSTATE".equals(name)) {
            		responsObj.set__VIEWSTATE(value);
            		
            	} else if ("__VIEWSTATEGENERATOR".equals(name)) {
            		responsObj.set__VIEWSTATEGENERATOR(value);
            		
            	} else if ("__EVENTVALIDATION".equals(name)) {
            		responsObj.set__EVENTVALIDATION(value);
            	}
            }
            
            //cookies
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            StringBuilder cookiesSB = new StringBuilder();
            //System.out.println("第一次cookie");
            if (cookies.isEmpty())
            {
                System.out.println("None");
            } else
            {
                for (int i = 0; i < cookies.size(); i++)
                {
                    // System.out.println("- " + cookies.get(i).toString());
                    cookiesSB.append(cookies.get(i).getName()).append("=")
                            .append(cookies.get(i).getValue()).append("; ");
                }
            }
            responsObj.setCookie(cookiesSB.toString());

            //modulus
            int idx = content.indexOf("var key = new RSAKeyPair");
            if(idx > 0){
            	String substr = content.substring(idx, idx + 300);
            	substr = substr.split(",")[2];
            	substr = substr.replaceAll("\"", "");
            	substr = substr.trim();
            	responsObj.setModulus(substr);
            }
            
            return responsObj;
            
        }  catch (Exception e)
        {
            e.printStackTrace();

            return null;
        } finally{
            if(in != null)
                try
                {
                    in.close();
                    if(ObjectUtil.isNotEmpty(response)){
            			response.getEntity().getContent().close();
            		}
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
    }

    //取得交易数据
    public static String getTradeInfo(ResponsObj responsObj, String userid)
    {

        BufferedReader in = null;
        
        HttpResponse response = null;
        
        //用map保持多用户多sessionid连接
        if(!httpclientMap.containsKey(userid)){
        	httpclientMap.put(userid, new DefaultHttpClient());
        }
        DefaultHttpClient httpclient = httpclientMap.get(userid);
        
        try
        {
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            HttpGet httpGet = new HttpGet(lscjcx_url);
            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            
            httpGet.setHeader("Cache-Control", "max-age=0");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", responsObj.getCookie());
            httpGet.setHeader("Host", "trade.glsc.com.cn");
            httpGet.setHeader("Referer", "https://trade.glsc.com.cn/left.aspx");
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
            
            response = httpclient.execute(httpGet);

            String charset = "";  
            String contentType = response.getEntity().getContentType().getValue();  
            if(StringUtil.isNotEmpty(contentType)&&contentType.indexOf("charset")>0){  
                 charset = contentType.substring(contentType.indexOf("charset=")+"charset=".length());  
            }  
            if(StringUtil.isEmpty(charset)){  
                 charset = "UTF-8";  
            }  
            StringBuilder sb = new StringBuilder();  
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset));  
            String line;  
            while ((line = br.readLine()) != null) {  
                 sb.append(line);  
            }  
            String content = sb.toString();  
            Document document = Jsoup.parse(content);  
            @SuppressWarnings("unused")
			org.jsoup.select.Elements eles = document.select("input");
            
            //cookies
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            //httpGet.releaseConnection();
            StringBuilder cookiesSB = new StringBuilder();
            //System.out.println("第一次cookie");
            if (cookies.isEmpty())
            {
                System.out.println("None");
            } else
            {
                for (int i = 0; i < cookies.size(); i++)
                {
                    // System.out.println("- " + cookies.get(i).toString());
                    cookiesSB.append(cookies.get(i).getName()).append("=")
                            .append(cookies.get(i).getValue()).append("; ");
                }
            }
            responsObj.setCookie(cookiesSB.toString());

            //modulus
            int idx = content.indexOf("var key = new RSAKeyPair");
            if(idx > 0){
            	String substr = content.substring(idx, idx + 300);
            	substr = substr.split(",")[2];
            	substr = substr.replaceAll("\"", "");
            	substr = substr.trim();
            	responsObj.setModulus(substr);
            }
            
            return "ok";
            
        }  catch (Exception e)
        {
            e.printStackTrace();

            return null;
        } finally{
            if(in != null)
                try
                {
                    in.close();
                    if(ObjectUtil.isNotEmpty(response)){
            			response.getEntity().getContent().close();
            		}
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
    }
    
    public static String imgCookie(ResponsObj responsObj, String userid)
    {

        BufferedReader in = null;

        HttpResponse response = null;
        
        //用map保持多用户多sessionid连接
        if(!httpclientMap.containsKey(userid)){
        	httpclientMap.put(userid, new DefaultHttpClient());
        }
        DefaultHttpClient httpclient = httpclientMap.get(userid);
        
        try
        {
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            HttpGet httpGet = new HttpGet(img_path_url);
            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);

            httpGet.setHeader("Cache-Control", "max-age=0");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", responsObj.getCookie());
            httpGet.setHeader("Host", "trade.glsc.com.cn");
            httpGet.setHeader("Referer", "https://trade.glsc.com.cn/Default.aspx");
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");

            
            response = httpclient.execute(httpGet); 
            //保存图片
            download(response.getEntity().getContent(), image_save_path);
            return image_save_path;
        }  catch (Exception e)
        {
            e.printStackTrace();

            return null;
        } finally{
            if(in != null)
                try
                {
                    in.close();
                    if(ObjectUtil.isNotEmpty(response)){
            			response.getEntity().getContent().close();
            		}
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
    }
    
    public static String identifyImg(){
    	//String str = CQZDMDLL.result(image_save_path);
    	String str = null;
		try {
			str = CodeRecognitionUtil.getAllOcr(image_save_path);
		} catch (Exception e) {
			System.out.println("图片验证码识别失败！");
			e.printStackTrace();
		}
//        if(str != null && str.trim().matches("\\d{4,}"))
//            return str;

        return str;
    }

    public static String loginCookie(String code, String id, String pw, ResponsObj responsObj, String userid)
    {
    	
    	HttpResponse response = null;
    	
        //用map保持多用户多sessionid连接
        if(!httpclientMap.containsKey(userid)){
        	httpclientMap.put(userid, new DefaultHttpClient());
        }
        DefaultHttpClient httpclient = httpclientMap.get(userid);
    	
        try
        {
        	String encryptionExponent = "010001";
        	String modulus = responsObj.getModulus().replace(");", "");
        	String cookie = responsObj.getCookie() + "leftmain=0";
        	String __VIEWSTATE = responsObj.get__VIEWSTATE();
        	String __VIEWSTATEGENERATOR = responsObj.get__VIEWSTATEGENERATOR();
        	String __EVENTVALIDATION = responsObj.get__EVENTVALIDATION();
        	
        	//posx
        	String posx = getPosx(BaseConstant.LOGINUTIL_URL, encryptionExponent, modulus, id, pw);
        	
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            
            // 2  用户登录
            HttpPost httppost = new HttpPost(login_url); 

            httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httppost.setHeader("Accept-Encoding", "gzip, deflate");
            httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setHeader("Cookie", cookie);
            httppost.setHeader("Host", "trade.glsc.com.cn");
            httppost.setHeader("Origin", "https://trade.glsc.com.cn");
            httppost.setHeader("Referer", "https://trade.glsc.com.cn/");
//            httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
            nvps.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR));
            nvps.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));
            nvps.add(new BasicNameValuePair("type_zh", "0"));
            nvps.add(new BasicNameValuePair("DropDownList1", "8"));
            nvps.add(new BasicNameValuePair("Text1", id));
            nvps.add(new BasicNameValuePair("Text2", ""));
            nvps.add(new BasicNameValuePair("aqfsddl", "SSL"));
            nvps.add(new BasicNameValuePair("Text3", code));
            nvps.add(new BasicNameValuePair("otptxt", ""));
            nvps.add(new BasicNameValuePair("Button1", ""));
            nvps.add(new BasicNameValuePair("posx", posx));
            nvps.add(new BasicNameValuePair("hidmac", ""));
            
            httppost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
            httppost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            
            //执行
            response = httpclient.execute(httppost);
            
            //判断登录是否成功
            String sub="交易链接出现异常断开，请重新登录交易或者关闭浏览器后重新登录";
    		int errors=response.toString().indexOf(sub);
    		if(errors>0){
    			System.out.println("cookie错误----->"+sub);
    			logger.info("cookie错误----->"+sub);
    			//登录失败
    			return "-1";
    		}

            //headers
            for (org.apache.http.Header hd : response.getAllHeaders()){
            	System.out.println(hd.getName() + ":" + hd.getValue()); 
            }
            
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            //httppost.releaseConnection();
            StringBuilder cookiesSB = new StringBuilder();
            //System.out.println("第一次cookie");
            if (cookies.isEmpty())
            {
                System.out.println("None");
            } else
            {
                for (int i = 0; i < cookies.size(); i++)
                {
                    // System.out.println("- " + cookies.get(i).toString());
                    cookiesSB.append(cookies.get(i).getName()).append("=")
                            .append(cookies.get(i).getValue()).append("; ");
                }
            }
            //httppost.releaseConnection();

            return cookiesSB.toString();

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
        	try
            {
        		if(ObjectUtil.isNotEmpty(response)){
        			response.getEntity().getContent().close();
        		}
                
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        return null;
    }

    
    public static String rend(String cookie, String userid)
    {
        BufferedReader in = null;

        HttpResponse response = null;
        
        //用map保持多用户多sessionid连接
        if(!httpclientMap.containsKey(userid)){
        	httpclientMap.put(userid, new DefaultHttpClient());
        }
        DefaultHttpClient httpclient = httpclientMap.get(userid);
        
        try
        {
            // 1 获取 _tb_token_
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            HttpGet httpGet = new HttpGet("http://www.400gb.com/index.php");
            httpGet.setHeader("Cookie", cookie);
            httpGet.setHeader("Host", "www.400gb.com");
            httpGet.setHeader("Referer", "http://www.400gb.com/index.php");
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");

            httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            response = httpclient.execute(httpGet); 


            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = in.readLine()) != null)
            {
                sb.append(s.trim()).append("\n");
            }

            System.out.println(sb.toString());

            return sb.toString();
        }  catch (Exception e)
        {
            e.printStackTrace();

            return null;
        } finally{
            if(in != null)
                try
                {
                    in.close();
                    if(ObjectUtil.isNotEmpty(response)){
            			response.getEntity().getContent().close();
            		}
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
    }


    public static boolean download(InputStream in, String path)
    {
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(path);
            byte b[] = new byte[1024];
            int j = 0;
            while ((j = in.read(b)) != -1)
            {
                out.write(b, 0, j);
            }
            out.flush();
            File file = new File(path);
            if(file.exists() && file.length() == 0)
                return false;
            return true;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            if("FileNotFoundException".equals(e.getClass().getSimpleName()))
                System.err.println("download FileNotFoundException");
            if("SocketTimeoutException".equals(e.getClass().getSimpleName()))
                System.err.println("download SocketTimeoutException");
            else
                e.printStackTrace();
        } finally{

            if(out != null)
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            if(in != null)
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
        return false;
    }

    /**
     * 采集
     * @param url：指定URL
     * @param times：如果采集失败，采集最少次数（2次）
     * @return
     */
    public static boolean download(String urlstr, String path)
    {
        if(urlstr == null || "".equals(urlstr.trim()))
            return false;


        InputStream in = null;
        FileOutputStream out = null;
        try
        {
            System.out.println("download url " + urlstr);
            URL url = new URL(urlstr);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //connection.setReadTimeout(5000);//jdk 1.5换成这个,读操作超时
            connection.setDoOutput(true);

            out = new FileOutputStream(path);
            in = connection.getInputStream();
            byte b[] = new byte[1024];
            int j = 0;
            while ((j = in.read(b)) != -1)
            {
                out.write(b, 0, j);
            }
            out.flush();
            File file = new File(path);
            if(file.exists() && file.length() == 0)
                return false;
            return true;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            if("FileNotFoundException".equals(e.getClass().getSimpleName()))
                System.err.println("download FileNotFoundException");
            if("SocketTimeoutException".equals(e.getClass().getSimpleName()))
                System.err.println("download SocketTimeoutException");
            else
                e.printStackTrace();
        } finally{

            if(out != null)
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            if(in != null)
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
        return false;
    }
    
    public static InputStream getUrlImg(String URLName) throws Exception
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int HttpResult = 0; // 服务器返回的状态
        URL url = new URL(URLName); // 创建URL
        URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码urlconn.connect();
        HttpURLConnection httpconn = (HttpURLConnection) urlconn;
        HttpResult = httpconn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
        { // 不等于HTTP_OK说明连接不成功
            System.out.print("连接失败！");
        } else
        {
            int filesize = urlconn.getContentLength(); // 取数据长度
            System.out.println(filesize);
            BufferedInputStream bis = new BufferedInputStream(urlconn.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] buffer = new byte[1024]; // 创建存放输入流的缓冲
            int num = -1; // 读入的字节数
            while (true)
            {
                num = bis.read(buffer); // 读入到缓冲区
                if (num == -1)
                {
                    bos.flush();
                    break; // 已经读完
                }
                bos.flush();
                bos.write(buffer, 0, num);
            }
            bos.close();
            bis.close();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        return bis;
    }
    
    public static String getPosx(String jspath, String encryptionExponent, String modulus, String id, String pw) throws FileNotFoundException, ScriptException, NoSuchMethodException{
    	
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");  
        Bindings bind = engine.createBindings();  
        //bind.put("factor", 2);    //这里绑定一个factor的值为2.  
        engine.setBindings(bind,ScriptContext.ENGINE_SCOPE);  
        //Scanner input = new Scanner(System.in);  
        //while(input.hasNext())  
        //{  
            //int first = input.nextInt();  
            //int sec = input.nextInt();  
            //System.out.println("输入的参数为："+ first+ " , " + sec);  
            engine.eval(new FileReader(jspath));  
            String result = null;
            if(engine instanceof Invocable)  
            {  
                Invocable in = (Invocable)engine;  
                result = (String)in.invokeFunction("GetPosx", encryptionExponent, modulus,id, pw);  
                //System.out.println("运算结果为：" + result);  
            }  
        //}  
    	
    	return result;
    }	/**
	 * 适合多线程的HttpClient,用httpClient4.2.1实现
	 * @return DefaultHttpClient
	 */
	public static DefaultHttpClient getHttpClient()
	{  		
	    // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9 
		HttpParams params = new BasicHttpParams(); 
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); 
	    HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1"); 
	    HttpProtocolParams.setUseExpectContinue(params, true); 	  

	    //设置连接超时时间 
	    int REQUEST_TIMEOUT = 10*1000;	//设置请求超时10秒钟 
		int SO_TIMEOUT = 10*1000; 		//设置等待数据超时时间10秒钟 
		//HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
		//HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
	    params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);  
	    params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT); 
	  
		//设置访问协议 
		SchemeRegistry schreg = new SchemeRegistry();  
		schreg.register(new Scheme("http",80,PlainSocketFactory.getSocketFactory())); 
		schreg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory())); 	  
		
		//多连接的线程安全的管理器 
		PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schreg);
		pccm.setDefaultMaxPerRoute(20);	//每个主机的最大并行链接数 
		pccm.setMaxTotal(200);			//客户端总并行链接最大数    
		
		DefaultHttpClient httpClient = new DefaultHttpClient(pccm, params);  
		return httpClient;
	}
}