<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>证券登录</title>
	<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/stock.js"></script>
</head>
<body>

	<input type="text" id="stckaccount" />
	<br>
	<input type="text" id="stckpw" />
	<input type="submit" value="登录" onclick="stock.login('mechanization');" />
	<input type="submit" value="自动登录" onclick="stock.login('automatic');" />
	<script type="text/javascript">
// 		stock.login('mechanization');
	</script>
</body>
</html>