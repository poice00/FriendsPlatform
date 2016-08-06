<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0042)http://matching.baihe.com/twelve/questions -->
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>心灵匹配 </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/indexStyle.css">
<link rel="stylesheet" href="http://matching.baihe.com/static/css/answer.css">
<script src="<%=request.getContextPath()%>/resources/js/baihe/jquery-1.8.3.min.js"></script>
</head>
<body>
	<div class="anBg">
	<form id="twelve_form" action="/FriendsPlatform/result" method="post">
		<div id="question_1" class="contAl" style="display: block;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n1.gif">				<div class="dtCent">
					<div class="h1">我是一个善于</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[1]" value="A"> 
						<input style="display: none" type="radio" name="questions[1]" value="B"> 
						<div class="btnBl">
						  <a href="#1" class="btL1">表达同情与关心的人</a>
						</div>
						<div class="btnBr">
						  <a href="#1" class="btR1">理性处理问题的人</a>
						</div>
					</div>
				</div>
				<a class="t21 prev-q" href="#1"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t1.gif"></a>
			</div>
		</div>
		<div id="question_2" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n2.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[2]" value="A"> 
						<input style="display: none" type="radio" name="questions[2]" value="B"> 
						<div class="btnBl">
						  <a href="#2" class="btL1">稳重内敛</a>
						</div>
						<div class="btnBr">
						  <a href="#2" class="btR1">活跃开朗</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#2"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_3" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n3.gif">				<div class="dtCent">
					<div class="h1">我是一个</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[3]" value="A"> 
						<input style="display: none" type="radio" name="questions[3]" value="B"> 
						<div class="btnBl">
						  <a href="#3" class="btL1">经常有新点子的人</a>
						</div>
						<div class="btnBr">
						  <a href="#3" class="btR1">脚踏实地的人</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#3"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_4" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n4.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[4]" value="A"> 
						<input style="display: none" type="radio" name="questions[4]" value="B"> 
						<div class="btnBl">
						  <a href="#4" class="btL1">感性的</a>
						</div>
						<div class="btnBr">
						  <a href="#4" class="btR1">理性的</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#4"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_5" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n5.gif">				<div class="dtCent">
					<div class="h1">按照日程表做事</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[5]" value="A"> 
						<input style="display: none" type="radio" name="questions[5]" value="B"> 
						<div class="btnBl">
						  <a href="#5" class="btL1">是我的行为习惯</a>
						</div>
						<div class="btnBr">
						  <a href="#5" class="btR1">会让我感到受约束</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#5"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_6" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n6.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[6]" value="A"> 
						<input style="display: none" type="radio" name="questions[6]" value="B"> 
						<div class="btnBl">
						  <a href="#6" class="btL1">安静的</a>
						</div>
						<div class="btnBr">
						  <a href="#6" class="btR1">有活力的</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#6"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_7" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n7.gif">				<div class="dtCent">
					<div class="h1">我常常会</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[7]" value="A"> 
						<input style="display: none" type="radio" name="questions[7]" value="B"> 
						<div class="btnBl">
						  <a href="#7" class="btL1">憧憬未来</a>
						</div>
						<div class="btnBr">
						  <a href="#7" class="btR1">关注现实</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#7"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_8" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n8.gif">				<div class="dtCent">
					<div class="h1">我是一个</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[8]" value="A"> 
						<input style="display: none" type="radio" name="questions[8]" value="B"> 
						<div class="btnBl">
						  <a href="#8" class="btL1">随心所欲的人</a>
						</div>
						<div class="btnBr">
						  <a href="#8" class="btR1">遵循计划的人</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#8"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_9" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n9.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[9]" value="A"> 
						<input style="display: none" type="radio" name="questions[9]" value="B"> 
						<div class="btnBl">
						  <a href="#9" class="btL1">富于想象的</a>
						</div>
						<div class="btnBr">
						  <a href="#9" class="btR1">注重现实的</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#9"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_10" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n10.gif">				<div class="dtCent">
					<div class="h1">我平时更喜欢</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[10]" value="A"> 
						<input style="display: none" type="radio" name="questions[10]" value="B"> 
						<div class="btnBl">
						  <a href="#10" class="btL1">按兴致做事情</a>
						</div>
						<div class="btnBr">
						  <a href="#10" class="btR1">按计划做事情</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#10"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_11" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n11.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[11]" value="A"> 
						<input style="display: none" type="radio" name="questions[11]" value="B"> 
						<div class="btnBl">
						  <a href="#11" class="btL1">喜欢独处的</a>
						</div>
						<div class="btnBr">
						  <a href="#11" class="btR1">喜欢交往的</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#11"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_12" class="contAl" style="display: none;">
			<div class="dtBox">
				<img class="t1" src="<%=request.getContextPath()%>/resources/css/baihe/img/n12.gif">				<div class="dtCent">
					<div class="h1">更适合描述我的是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[12]" value="A"> 
						<input style="display: none" type="radio" name="questions[12]" value="B"> 
						<div class="btnBl">
						  <a href="#12" class="btL1">善于感悟</a>
						</div>
						<div class="btnBr">
						  <a href="#12" class="btR1">善于推理</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#12"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		<div id="question_13" class="contAl" style="display: none;">
			<div class="dtBox">
								<div class="dtCent">
					<div class="h1">我是</div>
					<div class="btnBox">
						<input style="display: none" type="radio" name="questions[13]" value="A"> 
						<input style="display: none" type="radio" name="questions[13]" value="B"> 
						<div class="btnBl">
						  <a href="#13" class="btL1">猛汉子</a>
						</div>
						<div class="btnBr">
						  <a href="#13" class="btR1">萌妹子</a>
						</div>
					</div>
				</div>
				<a class="t2 prev-q" href="#13"><img src="<%=request.getContextPath()%>/resources/css/baihe/img/t2.gif"></a>
			</div>
		</div>
		</form>
	<div id="loading" class="loading" style="display:none">
		<img src="<%=request.getContextPath()%>/resources/css/baihe/img/loading.gif">
		<p>正在疯狂加载中...</p>
	</div>
	<div class="foot">SICD   |   版权所有 @ 2014</div>
	</div>
