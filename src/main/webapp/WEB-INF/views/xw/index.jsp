<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="/froala/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="/froala/css/froala_editor.min.css" rel="stylesheet" type="text/css">


    <style>
        body {
            text-align: center;
        }

        section {
            width: 80%;
            margin: auto;
            text-align: left;
        }
    </style>
</head>
<body>
这是展示页面
	<span id="title">lalala</span>
	
<!-- 	<section id="editor"> -->
      <div id='edit' style="margin-top: 30px;">
          <img class="fr-fir" src="/froala/img/old_clock.jpg" alt="Old Clock" width="300"/>

          <h1>Click and edit</h1>

          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec facilisis diam in odio iaculis blandit. Nunc eu mauris sit amet purus viverra gravida ut a dui. Vivamus nec rutrum augue, pharetra faucibus purus. Maecenas non orci sagittis, vehicula lorem et, dignissim nunc. Suspendisse suscipit, diam non varius facilisis, enim libero tincidunt magna, sit amet iaculis eros libero sit amet eros. Vestibulum a rhoncus felis. Nam lacus nulla, consequat ac lacus sit amet, accumsan pellentesque risus. Aenean viverra mi at urna mattis fermentum. Curabitur porta metus in tortor elementum, in semper nulla ullamcorper. Vestibulum mattis tempor tortor quis gravida. In rhoncus risus nibh. Nullam condimentum dapibus massa vel fringilla. Sed hendrerit sed est quis facilisis. Ut sit amet nibh sem. Pellentesque imperdiet mollis libero.</p>

          <p><a href="http://google.com" title="Aenean sed hendrerit">Aenean sed hendrerit</a> velit. Nullam eu mi dolor. Maecenas et erat risus. Nulla ac auctor diam, non aliquet ante. Fusce ullamcorper, ipsum id tempor lacinia, sem tellus malesuada libero, quis ornare sem massa in orci. Sed dictum dictum tristique. Proin eros turpis, ultricies eu sapien eget, ornare rutrum ipsum. Pellentesque eros nisl, ornare nec ipsum sed, aliquet sollicitudin erat. Nulla tincidunt porta vehicula.</p>

          <p>Nullam laoreet imperdiet orci ac euismod. Curabitur vel lectus nisi. Phasellus accumsan aliquet augue, eu rutrum tellus iaculis in. Nunc viverra ultrices mollis. Curabitur malesuada nunc massa, ut imperdiet arcu lobortis sed. Cras ac arcu mauris. Maecenas id lectus nisl. Donec consectetur scelerisque quam at ultricies. Nam quis magna iaculis, condimentum metus ut, elementum metus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus id tempus nisi.</p>
      </div>
<!--   </section> -->
	
	
	<form action="/news/editOne" id="froala-form" method="post" style="margin-top: 30px;">
		<textarea name="froala" id="froala"></textarea>
		<button id="submit">提交</button>
	</form>
	
	
	
	
	<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript">
		$.get(
				"/news/nowList",
				function(result){
					var obj = JSON.parse(result);
					console.log(obj['data']);
					for(var i=0; i<obj['data'].length;i++){
						console.log(obj['data'][i]['classification']);
						$("#title").html(obj['data'][i]['classification']);
					}
				});
		
		$("#submit").click(function(){
			
		});
	</script>
	
	<script src="/froala/js/libs/jquery-1.11.1.min.js"></script>
  <script src="/froala/js/froala_editor.min.js"></script>
  <!--[if lt IE 9]>
    <script src="../js/froala_editor_ie8.min.js"></script>
  <![endif]-->
  <script src="/froala/js/plugins/tables.min.js"></script>
  <script src="/froala/js/plugins/lists.min.js"></script>
  <script src="/froala/js/plugins/colors.min.js"></script>
  <script src="/froala/js/plugins/media_manager.min.js"></script>
  <script src="/froala/js/plugins/font_family.min.js"></script>
  <script src="/froala/js/plugins/font_size.min.js"></script>
  <script src="/froala/js/plugins/block_styles.min.js"></script>
  <script src="/froala/js/plugins/video.min.js"></script>

  <script>
      $(function(){
          $('#edit').editable({inlineMode: false, alwaysBlank: true});
          $('#froala').editable({inlineMode: false, alwaysBlank: true});
      });
  </script>
</body>
</html>