package com.zq.fin.seckill.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcUtils {

	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
		
	public static void loadConf(String path) throws Exception {
		
		if(StringUtil.isEmpty(path)) throw new Exception();
		
		Properties prop = new Properties();
		InputStream in = new FileInputStream(path);
		prop.load(in);
		
	  //获取数据库连接驱动
		driver = prop.getProperty("jdbc.driver");
		//获取数据库连接URL地址
		url = prop.getProperty("jdbc.url");
		//获取数据库连接用户名
		username = prop.getProperty("jdbc.username");
		//获取数据库连接密码
		password = prop.getProperty("jdbc.password");
		
		Class.forName(driver);
		
	}
	
 /**
	* @Method: getConnection
	* @Description: 获取数据库连接对象
	*
	* @return Connection数据库连接对象
	* @throws SQLException
	*/ 
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username,password);
	}
	
	//设定是否自动提交
	public static void setAutoCommit(Connection conn, boolean isAutoCommit) throws SQLException {
		conn.setAutoCommit(isAutoCommit);
	}
	
	public static Statement createStatement(Connection conn) throws SQLException {
		return conn.createStatement();
	}
	
	public static void commit(Connection conn) throws SQLException {
		conn.commit();
	}
	
	public static void rollback(Connection conn) throws SQLException {
		conn.rollback();
	}
	
	/**
	* @Method: release
	* @Description: 释放资源，
	*	 要释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
	*
	* @param conn
	* @param st
	* @param rs
	*/ 
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(rs!=null){
			try{
				//关闭存储查询结果的ResultSet对象
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(st!=null){
			try{
				//关闭负责执行SQL命令的Statement对象
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try{
				//关闭Connection数据库连接对象
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}