<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>  
	<head>  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>优选</title>
		
		<jsp:include page="/WEB-INF/jsps/common/link.jsp"></jsp:include>
		
	</head>
	<body class="afternoon">
		
		<!-- 左侧导航栏 -->
		<jsp:include page="/WEB-INF/jsps/common/left_nav.jsp"></jsp:include>
		
		<div id="content">
			<div class="nav wTop">
				<ul>
					<li><a href="/FriendsPlatform/user/recommend" class="active">今日优选</a></li>
					<li><a href="/index/enjoy">我喜欢的</a></li>
					<li id="n_doubleenjoy"><a href="/index/double">双向倾心</a></li>
					<li id="n_like"><a href="/index/enjoyme">喜欢我的</a></li>
				</ul>

				<div class="feedBack"><a href="javascript:;" data-action="/index/feedback">意见反馈</a></div>
			</div>	   
			<div class="promptTxt02"><em></em>
				亲，没事多登录，才能把你推荐给更多的人哦！
			</div>
			<div id="rightMenu">
				<div id="rightMenuCont" style="display: none;">
					<div>
						<img src="/FriendsPlatform${recommend.headImg }">
					</div>
					<dl>
						<dt>
							<strong>${recommend.realName }</strong><br>
							年龄：${recommend.age }<br>
							身高：${recommend.height }<br>
							学历：${recommend.education }<br>
							月收入：${recommend.salary }<br>
							居住地：${recommend.liveCity }<br>
							<div class="clear"></div>
							<a href="/FriendsPlatform/user/profile/${recommend.id }">查看详细资料</a>
						</dt>
						<dd>推荐时间：<br>${time }</dd>
					</dl>
				</div>
				<div class="online_icon">
					<a title="TA的资料" id="floatShow" style="display:block;" href="javascript:void(0);">TA<br>的<br>资<br>料<i></i></a>
					<a title="TA的资料" id="floatHide" style="display:none;" href="javascript:void(0);">TA<br>的<br>资<br>料<i></i></a>
				</div>
			</div>
			<div class="photo" id="picDiv" data-id="${recommend.id }">
				<div></div>
				<img src="/FriendsPlatform${recommend.headImg }" style=" width:400px; height:400px; display: inline; margin-top: 0px; margin-left: 0px;">
				<a href="javascript:;" title="有感觉" data-type="like" class="icon01">有感觉</a>
				<a href="javascript:;" title="考虑一下" data-type="think" class="icon02">考虑一下</a>
				<a href="javascript:;" title="不喜欢" data-type="unlike" class="icon03">不喜欢</a>
			</div>
			<div class="photo" id="lastPicDiv" style="display:none">
				<img src="/FriendsPlatform/resources/img/static/pic_06.jpg">
				<div id="showtime" class="time">00:00:00</div>
			</div>
			<div class="photoList">
				<a href="javascript:;" class="next">向前</a>
				<a href="javascript:;" class="prev">向后</a>
				<ul>
					<li><a href="javascript:;"><img src="/FriendsPlatform${recommend.headImg }"><span class="black" data-userid="${info.id }" style="opacity: 0;"></span></a></li>
					
					<c:forEach items="${infoList }" var="info">
						<li><a href="javascript:;"><img src="/FriendsPlatform${info.headImg }"><span class="black" data-userid="${info.id }"></span></a></li>
					</c:forEach>
					
					<c:forEach var="i" begin="1" end="${count }">
						<li><a href="javascript:;"><img src="/FriendsPlatform/resources/img/static/pic_01.jpg"><span class="red"></span></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/jsps/common/script.jsp"></jsp:include>
		
	</body>  
</html>