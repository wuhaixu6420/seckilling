<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>相册</title>
	<%@ include file="/WEB-INF/views/common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-conter">
				<h2>个人相册·默认</h2>
			</div>
			<div class="panel-body">
				<form action="image" method="post" role="form" enctype="multipart/form-data">
					上传：<input type="file" id="images" name="images" /><br/>
					<input type="submit" />
				</form>
				
				<c:forEach var="sk" items="${dataImage.data}">
					<div class="col-sm-12 col-md-3 mod">
						<div class="mod-header">
							<img src="${sk.url}" alt="${sk.name}" style="width: 100%"/>
						</div>
						<div class="mod-footer">
							<a class="btn-mini" href="#">查看</a>
							<a class="btn-mini" href="#">删除</a>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabe2">Image Upload</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="form">
						<div class="form-group">
							<label for="image" class="col-xs-2 control-label">上传:</label>
							<div class="col-xs-4">
								<input type="file" id="images" name="images"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="upload">上传</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>