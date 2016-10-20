<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>首页</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/static/css/index.css" type="text/css">
<link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-minicolors/2.2.4/jquery.minicolors.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-minicolors/2.2.4/jquery.minicolors.min.js"></script>
<script type="text/javascript">
var browser={
	versions:function(){
		var u = navigator.userAgent, app = navigator.appVersion;
		return {
			trident: u.indexOf('Trident') > -1, //IE内核
			presto: u.indexOf('Presto') > -1, //opera内核
			webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
			mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			android: u.indexOf('Android') > -1, //android终端或者uc浏览器
			iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
			iPad: u.indexOf('iPad') > -1, //是否iPad
			webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		};
	}(),
	language:(navigator.browserLanguage || navigator.language).toLowerCase()
}

</script><script src="/static/js/index.js"></script>
</head>

<body>

<div id="index_pageChose">
	<div id="1pageChose" class="page1 pageChose adjustAuto page1_dot active"></div>
	<div id="2pageChose" class="page2 pageChose adjustAuto page1_dot"></div>
	<div id="3pageChose" class="page3 pageChose adjustAuto page1_dot"></div>
	<div id="4pageChose" class="page4 pageChose adjustAuto page1_dot"></div>
	<div id="5pageChose" class="page5 pageChose adjustAuto page1_dot"></div>
	<div id="6pageChose" class="page6 pageChose adjustAuto page1_dot"></div>
