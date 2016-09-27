package com.zq.fin.seckill.dto.reg;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.zq.fin.seckill.common.BaseGroup.GroupVerify;

public class RegModelVerify {

	/**
	 * 登录名
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入登录名")
	@Size(groups={GroupVerify.class}, min = 6, max = 16, message = "登录名要6-16位")
	private String username;
	
	/**
	 * 密码
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入密码")
	@Size(groups={GroupVerify.class}, min = 6, max = 16, message = "确认密码要6-16位")
	private String password;
	
	/**
	 * 确认密码
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入确认密码")
	@Size(groups={GroupVerify.class}, min = 6, max = 16, message = "确认密码要6-16位")
	private String verifypassword;
	
	/**
	 * 手机号
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入手机号")
	@Size(groups={GroupVerify.class}, min = 11, max = 15, message = "手机号要11位")
	private String phone;
	
	/**
	 * 验证码
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入验证码")
	@Size(groups={GroupVerify.class}, min = 4, max = 6, message = "验证码要4-6位")
	private String authcode;
	
	/**
	 * 手机验证码
	 */
	@NotBlank(groups={GroupVerify.class}, message = "请输入手机验证码")
	@Size(groups={GroupVerify.class}, min = 4, max = 6, message = "手机验证码要4-6位")
	private String mobileCode;
	
	/**
	 * 跳转链接
	 */
	private String redirectUrl;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifypassword() {
		return verifypassword;
	}

	public void setVerifypassword(String verifypassword) {
		this.verifypassword = verifypassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}
	
}
