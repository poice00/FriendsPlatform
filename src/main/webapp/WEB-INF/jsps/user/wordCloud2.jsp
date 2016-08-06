<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0032)http://xq.ibaihe.com/index/basic -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<title>西安高校非诚勿扰标签云</title>
<link rel="stylesheet" href="/FriendsPlatform/resources/baihe_files/jquery.Jcrop.css">
<link href="/FriendsPlatform/resources/baihe_files/lovecss.css" rel="stylesheet" type="text/css">
<link href="/FriendsPlatform/resources/baihe_files/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"src="/FriendsPlatform/resources/baihe_files/jquery.min.js"></script>
<style>
	.bubble circle{
		stroke: black;
		stroke-width: 2px;
	}

	.bubble text{
		fill: black;
		font-size: 14px;
		font-family: arial;
		text-anchor: middle;
	}

	</style>
<script src="/FriendsPlatform/resources/wordCloud/d3.min.js" charset="utf-8"></script>  
</head>

<body class="noon" style="background:url(<%=request.getContextPath()%>/resources/baihe_files/bj_body.jpg);">
	
	<!-- 左侧导航栏 -->
		<jsp:include page="/WEB-INF/jsps/common/left_nav.jsp"></jsp:include>
	<div id="content" style="text-align:center;">

	</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/effect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/jquery.jqtransform.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/index.basic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/basic.common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/jquery.artDialog.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/swfupload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/swfupload.queue.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/fileprogress.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/jquery.Jcrop.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/notice.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/baihe_files/que.js"></script>

</body>
</html>
<script>
	var width  = 800;	//SVG绘制区域的宽度
	var height = 800;	//SVG绘制区域的高度
			
	var svg = d3.select("div#content")			//选择<body>
			.append("svg")			//在<body>中添加<svg>
			.attr("width", width)	//设定<svg>的宽度属性
			.attr("height", height);
	
	var pack = d3.layout.pack()
	    	.size([ width, height ])
	    	.sort(null)
	    	.value(function(d){
	    		return d.weight;
	    	})
		.padding(2);
	
	d3.json("/FriendsPlatform/resources/wordCloud/wordCloud2.json",function(error, root){

		var nodes = pack.nodes(root);
		
		console.log(nodes);
			
		var color = d3.scale.category20c();

		var bubbles = svg.selectAll(".bubble")
			.data(nodes.filter(function(d) { 
				return !d.children; 
			}))
			.enter()
			.append("g")
			.attr("class","bubble");

		bubbles.append("circle")
			.style("fill",function(d,i){
				return color(i);
			})
			.attr("cx",function(d){ return d.x; })
			.attr("cy",function(d){ return d.y; })
			.attr("r",function(d){ return d.r; });

		bubbles.append("text")
			.attr("x",function(d){ return d.x; })
			.attr("y",function(d){ return d.y; })
			.text(function(d){
				return d.name;
			});

	});


		
		
</script>
