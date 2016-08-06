<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0032)http://matching.baihe.com/twelve -->
<html lang="zh-CN" style="overflow: hidden; height: 100%;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>心灵匹配 </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/jquery.fullPage.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/indexStyle.css">
<script src="<%=request.getContextPath()%>/resources/js/baihe/tongji-1.0.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/resources/js/baihe/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/baihe/jquery-ui-1.10.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/baihe/jquery.fullPage.min.js"></script>
<script>
$(document).ready(function() {
	$.fn.fullpage({
		slidesColor: ['#010715', '#ffffff', '#f5f6f9', '#c1c7d1' ,'#ffffff'],
		anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
		// navigation: true,
		//css3: true,
		easing: 'easeInExpo',
		afterLoad: function(anchorLink, index){
			if(index == 1){
				indexOne();
				baihe.bhtongji.tongji({'event':'3','spm':'4.44.155.262.1482'});
			}
			if(index == 2){
				$('.section2 .tit_2 img').delay(400).animate({
					opacity: 1
				}, 1500, 'easeOutExpo');
				$('.section2 .tit_22 img').delay(800).animate({
					opacity: 1
				}, 1500, 'easeOutExpo');
				$('.section2 .tit_23 img').delay(1200).animate({
					opacity: 1
				}, 1500, 'easeOutExpo');
				$('.section2 .pic_2 img').delay(1600).animate({
					opacity: 1
				}, 1500, 'easeOutExpo');
				baihe.bhtongji.tongji({'event':'3','spm':'4.44.156.262.1484'});
			}
			if(index == 3){
				$('.lfBox li').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[0] + 'px',
						top: $arr[1] + 'px',
						opacity:1
					}, 400);
				});
				$('.rtBox img').addClass('tsf')
				baihe.bhtongji.tongji({'event':'3','spm':'4.44.157.262.1485'});
			}
			if(index == 4){
				$('.section4 .mb').fadeIn(1000);
				baihe.bhtongji.tongji({'event':'3','spm':'4.44.158.262.1486'});
			}
			if (index == '5') {
				$('.bottomC').fadeIn(1000);
				baihe.bhtongji.tongji({'event':'3','spm':'4.44.159.262.1487'});
			};
		},
		onLeave: function(index, direction){
			if(index == '1'){
				$('.section1 .tit_1').delay(100).animate({
					opacity: '0',
					top:50
				}, 2000, 'easeOutExpo');
				$('.section1 .tit_11').delay(200).animate({
					opacity: '0',
					top:50
				}, 2000, 'easeOutExpo');
				$('.section1 .btn_1').delay(300).animate({
					opacity: '0',
					top:50
				}, 2000, 'easeOutExpo');
				$('.section1 .down').delay(400).animate({
					opacity: '0'
				}, 2000, 'easeOutExpo');
			}
			if(index == '2'){
				$('.section2 .tit_2 img').animate({
					opacity: 0
				}, 800, 'easeOutExpo');
				$('.section2 .tit_22 img').animate({
					opacity: 0
				}, 800, 'easeOutExpo');
				$('.section2 .tit_23 img').animate({
					opacity: 0
				}, 800, 'easeOutExpo');
				$('.section2 .pic_2 img').animate({
					opacity: 0
				}, 800, 'easeOutExpo');
			}
			if(index == '3'){
				$('.lfBox li').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[2] + 'px',
						top: $arr[3] + 'px',
						opacity:0
					}, 400);
				});
				$('.rtBox img').removeClass('tsf')
			}
			if(index == '4'){
				$('.section4 .mb').fadeOut(1000);
			}
			if (index == '5') {
				$('.bottomC').fadeOut(1000);
			};
		}
	});
	indexOne();
});
function indexOne(){
	$('.section1 .tit_1').delay(200).animate({
		opacity: '1',
		top:0
	}, 2000, 'easeOutExpo');
	$('.section1 .tit_11').delay(800).animate({
		opacity: '1',
		top:0
	}, 2000, 'easeOutExpo');
	$('.section1 .dtCent').delay(1400).animate({
		opacity: '1',
		top:0
	}, 2000, 'easeOutExpo');
	$('.section1 .down').delay(1800).animate({
		opacity: '1'
	}, 2000, 'easeOutExpo');
	}
	function indexTwo(zAndO,timeI){
		$('.section2 .tit_2').delay(400).animate({
			opacity: zAndO
		}, timeI, 'easeOutExpo');
		$('.section2 .tit_22').delay(600).animate({
			opacity: zAndO
		}, timeI, 'easeOutExpo');
		$('.section2 .tit_23').delay(800).animate({
			opacity: zAndO
		}, timeI, 'easeOutExpo');
		$('.section2 .pic_2').delay(1000).animate({
			opacity: zAndO
		}, timeI, 'easeOutExpo');
	}

