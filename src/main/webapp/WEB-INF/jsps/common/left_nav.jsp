<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<div id="leftNav">
	<div class="cont">
		<a class="mobileD" href="/download/client">
			<div><em>图标</em></div>
			客户端下载
		</a>
		<a href="/FriendsPlatform/user/shouye"><h1 class="icon">百合相亲</h1></a>
		<div class="portrait" id="1portrait" onmouseover="leftNavLayer('portrait',1);" onmouseout="leftNavLayer('portrait',2);">
			<a href="javascript:;">
				<!-- 用户头像 -->
				<img class="mainPhoto" src="http://xqphoto.ibaihe.com/photo/0000/663/140/142345_144498191942_87_87.jpg">
			</a>
			<div id="2portrait" class="data" style="display: none;">
				<div class="Bj"></div>
				<ul>
					<li class="red">您好！</li>
					<li><a href="/index/basic">完善个人资料</a></li>
					<li><a href="/index/match">修改择偶要求</a></li>
					<li><a href="/index/credit">完成实名认证</a></li>
					<li><a href="/service/pay">订单中心</a></li>
					<li><a href="/register/logout">登出网站</a></li>
				</ul>
			</div>
		</div>
		<div class="menu">
			<ul>
				<li><a class="active01" href="/FriendsPlatform/user/recommend"><i></i><span>优选</span></a></li>
				<li id="n_msg"><a class="menu02" href="/FriendsPlatform/match" target="_blank"><i></i><span>心灵匹配</span></a></li>
				<li><a class="menu05" href="/FriendsPlatform/topic/list/1"><i></i><span>话题</span></a></li>
				<li><a class="menu08" href="/FriendsPlatform/maker" target="_blank"><i></i><span>云红娘</span></a></li>
				<li><a class="menu08" href="/FriendsPlatform/user/wordCloud"><i></i><span>云标签1</span></a></li>
				<li><a class="menu08" href="/FriendsPlatform/user/wordCloud2"><i></i><span>云标签2</span></a></li>
				<li><a class="menu08" href="/FriendsPlatform/user/diantu"><i></i><span>特征抽取</span></a></li>
			</ul>
		</div>
		<div class="menuNav" id="3menu" onmouseover="leftNavLayer('menu',3);" onmouseout="leftNavLayer('menu',4);">
			<div class="icon">网站菜单</div>
			<div id="4menu" class="menuCont" style="display:none;">
				<ul>
					<li class="red">客服电话<br>400-152-0555 按 5</li>
					<li><a href="/menu/about">关于我们</a></li>
					<li><a href="/menu/security">安全交友</a></li>
					<li><a href="/menu/privacy">隐私保护</a></li>
					<li><a href="/menu/service">服务条款</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>