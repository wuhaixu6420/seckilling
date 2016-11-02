//存放主要交互逻辑js代码
//javascript 模块化
//根据练习，使用js模块化工程
var image = {
	URL :{
		list : function(userid) {
			return '/image/' + userid + '/list';
		},
		showOne : function(id) {
			return '/image/' + id + '/detail';
		},
		deleteOne : function(id){
			return '/image/' + id + '/delete';
		},
		upload : function(userid){
			return '/image/' + userid + '/upload';
		}
	},
	list : function(userid){
		location.href=image.URL.list(userid);
	},
	detail : function(id){
		$.get(image.URL.showOne(id), {} , function(result){
			if(result && result['success']){
				//正常情况
				//查看大图
			} else {
				//失败
			}
		});
	},
	deleteOne : function(id){
		$.get(image.URL.deleteOne(id), {} , function(result){
			if(result && result['success']){
				//正常删除
				//回调list
				image.list();
			} else {
				//失败
			}
		});
	},
	upload :function(userid, Idfrom){
		Idfrom.attr('action', image.URL.upload(userid));
		Idfrom.attr('enctype', 'multipart/form-data');
		Idfrom.attr('method', 'post');
		Idfrom.submit();
//		location.href=image.URL.list(userid);
	}
}