<script src="<%=request.getContextPath()%>/resources/js/baihe/tongji-1.0.1.min.js" type="text/javascript" charset="utf-8"></script>
<script>
$(function(){
	var questionSpms = ['4.44.160.548.1490', '4.44.161.548.1492', '4.44.162.548.1494', '4.44.163.548.1496', '4.44.164.548.1498', '4.44.165.548.1500', '4.44.166.548.1502', '4.44.167.548.1504', '4.44.168.548.1506', '4.44.169.548.1508', '4.44.170.548.1510', '4.44.171.548.1512', '4.44.172.548.1514'];
	
	amove(0, '');
	$('.btL1, .btR1').click(function(){
	    var id = parseInt($(this).attr('href').substring(1));
	    var checkedValue = $(this).hasClass('btL1') ? 'A' : 'B';  

	    return amove(id, checkedValue);
	});
	$('.prev-q').click(function(){
		var id = parseInt($(this).attr('href').substring(1)) - 1; 

		if(id > 0){
			amove(id - 1, '');
		}
		return  false;
	});

	function amove(id, checkedValue){
		$('.contAl').hide();
		if('' != checkedValue)
			  $('input[name="questions['+ id +']"][value='+ checkedValue +']').attr("checked", true); 
		if(id == 13){
			$('.contAl').hide();
			$('#loading').show();
		    $('#twelve_form').submit();
		}
	    
		if(id < 13){
			$('#question_' + (id + 1)).show();
	    }
	    
	    baihe.bhtongji.tongji({'event': '3','spm': questionSpms[id]});
	    
	    return false;
	}
	
	baihe.bhtongji.tongji({'event': '3','spm': '4.44.160.262.1489'});
});
</script>

</body></html>