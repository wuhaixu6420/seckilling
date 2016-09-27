package com.zq.fin.seckill.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	
	/**
	 * @param Format	   转化模式  yyyy-MM-dd
	 * @return
	 */
	public static final String Date_long2String(String Format){
		if(ObjectUtil.isEmpty(Format)){
			Format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(Format);
		
		return sdf.format(new Date(System.currentTimeMillis()));
		
	}
	
	/**
	 * 转化模式  yyyy-MM-dd
	 * @param str	输入参数yyyyMMdd
	 */
	public static final String MMdd2MM_dd(String str){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(format.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转化模式  yyyyMMdd
	 * @param str	输入参数yyyy-MM-dd
	 */
	public static final String MM_dd2MMdd(String str){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(format.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转化模式  yyyy-MM-dd
	 * @param str	输入参数yyyyMMdd
	 */
	public static final String time2string(String str){
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy-MM-dd");
			if(str.length()==5){
				str = "0" + str;
			}
				
			return sdfymd.format(new Date()) + " " + sdf.format(format.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final Date string2Date(String str){
		
		try {
			if(str == null || str.length() == 0 || "".equals(str) || "null".equals(str)){
				return null;
			}
			//转string
			String strDate = String.valueOf(str);
			//类型转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(ObjectUtil.isNotEmpty(strDate)){
				return new java.util.Date(sdf.parse(strDate).getTime());
			}else{
				return null;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String date2String(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(date);  
	}
	
	

	/**
	 * @param Format	   转化模式  yyyy-MM-dd
	 * @return
	 */
	public static final Date string2Date(String str, String Format){
		
		try {
			if(str == null || str.length() == 0 || "".equals(str) || "null".equals(str)){
				return null;
			}
			//转string
			String strDate = String.valueOf(str);
			//类型转换
			SimpleDateFormat sdf = new SimpleDateFormat(Format);
			if(ObjectUtil.isNotEmpty(strDate)){
				return new java.util.Date(sdf.parse(strDate).getTime());
			}else{
				return null;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 返回当前日期时间
	 */
	public static String getCurrentTime(String Format){
		
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
		if(ObjectUtil.isEmpty(Format)){
			Format = "yyyy-MM-dd HH:mm:ss";
		}
		sdf.applyPattern(Format);
	 return sdf.format(System.currentTimeMillis());
	}

	public static int compare_date(String DATE1, String DATE2) {
		 
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 try {
	     Date dt1 = df.parse(DATE1);
	     Date dt2 = df.parse(DATE2);
	     if (dt1.getTime() > dt2.getTime()) {
		  return 1;
	     } else if (dt1.getTime() < dt2.getTime()) {
		  return -1;
	     } else {
		  return 0;
	     }
	 } catch (Exception exception) {
	     exception.printStackTrace();
	 }
	 return 0;
    }
	 
	 public static int compare_month(String DATE1, String DATE2) {
		 
		 DateFormat df = new SimpleDateFormat("yyyy-MM");
		 try {
		     Date dt1 = df.parse(DATE1);
		     Date dt2 = df.parse(DATE2);
		     if (dt1.getTime() > dt2.getTime()) {
			  return 1;
		     } else if (dt1.getTime() < dt2.getTime()) {
			  return -1;
		     } else {
			  return 0;
		     }
		 } catch (Exception exception) {
		     exception.printStackTrace();
		 }
		 return 0;
	    }
	
	
	
	/**
	 * 返回当前Linux 时间戳
	 */
	public static Long getLinuxCurrentTime(){
		
	 return System.currentTimeMillis()/1000;
	}

	/**
	 * 返回当前日期时间
	 */
	public static Timestamp getCurrentTimestamp(){
		
	 return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * LINUX 时间戳转日期时间（Timestamp）
	 * 
	 */
	public static Timestamp linux2Timestamp(String time){
		
	 return Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(new Long(time)*1000)));
	}
	
	/**
	 * 取得目标日期的前几天或后几天
	 * 
	 */
	public static String getLastday(String date, int days){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dNow = null;
		try {
			dNow = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		
		calendar.add(Calendar.DAY_OF_MONTH, days);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		return defaultStartDate;
	}
	
	/**
	 * 取得目标日期的前几月或后几月
	 * 
	 */
	public static String getLastmonth(String date, int months){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dNow = null;
		try {
			dNow = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		
		calendar.add(Calendar.MONTH, months);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一月的时间
		String defaultStartDate = sdf.format(dBefore);    //格式化前一月
		return defaultStartDate;
	}
	
	
	/**
	 * 取得指定日期的上个月最后一天
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String getLastmonthLastday(String date){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dt.setDate(0);
		
		return date2String(dt);
	}
	
	/**
	 * 取得指定日期的最后一天
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthLastday(String date){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dt.setMonth(dt.getMonth()+1);
		dt.setDate(0);
		
		return date2String(dt);
	}
	
	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param date
	 *	     当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin
	 *	     开始时间 00:00:00
	 * @param strDateEnd
	 *	     结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否是周末
	 * @return
	 */
	public static boolean isWeekend(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(week ==6 || week==0){//0代表周日，6代表周六
			return true;
		}
		return false;
	}
	
	
	/**
	 * 加减日期
	 * @param date
	 * @param type
	 * @param sum
	 * @return
	 * @throws Exception
	 */
	public static String addYMD(String date, String type, int sum) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt=sdf.parse(date);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		if("year".equals(type)){
//			rightNow.add(Calendar.YEAR,-1);//日期减1年
			rightNow.add(Calendar.YEAR, sum);//日期减1年
		} else if("month".equals(type)){
//			rightNow.add(Calendar.MONTH,3);//日期加3个月
			rightNow.add(Calendar.MONTH, sum);//日期加3个月
		} else if("day".equals(type)){
//			rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
			rightNow.add(Calendar.DAY_OF_YEAR, sum);//日期加10天
		}
		Date dt1=rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}
	
	/**
	 * 判断两个日期是否相同
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(String str1, String str2) {
		
		Date date1 = string2Date(str1);
		Date date2 = string2Date(str2);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
			 .get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
			 && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth
			 && cal1.get(Calendar.DAY_OF_MONTH) == cal2
				.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
}
