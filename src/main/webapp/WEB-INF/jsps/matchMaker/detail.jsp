<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0063)http://vip.baihe.com/matchmaker/content/matchmaker_content.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>百合网</title>
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/public.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/formStyle.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/vip.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.SuperSlide2.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.select-1.3.6.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.blockUI-2.42.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/district.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/city.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/pop_text.js"></script>
<!--[if IE]><script type="text/javascript" src="http://static4.baihe.com/3rd-lib/expressions.js"></script><![endif]-->
<!--[if IE 6]>
<script src="http://static4.baihe.com/3rd-lib/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('#vipHead .logo,#vipFoot .foot ul li a,#vipFoot .foot .logo,.matchMakerInfo .right dt .bottom img,.matchMakerInfo .left dt a,.matchMakerInfo .left dt a img,.successCase dl dt a');
    </script>
<![endif]-->
<script type="text/javascript">
	window.onload = function(){
		$("a[index='40']").addClass("selected");
		$("a[index='0']").each(function(){
    		if($(this).text()=="1950"){
				$(this).removeClass("selected");
			}
  		});
		$("span").each(function(){
    		if($(this).text()=="1950"){
				$(this).text("1990");
			}
  		});
		$("#district").attr("data-val","861108");
		$("#district").val("中国-北京市-海淀区");
	} 
	function validate1(){
		if($("#message").val().length<=0||"给红娘留言..."==($("#message").val())){
			alert("请填写留言");
			return false;
		}
		if($("#username1").val().length<=0){
			alert("请填写您的称呼");
			return false;
		}
		var telphoto=$("input[name='contact']").val();
		if(!(/^0?1[3|4|5|8][0-9]\d{8}$/).test(telphoto)){
			alert("请正确填写的电话");
			return false;
		}
		return true;
	}
    
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}
function Csex(m){
	$("#gender").val(m);
	$("#gender_"+((m+1)%2)).addClass("check");
	$("#gender_"+m).removeClass("check");
}
function count1(){
	var s=$("#message").val().length;
	if(s>300){
		$("#message").val($("#message").val().substring(0,300));
		$("#span1").text(300);
		$("#span2").text(0);
		alert("您输入内容过长");
	}else{
		$("#span1").text(s);
		$("#span2").text(300-s)
	}
}
</script>
</head>

<body>
<div id="vipHead">	
<div class="head">		
	<a href="http://www.baihe.com/" class="logo">百合网</a>      
		<div class="nav">
			<a href="http://vip.baihe.com/index.html">首 页</a>
			    		<a href="http://vip.baihe.com/member/index.html">会员推荐</a>
			    		    		<a href="http://vip.baihe.com/story/index.html">成功故事</a> 
			    		    		   		<a href="http://vip.baihe.com/matchmaker/index.html" class="active">百合红娘</a>
			    		    		   		    		<a href="http://vip.baihe.com/activities/index.html">交友活动</a>
			    		    		   		    		    		<a href="http://vip.baihe.com/shop/index.html">实体店</a>
   				</div>	</div></div>
<div class="content">
	<div class="matchMakerInfo fixfloat">
	
			<dl class="left">
				<dt>
					<img src="<%=request.getContextPath()%>${detail.img }" width="465" height="364" alt=""/>
						</dt>
				<dd>
					<h4>${detail.name }<span>${detail.job }</span></h4>
					<p><span>从业时间：</span>${detail.workTime }</p>
					<p><span>咨询热线：</span>${detail.phone }</p>
					<p><span>服务时间：</span>${detail.serviceTime }</p>
				</dd>
			</dl>
			<dl class="right">
				<dt class="fixfloat">
					<div class="top"></div>
					<div class="text">${detail.intro }</div>
					<div class="bottom"><img src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/bg_mm_03.png"></div>
				</dt>
				<dd>
					<div class="experience">
						<h5>心得资历</h5>
						<p>${detail.experience }</p>
					</div>
					<div class="motto">
						<h5>座右铭</h5>
						<p>${detail.motto }</p>
					</div>
				</dd>
			</dl>
  </div>	
	<div class="successCase">
		<h3 class="bt">成功案例</h3>
		<dl class="fixfloat">
			<dt><img src="<%=request.getContextPath()%>${cs.pic}" width="517" height="425">
			<a href="#">${cs.tip}</a></dt>
			<dd>
				<div class="info fixfloat">
					<img src="<%=request.getContextPath()%>${cs.femalePic}" width="670" height="445">
					<h5>${cs.femaleName}<span>${cs.femaleAge}</span></h5>
					<p><span>${cs.femaleYear}</span>年<span>${cs.femaleMonth}</span>月成为百合网VIP会员</p>
			  </div>
				<div class="info fixfloat">
					<img src="<%=request.getContextPath()%>${cs.malePic}" width="960" height="1280">
					<h5>${cs.maleName}<span>${cs.maleAge}</span></h5>
					<p><span>${cs.maleYear}</span>年<span>${cs.maleMonth}</span>月成为百合网VIP会员</p>
			  </div>
				<div class="word">
					<h5>红娘寄语 :</h5>
					<p>${cs.matchMaketips}</p>
				</div>
			</dd>
		</dl>
	</div>	
</div>
<div id="vipFoot">	<div class="foot">		
<a href="http://www.baihe.com/" class="logo">百合网</a>		
<div class="footText">			
<p>北京总部：北京市朝阳区阜通东大街1号院望京SOHO 5号楼（塔3）A(B)座6层</p>			
<p>幸福专线：400-1520-555 ( 8：00 至 20：00 ) </p>			<p class="textSmall">版权所有©2005-2014 百合网　京ICP证041124号　京公网安备110105000655号</p>		</div>		<ul>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=568&amp;listid=28#">关于百合</a></li>			<li><a href="http://media.baihe.com/html/index.html">媒体关注</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=571&amp;listid=28#">联系我们</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=572&amp;listid=28#">诚聘人才</a></li>		</ul>	</div></div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/scroll.js"></script>


</body></html>