</div>
<div id="indexDiv">	
	<div id="page1" class="indexPage row">
		<div class="page1top adjustAuto"></div>
		<div class="page1img1 page1img adjustAuto"><img src="/img/index/1.png"></div>
		<div class="page1img2 page1img adjustAuto"><img src="/img/index/2.png"></div>
		<div class="page1img3 page1img adjustAuto"><img src="/img/index/3.png"></div>
		<div class="page1img4 page1img adjustAuto"><img src="/img/index/4.png"></div>
		<div class="page1Right adjustAuto">
		 	<div class="logo adjustAuto page1logo">此处放logo</div>
			<div class="page1text1 adjustAutoText">贴近你的创意</div>
			<div class="page1text2 adjustAutoText">从未想象的简单与有趣，给你全新的线上设计体验，随时随地开启设计，呈现你的创意和想法。</div>
			<a href="/login" target="_blank">
				<span class="sign adjustAutoText page1-div1">注&nbsp;&nbsp;&nbsp;册</span>
			</a> 
			<a href="/login" target="_blank">
				<span class="login adjustAutoText page1-div2">登&nbsp;&nbsp;&nbsp;录</span>
			</a>			
		</div>
		<div class="page1bottom adjustAuto"></div>
	</div>
	<div id="page2" class="indexPage row">
		<div class="page2bg adjustAuto">
			<div class="page2text1 adjustAutoText">创造丰富的作品</div>
			<div class="page2text2 adjustAutoText">不让复杂的设计软件拘束你的创意，用最简单有趣的方式制作海报、ppt、信息图、贺卡、名片等所有你想要的一切。</div>
			<div class="page2img1 pageImg adjustAuto"><img src="/img/index/22.png"></div>
			<div class="page2img8 pageImg adjustAuto"><img src="/img/index/1(1).png"></div>
			
			<!-- <div class="page2img1 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_2.png" /></div>
			<div class="page2img2 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_3.png" /></div>
			<div class="page2img3 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_4.png" /></div>
			<div class="page2img4 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_5.png" /></div>
			<div class="page2img5 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_6.png" /></div>
			<div class="page2img6 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_7.png" /></div>
			<div class="page2img7 pageImg adjustAuto"><img src="http://eyuanku.qiniudn.com/2_8.png" /></div>
			
			<div class="page2imgDiv1 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv2 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv3 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv4 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv5 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv6 pageImgDiv adjustAuto"></div>
			<div class="page2imgDiv7 pageImgDiv adjustAuto"></div> -->
		</div>
	</div>
	
	<!--第3页-->
	<div id="page3" class="indexPage row">
		<div class="page3bg adjustAuto">
			<div class="page3text1 adjustAutoText">简单的拖拉拽</div>
			<div class="page3text2 adjustAutoText">搜索你需要的素材，通过拖拉拽即可完成素材的挑选与使用，这一切不需要下载任何插件或文件，让你更专注于自己的创意。</div>
			<div class="page3img1 pageImg adjustAuto"><img src="/img/index/1(2).png"></div>
			<div class="page3img2 pageImg adjustAuto"><img src="/img/index/2(1).png"></div>
			<div class="page3img3 pageImg adjustAuto"><img src="/img/index/3(1).png"></div>
			<div class="page3img4 pageImg adjustAuto"><img src="/img/index/4(1).png"></div>
		</div> 	
	</div>
	
	<!--第4页-->
	<div id="page4" class="indexPage row">
		<div class="page4bg adjustAuto">
			<div class="page4text1 adjustAutoText">免费与尽情</div>
			<div class="page4text2 adjustAutoText">创客贴为你提供大量免费的无背景图片、高质量的摄影图片、网络字体、背景、模板等素材，方便你自由创意。上传自己的素材到云端，无论何时何地都能使用，再也不用担心找不到素材。</div>
			<div class="page4img1 pageImg adjustAuto"><img src="/img/index/1(3).png"></div>
			<div class="page4img2 pageImg adjustAuto"><img src="/img/index/2(2).png"></div>
			<div class="page4img5 pageImg adjustAuto"><img src="/img/index/5(1).png"></div>
			<div class="page4img6 pageImg adjustAuto"><img src="/img/index/6(1).png"></div>
			<div class="page4img7 pageImg adjustAuto"><img src="/img/index/7.png"></div>
			<div class="page4img8 pageImg adjustAuto"><img src="/img/index/8.png"></div>
			<div class="page4img9 pageImg adjustAuto"><img src="/img/index/9.png"></div>
			<div class="page4img10 pageImg adjustAuto"><img src="/img/index/10.png"></div>
		</div>
	</div>
	
	<div id="page5" class="indexPage row">
		<div class="page6bottom adjustAuto"></div> 
		<div class="page6text1 adjustAutoText">PRINT &amp; SHARE<br>输出分享
		</div>
		<div class="page6text2 adjustAutoText">你可以分享设计到社交网络，富媒体的设计分享，让创意的展现不再是二维的世界；也可以输出成格式文件，平台为你提供高清的png、pdf格式输出，无论是传输还是打印印刷都将轻松实现。
		</div> 
		<div class="page6img1 pageImg adjustAuto"><img src="/img/index/1(4).png"></div>		
		<div class="page6img3 pageImg adjustAuto"><img src="/img/index/3(2).png"></div>
		<div class="page6img2 pageImg adjustAuto"><img src="/img/index/2(3).png"></div>
		<div class="page6img4 pageImg adjustAuto"><img src="/img/index/4(2).png"></div>
		<div class="page6img5 pageImg adjustAuto"><img src="/img/index/5(2).png"></div>		 		
	</div>
	<div id="page6" class="indexPage row">
		<div class="page9top adjustAuto"></div>
		<div class="page9bottom adjustAuto"></div>
		<div class="page9img5 page9img page9img11 adjustAuto page7img1"><img src="/img/index/5(3).png"></div>
		<div class="page9img6 page9img adjustAuto page7img2"><img src="/img/index/6(2).png"></div>
		<div class="page9img1 page9img adjustAuto page7img3"><img src="/img/index/1(5).png"></div>
		<div class="page9img2 page9img adjustAuto page7img4"><img src="/img/index/2(4).png"></div>
		<div class="page9img3 page9img adjustAuto page7img5"><img src="/img/index/3(3).png"></div>
		<div class="page9img4 page9img adjustAuto page7img6"><img src="/img/index/4(3).png"></div>
		<a href="#/index/login.do"><span class="page9img5 page9img adjustAuto page7img-div1"><img src="/img/index/5(3).png"></span></a>
		<a href="#/index/login.do"><span class="page9img6 page9img adjustAuto page7img-div2"><img src="/img/index/6(2).png"></span></a>
		<a href="#/index/login.do"><span class="page9img7 page9img adjustAuto page7img-div3"><img src="/img/index/7(1).png"></span></a>
		<div style="position:absolute;bottom:0px;width:100%">
		<div class="footerText" style="color: white;">
			<span>www.lonice.com.cn&copy;2016-2017</span>
			<span>皖ICP备16006624号</span>
		</div>
		<div></div>
	</div>
</div>

</div></body></html>