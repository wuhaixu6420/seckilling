<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
这是编辑页面
	<span id="title">lalala</span>
	<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript">
		$.get(
				"/news/nowList",
				function(result){
					var obj = JSON.parse(result);
					console.log(obj);
					for(var i=0; i<obj.length;i++){
						console.log(obj[i]['classification']);
						$("#title").html(obj[i]['classification']);
					}
				});
	</script>
</body>
</html>