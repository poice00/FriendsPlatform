<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/FriendsPlatform/resources/css/lovecss.css" rel="stylesheet"
	type="text/css" />
<link href="/FriendsPlatform/resources/css/public.css" rel="stylesheet"
	type="text/css">
<link href="/FriendsPlatform/resources/css/douban.css" rel="stylesheet"
	type="text/css">
<link href="/FriendsPlatform/resources/css/all.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" language="javascript"
	src="/FriendsPlatform/resources/js/jquery.min.js"></script>

<title>话题</title>
</head>
<body class="night">
	<div class="" style="display: none; position: absolute;">
		<div class="aui_main" style="width: auto; height: auto;">
			<div class="aui_content" style="padding: 20px 25px;"></div>
		</div>
	</div>
	<div id="leftNav">
		<div class="cont">
			<a class="mobileD" href="/download/client"><div>
					<em>图标</em>
				</div>客户端下载</a> <a href="/download/"><h1 class="icon">百合相亲</h1></a>
			<div class="portrait" id="1portrait"
				onmouseover="leftNavLayer('portrait',1);"
				onmouseout="leftNavLayer('portrait',2);">
				<a href="javascript:;">
					<!-- 用户头像 -->
					<img class="mainPhoto"
					src="http://xqphoto.ibaihe.com/photo/0000/663/140/142345_144498191942_87_87.jpg">
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
					<li><a class="menu01" href="/index?spm=1.9.1.62.76"><i></i><span>优选</span></a></li>

					<li id="n_msg"><a class="menu02" href="/msg/im"><i></i><span>消息</span></a></li>

					<li><a class="active05" href="/FriendsPlatform/topic/list/1"><i></i><span>话题</span></a></li>

					<li><a class="menu08" href="/assistant"><i></i><span>云红娘</span></a></li>
				</ul>
			</div>
			<div class="menuNav" id="3menu" onmouseover="leftNavLayer('menu',3);"
				onmouseout="leftNavLayer('menu',4);">
				<div class="icon">网站菜单</div>
				<div id="4menu" class="menuCont" style="display: none;">
					<ul>
						<li class="red">客服电话<br>400-152-0555 按 5
						</li>
						<li><a href="/menu/about">关于我们</a></li>
						<li><a href="/menu/security">安全交友</a></li>
						<li><a href="/menu/privacy">隐私保护</a></li>
						<li><a href="/menu/service">服务条款</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="content">
		<div class="cloud"
			style="background: #fff; opacity: 0.8; padding: 0 50px;">
			<div id="group-topics" class="mod">

				<div id="group-new-topic-bar">
					<div class="bns">
						<a href="/register?reason=join-group"
							class="j a_show_login bn-post1"> <span><i>+</i>发言</span>
						</a>
					</div>

					<div class="topic-tab" style="font-size: 26px;">
						<a href="http://www.douban.com/group/kaopulove/#topics" class="on">最靠谱的恋爱小组</a>
					</div>
				</div>

				<div class="">

					<table class="olt">
						<tbody>
							<tr class="th">
								<td>话题</td>
								<td>作者</td>
								<td align="right">最后回应</td>
							</tr>
							<!--- douban ad begin -->
							<tr id="dale_group_special2"></tr>
							<script>
								window.DoubanAdLoaders = {};
								window.DoubanAdLoaders['dale_group_special2'] = function(
										query, parameters) {
									var q = query || {}, unit = query.unit
											|| '', slot = document
											.getElementById(unit), param = parameters
											|| {}, content = param.html || '';

									if (content === '') {
										return;
									}
									cont = JSON.parse(content);
									if (cont.msg) {
										return;
									}
									var tpl = [
											'<td class="title" colspan="2">',
											'<img src="'+cont.pic+'" style="position: relative; top: 2px; margin-right: 6px;"/>',
											'<span class="pl">[推荐]</span>',
											'<a href="'+cont.redirect_link+'" title="'+cont.title+'" class="" target="_blank">'
													+ cont.title + '</a>',
											'<img src="'+cont.count_link+'" border="0" width="0" height="0" style="position:absolute;">',
											'</td>', '<td></td>', '<td></td>', ]
											.join('\n');
									slot.className = '';
									slot.innerHTML = tpl;
								};
							</script>
							<!--- douban ad end -->

							<c:forEach items="${topicList.entityList }" var="topic">
								<tr class="">
									<td class="title"><span class="pl"></span>&nbsp; <a
										href="/FriendsPlatform/topic/detail/${topic.id}/1">${topic.title}</a>
									</td>
									<td nowrap="nowrap">${topic.author}</td>
									<td nowrap="nowrap" class="time">${topic.time}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

					<div class="paginator">
						<c:choose>
							<c:when test="${topicList.currentPage eq 1 }">
								<span class="prev"> &lt;前页 </span>
							</c:when>
							<c:otherwise>
								<span class="prev">
									<link rel="prev"
										href="/FriendsPlatform/topic/list/${topicList.currentPage-1}">
									<a
									href="/FriendsPlatform/topic/list/${topicList.currentPage-1}">&lt;前页</a>
								</span>
							</c:otherwise>
						</c:choose>

						<c:forEach items="${topicList.pagination }" var="p">
							<c:choose>
								<c:when test="${topicList.currentPage==p }">
									<span class="thispage" data-total-page="6">${p }</span>
								</c:when>
								<c:otherwise>
									<a href="/FriendsPlatform/topic/list/${p}">${p }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>




						<c:choose>
							<c:when test="${topicList.currentPage==topicList.pageCount }">
								<span class="next">后页&gt;</span>
							</c:when>
							<c:otherwise>
								<span class="next">
									<link rel="next"
										href="/FriendsPlatform/topic/list/${topicList.currentPage+1}">
									<a
									href="/FriendsPlatform/topic/list/${topicList.currentPage+1}">后页&gt;</a>
								</span>
							</c:otherwise>
						</c:choose>

					</div>

				</div>

				<!-- <div class="group-topics-more">
					&gt; <a
						href="http://www.douban.com/group/kaopulove/discussion?start=50">更多小组讨论</a>
				</div> -->
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="/FriendsPlatform/resources/js/effect.js"></script>
	<script type="text/javascript"
		src="/FriendsPlatform/resources/js/jquery.artDialog.js"></script>
	<script type="text/javascript">
		(function() {
			$(document).on('click', function(event) {
				var target = event.target, _target = $(target), action = '';

				action = _target.data("action");
				if (action) {
					$.get(action, function(r) {
						art.dialog({
							content : r
						});
					});
				}
			});
		})(jQuery);
	</script>
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F887bcaca634b40591b6e9953c168af21' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script
		src=" http://hm.baidu.com/h.js?887bcaca634b40591b6e9953c168af21"
		type="text/javascript"></script>


</body>
</html>