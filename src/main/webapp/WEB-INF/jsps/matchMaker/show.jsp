<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://vip.baihe.com/matchmaker/index.html# -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>红娘</title>
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/public.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/formStyle.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/vip.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.blockUI-2.42.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.SuperSlide2.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/district.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/pop_text.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/jquery.select-1.3.6.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/iplookup.php"></script>
<script type="text/javascript">
	
	

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

</script>
<script type="text/javascript">
window.onload = function(){
	 if (!validate2()) {
        $(".btnAsk").attr("lim_skill", "");
        $(".btnAsk").attr("lim_company", "439100");
    }
    else {
        $(".btnAsk").attr("lim_skill", "1204");
        $(".btnAsk").attr("lim_company", "100562");
    }

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
<style type="text/css">
.check {
	background-color: #2694E8;
}
</style>
<script type="text/javascript">	
function validate2(){
	var patrn=/[\u5317\u4eac]/gi;
	if(patrn.exec(remote_ip_info["city"])){
		return true;
	}else{
		return false;
	}
}

</script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/floatButton.js"></script><script language="javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/floatButtonStatic.js"></script><script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/component-v5.js" id="lim:component"></script><link id="invite_style" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/invite.css"><link id="mini_chat_style" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/mini.css"></head>
<body class="matchmaker" lim:visitorcapacity="1">
<div style="display:none;">
</div>

	<div align="center">
		<form action="/FriendsPlatform/maker/login" method="post" >
			用户名:<input type="text" name="username"/>
			密码:<input type="text" name="password" />
			<input type="submit" value="登陆" style="cursor:pointer;"/>
		</form>	
	</div>
	
<div class="focusPic">
	<img src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/focus_bhhn.jpg" alt="">
</div>
<div id="content">
	<div class="matchMakerTeam">
		<h3 class="bt">红娘团队</h3>
		<ul>
			<c:forEach items="${makersList}" var="item">
			<li>
				<a href="<%=request.getContextPath()%>/detail/${item.id }"><img src="<%=request.getContextPath()%>${item.img}" alt=""></a>
				<div class="icon">
					<c:choose>
						<c:when test="${item.state==1 }">
							<a class="btnAsk" onclick="openwindow('${item.id }')" href="javascript:void(0)">在线咨询</a>
						</c:when>
						<c:otherwise>
							<a class="btnAsk">离线</a>
						</c:otherwise>
					</c:choose>
				</div>
				<a href="#" class="textName"><img src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/icon_02.png" class="icon2">${item.name}</a>
			</li>
			</c:forEach>
			
		</ul>
	</div>
</div>
<div class="counselor">
	<div class="cont">
		<div class="counselorBox">
			<div class="tempWrap" style="overflow:hidden; position:relative; width:1000px"><ul style="width: 3000px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: -1000px;"><li style="float: left; width: 712px; background: url(http://pic1.vip.baihe.com/images/bj_bhhn_01.jpg) no-repeat;">
					<dl class="left">
						<dt class="name">周小鹏</dt>
						<dd>百合网资深婚恋咨询师</dd>
						<dd>国家二级心理咨询师</dd>
						<dd>心理学硕士</dd>
						<dd>国家职业婚介师</dd>
						<dd>中国心理卫生协会会员</dd>
						<dt class="skill">擅长方向</dt>
						<dd>婚恋心理咨询</dd>
						<dd>EAP咨询</dd>
					</dl>
					<div class="line"></div>
					<dl class="right">
						<dt class="experience">专业经历</dt>
						<dd>2005年参加北京师范大学辅仁心理中心职业资格培训并获得资格认证证书；<br>
							2007年参加刘穿石教授人格心理学长期培训；<br>
							2008年灾后危机干预培训和电话咨询；<br>
							2008参加黄维仁博士"亲密之旅"培训课程；<br>
							2009年参加短程焦点与实务工作坊培训；<br>
							2009年萨提亚家庭治疗培训；<br>
							2009年蔡仲淮催眠课程；<br>
							2009年曾连续担任中国人民广播电台"都市之声-百年修得同车渡"节目嘉宾；<br>
							2010年5月参加SFBT、EAP和现代心理咨询培训；<br>
							2010年11月参加第四届国际EAP培训；<br>
							2010年以心理咨询师身份接受BTV《超级出租车》、《北京青年》等多档节目的采访；<br>
							2010年以职业心理咨询师身份接受《品位》杂志采访；<br>
							2011年4月参加"2011国际健康风险管理论坛"；<br>
							2011年4月参加由好人生国际健康产业集团主办的EAP咨询实务工作坊。</dd>
					</dl>
				</li>
				</ul></div>
		</div>	
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/baihe/matchMaker/scroll.js"></script>
<div id="vipFoot">	<div class="foot">		
<a href="#" class="logo">百合网</a>		
<div class="footText">			
<p>北京总部：北京市朝阳区阜通东大街1号院望京SOHO 5号楼（塔3）A(B)座6层</p>			
<p>幸福专线：400-1520-555 ( 8：00 至 20：00 ) </p><p class="textSmall">版权所有©2005-2014 百合网　京ICP证041124号　京公网安备110105000655号</p>		</div>		<ul>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=568&amp;listid=28#">关于百合</a></li>			<li><a href="http://media.baihe.com/html/index.html">媒体关注</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=571&amp;listid=28#">联系我们</a></li>			<li><a href="http://help.baihe.com/index.php?action=list&amp;cat=572&amp;listid=28#">诚聘人才</a></li>		</ul>	</div></div>

<script type="text/javascript">
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
</script>


</body></html>
