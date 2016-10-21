//存放主要交互逻辑js代码
//javascript 模块化
//根据练习，使用js模块化工程
var stock = {
	URL : {
		loginUrl : function () {
			return '/glsc/login';
		},
		automatic : function () {
			return '/glsc/automatic';
		}
	},
	login : function(data){
		switch(data){
//			手动登录状态
			case 'mechanization': 
				alert('mechanization');
				//拼接参数
				//post
				var params = 'stckaccount=72000153&pw=926498';
				
				$.post(stock.URL.loginUrl(), params, function (result) {
					alert(result['message']);
					//登录成功
					if(result['success']){
						
					}
				});
				break;
//			自动登录状态
			case 'automatic' :
				alert('automatic');
				$.get(stock.URL.automatic(), {}, function (result) {
					alert(result['message']);
					//登录成功
					if(result['success']){
						
					}
				});
				
				break;
			default : 
				alert('default');
		}
	}
}