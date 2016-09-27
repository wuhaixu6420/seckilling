<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>注册</title>
	<%@include file="/WEB-INF/views/common/head.jsp" %>
</head>
<body>
	<form action="reg" method="post" style="text-align: center;">
		登录名：<input id="form-account" type="text" name="username" placeholder="登录名"/><br>
		密码：<input id="form-pwd" type="password" name="password" placeholder="密码"/><br>
		确认密码：<input id="pwdRepeat" type="password" name="verifypassword" placeholder="确认密码"/><br>
		手机号：<input id="form-phone" type="text" name="phone" placeholder="手机号"/><br>
		验证码：<input id="authCode" type="text" name="authcode" placeholder="验证码"/><br>
		手机验证码：<input id="phoneCode" type="text" name="mobileCode" placeholder="手机验证码"/><br>
		<button type="submit" class="btn-register">立即注册</button>
	</form>
</body>
</html>