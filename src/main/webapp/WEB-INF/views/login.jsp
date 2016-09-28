<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<%@include file="/WEB-INF/views/common/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="/static/loginstyles.css">
</head>
<body>
	<div class="login-background"><img src="/img/background/login.jpg"></div>
	<div class="jq22-container" style="padding-top:100px;padding-right: 16.5%;">
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
				<div class="login-form">
					<div class="sign-in-htm">
						<form action="login" method="post">
							<div class="group">
								<label for="user" class="label">用户名</label>
								<input id="user" type="text" name="username" class="input"/>
							</div>
							<div class="group">
								<label for="pass" class="label">密码</label>
								<input id="pass" type="password" name="password" class="input" data-type="password"/>
							</div>
							<div class="group">
								<input id="check" type="checkbox" class="check" checked/>
								<label for="check"><span class="icon"></span> 保持登录</label>
							</div>
							<div class="group">
								<input type="submit" class="button" value="登录"/>
							</div>
							<div class="hr"></div>
							<div class="foot-lnk">
								<a href="#forgot">忘记密码?</a>
							</div>
						</form>
					</div>
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">用户名</label>
							<input id="user" type="text" class="input"/>
						</div>
						<div class="group">
							<label for="pass" class="label">密码</label>
							<input id="pass" type="password" class="input" data-type="password"/>
						</div>
						<div class="group">
							<label for="pass" class="label">确认密码</label>
							<input id="pass" type="password" class="input" data-type="password"/>
						</div>
						<div class="group">
							<label for="pass" class="label">邮箱</label>
							<input id="pass" type="text" class="input"/>
						</div>
						<div class="group">
							<input type="submit" class="button" value="注册"/>
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">登录?</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>