</script>
</head>
<body style="overflow: hidden; height: 100%;"><div id="superContainer" style="top: 0px;">
<div class="section section1 active table" data-anchor="page1" style="height: 935px; background-color: rgb(1, 7, 21);"><div class="tableCell" style="height:935px;">
	<img class="tit_1" src="<%=request.getContextPath()%>/resources/css/baihe/img/h1_tit1.png" style="opacity: 1; top: 0px;">
	<img class="tit_11" src="<%=request.getContextPath()%>/resources/css/baihe/img/h1_tit2.png" style="opacity: 1; top: 0px;">
	<a style="display:none" class="btn" href=""><img class="btn_1" src="<%=request.getContextPath()%>/resources/css/baihe/img/btn_1.gif"></a>
	<div class="dtCent" style="opacity: 1; top: 0px;">
		<div class="h1">我是一个善于</div>
		<div class="btnBox">
		    <input style="display: none" type="radio" name="question" value="A"> 
			<input style="display: none" type="radio" name="question" value="B">
			<div class="btnBl">
				<a href="/FriendsPlatform/twelve" class="btL1">理性处理问题的人</a>
			</div>
			<div class="btnBr">
				<a href="/FriendsPlatform/twelve" class="btR1">表达同情与关心的人</a>
			</div>
		</div>
	</div>
	<div class="gong">只需12题，快速了解你的恋爱类型</div>
	<div class="down msgOn" style="opacity: 1;"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/down_1.gif"></div>
</div></div>
<div class="section section2 table" data-anchor="page2" style="height: 935px; background-color: rgb(255, 255, 255);"><div class="tableCell" style="height:935px;">
	<div class="tit_2">
		<img src="<%=request.getContextPath()%>/resources/css/baihe/img/tit_2.gif">
	</div>
	<div class="tit_22">
		<img src="<%=request.getContextPath()%>/resources/css/baihe/img/tit_22.gif">
	</div>
	<div class="tit_23">
		<img src="<%=request.getContextPath()%>/resources/css/baihe/img/tit_23.gif">
	</div>
	<div class="pic_2">
		<img src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_2.gif">
	</div>
	<div class="down msgOn"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/down_2.gif"></div>
</div></div>
<div class="section section3 table" data-anchor="page3" style="height: 935px; background-color: rgb(245, 246, 249);"><div class="tableCell" style="height:935px;">
	<img class="tit_3" src="<%=request.getContextPath()%>/resources/css/baihe/img/tit_3.jpg">
	<div class="lfcenten">
	<ul class="lfBox">
		<li class="pic31" rel="0,0,-200,-200">
			<img src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_31.jpg">
		</li>
		<li class="pic32" rel="234,0,434,-200">
			<img src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_32.jpg">
		</li>
		<li class="pic34" rel="0,239,-200,439">
			<img src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_34.jpg">
		</li>
		<li class="pic33" rel="234,239,434,439">
			<img src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_33.jpg">
		</li>
	</ul>
	</div>
	<div class="down msgOn"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/down_3.gif"></div>

</div></div>
<div class="section section4 table" data-anchor="page4" style="height: 935px; background-color: rgb(193, 199, 209);"><div class="tableCell" style="height:935px;">
	<div class="warp">
		<img class="mb" src="<%=request.getContextPath()%>/resources/css/baihe/img/pic_4.jpg">
	</div>
</div></div>
<div class="section section5 table" data-anchor="page5" style="height: 935px; background-color: rgb(255, 255, 255);"><div class="tableCell" style="height:935px;">
	<div class="bottomC">
		<p class="mgTop">女作家型与男学者型是感性与理性的组合，在相互钦慕和帮助中</p>
		<p>“执子之手，与子偕老”</p>
		<a class="csBtn" href="/FriendsPlatform/twelve">开始测试</a>
		<div class="foot">SICD   |   版权所有 @ 2014</div>
	</div>
</div></div>


</div></body></html>