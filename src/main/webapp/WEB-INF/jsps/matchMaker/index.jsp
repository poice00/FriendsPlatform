<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://vip.baihe.com/matchmaker/index.html# -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的红娘</title>
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/public.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/formStyle.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/vip.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/dialog/showMessage.js"></script>
<!-- <script type="text/javascript">
	
	

function openwindow(id)
{
var url='chat/'+id; //转向网页的地址;
var name; //网页名称，可为空;
var iWidth='806px'; //弹出窗口的宽度;
var iHeight='641px'; //弹出窗口的高度;
//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
window.open(url,"_blank",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

</script> -->
<style type="text/css">
.check {
	background-color: #2694E8;
}
</style>
<script type="text/javascript">	
function mydetail(){
	$("#case").removeClass("active");
	$("#detail").addClass("active");	
	

	$("#sc").css("display","none")  
	$("#mi").css("display","block") 
}
function myCase(){
	$("#detail").removeClass("active");
	$("#case").addClass("active");
	
	
	$("#mi").css("display","none")  
	$("#sc").css("display","block")  
	
}

</script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/floatButton.js"></script><script language="javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/floatButtonStatic.js"></script><script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/component-v5.js" id="lim:component"></script><link id="invite_style" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/invite.css"><link id="mini_chat_style" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/mini.css"></head><body class="matchmaker" lim:visitorcapacity="1"><div style="display:none;"><a href="http://www.live800.com/">在线客服系统</a></div><script language="javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/monitor.js"></script></script><iframe id="lim:share" name="lim:share" height="0" width="0" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/shared.html"></iframe></div></div>

<body>

	<div id="vipHead">	
		<div class="head">		
		     <div class="nav">
		     			<a id="detail" href="#" onclick="mydetail()" class="active">我的资料</a>    		
		     			<!-- <a href="#">用户信息</a>    	 -->	
		     			<a id="case" href="#" onclick="myCase()" >我的成功故事</a>    		
				</div>	
     			<!--  <a style="color: white;" href="javascript:history.go(0)">刷新</a>-->
				<span style="color: white;font: bold 26px/55px 'Microsoft Yahei'">登陆用户：${matchmaker.name }</span>
				<a style="color: white;" href="/FriendsPlatform/maker/logout">注销</a>	
		</div>
	</div>
	
	<div class="content">
	<div id="mi" class="matchMakerInfo fixfloat">
			<dl class="left">
				<dt>
					<img src="<%=request.getContextPath()%>${matchmaker.img }" width="465" height="364" alt=""/>
						</dt>
				<dd>
					<h4>${matchmaker.name }<span>${matchmaker.job }</span></h4>
					<p><span>从业时间：</span>${matchmaker.workTime }</p>
					<p><span>咨询热线：</span>${matchmaker.phone }</p>
					<p><span>服务时间：</span>${matchmaker.serviceTime }</p>
				</dd>
			</dl>
			<dl class="right">
				<dt class="fixfloat">
					<div class="top"></div>
					<div class="text">${matchmaker.intro }</div>
					<div class="bottom"><img src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/bg_mm_03.png"></div>
				</dt>
				<dd>
					<div class="experience">
						<h5>心得资历</h5>
						<p>${matchmaker.experience }</p>
					</div>
					<div class="motto">
						<h5>座右铭</h5>
						<p>${matchmaker.motto }</p>
					</div>
				</dd>
			</dl>
  </div>	
	<div id="sc" class="successCase"  style="display: none">
		<h3 class="bt">成功案例</h3>
		<c:forEach items="${matchmaker.cases}" var="item">
			<dl class="fixfloat">
				<dt><img src="<%=request.getContextPath()%>${item.pic}" width="517" height="425">
				<a href="#">${cs.tip}</a></dt>
				<dd>
					<div class="info fixfloat">
						<img src="<%=request.getContextPath()%>${item.femalePic}" width="670" height="445">
						<h5>${item.femaleName}<span>${item.femaleAge}</span></h5>
						<p><span>${item.femaleYear}</span>年<span>${item.femaleMonth}</span>月成为百合网VIP会员</p>
				  </div>
					<div class="info fixfloat">
						<img src="<%=request.getContextPath()%>${item.malePic}" width="960" height="1280">
						<h5>${item.maleName}<span>${item.maleAge}</span></h5>
						<p><span>${item.maleYear}</span>年<span>${item.maleMonth}</span>月成为百合网VIP会员</p>
				  </div>
					<div class="word">
						<h5>红娘寄语 :</h5>
						<p>${item.matchMaketips}</p>
					</div>
				</dd>
			</dl>
			<hr />
		</c:forEach>
	</div>	
</div>
<div id="vipFoot">	<div class="foot">		
<a href="http://www.baihe.com/" class="logo">百合网</a>		
<div class="footText">			
<p>北京总部：北京市朝阳区阜通东大街1号院望京SOHO 5号楼（塔3）A(B)座6层</p>			
<p>幸福专线：400-1520-555 ( 8：00 至 20：00 ) </p>			<p class="textSmall">版权所有©2005-2014 百合网　京ICP证041124号　京公网安备110105000655号</p>		</div>		<ul>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=568&amp;listid=28#">关于百合</a></li>			<li><a href="http://media.baihe.com/html/index.html">媒体关注</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=571&amp;listid=28#">联系我们</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=572&amp;listid=28#">诚聘人才</a></li>		</ul>	</div></div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/scroll.js"></script>


</body></html>