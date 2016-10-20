package com.zq.fin.seckill.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GlscLoginServiceModel {

	/**
	 * 用户id
	 */
	private Long userid;
	
	/**
	 * 证券账户
	 */
	private String Stckaccount;
	
	/**
	 * 证券密码
	 */
	private String pw;
	
	/**
	 * cookie
	 */
	private String cookie;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getStckaccount() {
		return Stckaccount;
	}

	public void setStckaccount(String stckaccount) {
		Stckaccount = stckaccount;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}
