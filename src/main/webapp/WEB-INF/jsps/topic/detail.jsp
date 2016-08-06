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
	src="/FriendsPlatform/resources/js/lovejs/jquery.min.js"></script>
<!-- <script type="text/javascript"
	src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script> -->
<!-- <style type="text/css">
${
demo
.css
}
</style> -->
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '${topic.title}'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: "Brands",
            colorByPoint: true,
            data: [{
                name: "Positive",
                y: ${topic.positive}
            }, {
                name: "Negative",
                y: ${topic.negative}
                /* sliced: true,
                selected: true */
            }]
        }]
    });
});
		</script>
<title>话题详情</title>
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
				<a href="javascript:;"> <!-- 用户头像 --> <img class="mainPhoto"
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
					<li><a class="menu01" href="/FriendsPlatform/user/recommend"><i></i><span>优选</span></a></li>

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

			<h1>${topic.title }</h1>

			<div class="grid-16-8 clearfix">

				<div class="article">
					<input type="hidden" id="start" value="0">
					<div class="topic-content clearfix">
						<div class="user-face"></div>
						<div class="topic-doc">
							<span class="from">来自:${topic.author }</span> <span
								class="color-green">${topic.time }</span>

							<div id="link-report" class="">
								<div class="topic-content">${topic.content }</div>
							</div>
						</div>

						<div class="sns-bar" id="sep">
							<div class="sns-bar-rec"></div>
							<div class="sns-bar-fav"></div>

							<c:forEach items="${replyList.entityList }" var="reply">
								<ul class="topic-reply" id="comments">
									<li class="clearfix comment-item" id="1009734177"
										data-cid="1009734177">
										<div class="reply-doc content" style="padding-left: 0px;">
											<div class="bg-img-green">
												<h4>${reply.replyer }
													<span class="pubtime">${reply.time }</span>
												</h4>
											</div>
											<p class="">${reply.content }</p>
										</div>
									</li>
								</ul>
							</c:forEach>

						</div>


						<div class="paginator" style="margin-top:-20px;">
							<c:choose>
								<c:when test="${replyList.currentPage eq 1 }">
									<span class="prev"> &lt;前页 </span>
								</c:when>
								<c:otherwise>
									<span class="prev">
										<link rel="prev"
											href="/FriendsPlatform/topic/detail/${topic.id}/${replyList.currentPage-1}">
										<a
										href="/FriendsPlatform/topic/detail/${topic.id}/${replyList.currentPage-1}">&lt;前页</a>
									</span>
								</c:otherwise>
							</c:choose>

							<c:forEach items="${replyList.pagination }" var="p">
								<c:choose>
									<c:when test="${replyList.currentPage==p }">
										<span class="thispage" data-total-page="6">${p }</span>
									</c:when>
									<c:otherwise>
										<a href="/FriendsPlatform/topic/detail/${topic.id}/${p}">${p }</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>




							<c:choose>
								<c:when test="${replyList.currentPage==replyList.pageCount }">
									<span class="next">后页&gt;</span>
								</c:when>
								<c:otherwise>
									<span class="next">
										<link rel="next"
											href="/FriendsPlatform/topic/detail/${topic.id}/${replyList.currentPage+1}">
										<a
										href="/FriendsPlatform/topic/detail/${topic.id}/${replyList.currentPage+1}">后页&gt;</a>
									</span>
								</c:otherwise>
							</c:choose>

						</div>



					</div>
				</div>

				<div style="position: absolute; left: 800px; width: 600px;">
					<c:choose>
						<c:when test="${!empty topic.positive}">
							<div id="container"
								style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
						</c:when>
						<c:otherwise>
							<h1>No sentiment analysis</h1>
						</c:otherwise>
					</c:choose>
				</div>


				<!-- <script>var POPUP_REG = true;</script>

				<script>
					Do(function() {
						var DOULIST_ADDITEM = "/j/doulist/{doulist_id}/additem";
						var DOULIST_EDITITEM = "/j/doulist/{doulist_id}/edititem";
						var DOULIST_COMMENT = "/j/doulist/{doulist_id}/poke";
						var DOULIST_CREATE = "/j/doulist/add";
						var DOULIST_LIST = "/j/doulist/cat";
						var DOULIST_SEARCH = "/j/doulist/search";
						var DOULIST_SEARCH_SELF = "/j/doulist/search_user_doulists";
						var DOULIST_GET_ITEM_INFO = "/j/doulist/get_item_info";
						function deferred() {
							var a = {
								done : [],
								fail : []
							};
							var b = {
								done : function(c) {
									a.done.push(c);
									return b
								},
								fail : function(c) {
									a.fail.push(c);
									return b
								}
							};
							return {
								resolve : function() {
									var d = 0, c;
									for (; c = a.done[d++];) {
										c.apply(this, arguments)
									}
								},
								reject : function() {
									var d = 0, c;
									for (; c = a.fail[d++];) {
										c.apply(this, arguments)
									}
								},
								promise : b
							}
						}
						function asyncRequest(a, e, f) {
							var d = deferred();
							var c = null;
							var b = (f || "get").toLowerCase();
							c = $.ajax({
								url : a,
								type : b,
								dataType : "json",
								data : (b === "post") ? $.extend(e, {
									ck : get_cookie("ck")
								}) : e,
								error : function(g) {
									d.reject(g)
								},
								success : function(g) {
									d.resolve(g)
								}
							});
							d.promise.abort = function() {
								c && c.abort()
							};
							return d.promise
						}
						var addTooltipToDoulistBtn = function(b) {
							if (!get_cookie("ck")) {
								return
							}
							var d = /^https?:\/\/\w+\.douban\.com\/link2\/\?url=(\S+)$/i;
							var i = function(k) {
								var j = k.match(d);
								return j ? decodeURIComponent(j[1]) : k
							};
							$(document)
									.delegate(
											".url-doulist-add",
											"click",
											function(n) {
												n.preventDefault();
												var j = $(this);
												var l = i(j.data("url"));
												var k;
												var m = dui
														.Dialog(
																{
																	title : "添加到豆列",
																	width : 640,
																	cls : "dialog-doulist dialog-tooltip-loading",
																	content : '<div class="tooltip-text">内容加载中<i class="tooltip-loading-icon"></i></div>'
																}).open();
												m.node
														.find(
																".dui-dialog-close")
														.click(function(o) {
															k && k.abort()
														});
												k = asyncRequest(
														DOULIST_GET_ITEM_INFO,
														{
															url : l,
														}, "post")
														.done(
																function(o) {
																	if (o.r) {
																		m.node
																				.find(
																						".tooltip-text")
																				.text(
																						o.error);
																		return
																	}
																	o.cate = (o.kind || "")
																			+ "";
																	o.picture = typeof o.images === "string" ? o.images
																			: (o.images && o.images[0])
																					|| "";
																	o.id = (o.id || "")
																			+ "";
																	m.close();
																	j
																			.doulistDialog(o)
																})
														.fail(
																function(o) {
																	m.node
																			.find(
																					".tooltip-text")
																			.text(
																					"失败啦！再试一次吧")
																})
											});
							var e = 85;
							var a = false;
							var c = "doulist-tooltip-hide";
							var f = $('<div class="doulist-add-tooltip"><a class="url-doulist-add" ><i></i> 添加到豆列</a><div class="arrow"></div></div>');
							var h = f.find("a");
							f.addClass(c);
							f.appendTo($("body"));
							var g = function(j) {
								a = setTimeout(function() {
									f.addClass(c)
								}, e)
							};
							$(b).mouseenter(function(k) {
								var j = $(this);
								f.css({
									top : j.offset().top - 28,
									left : k.pageX - 42
								});
								h.data("url", j.attr("href"));
								clearTimeout(a);
								f.removeClass(c)
							}).mouseleave(g);
							f.mouseenter(function() {
								clearTimeout(a)
							}).mouseleave(g)
						};
						addTooltipToDoulistBtn('#link-report .topic-content a');

						if (get_cookie('ck')) {
							$
									.each(
											$(".popular-bd .operation-more"),
											function(i, el) {
												if ($(el).find(
														".comment-report").length == 0) {
													$(el)
															.append(
																	'<div class="comment-report"><a rel="nofollow" href="javascript:void(0)">举报</a></div>');
												}
											});
							$(".popular-bd")
									.delegate(
											".comment-item",
											'hover',
											function() {
												$(this).find(".comment-report")
														.css('visibility',
																'visible');
											},
											function() {
												$(this).find(".comment-report")
														.css('visibility',
																'hidden');
											})
									.delegate(
											".comment-report a",
											'click',
											function(e) {
												e.preventDefault();
												var auditUrl = "http://www.douban.com/misc/audit_report?url=", opt = "comment_id";
												var obj = $(e.target).closest(
														'.comment-item');
												var cid = obj.data("cid");
												var url = "http://www.douban.com/group/topic/80730541/?"
														.concat(opt, '=', cid);
												generate_report_dialog({
													report_url : url
												});
											});
						}
					});
				</script>

			</div>
			<div class="extra">




			</div> -->
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
	<!-- <script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F887bcaca634b40591b6e9953c168af21' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script
		src=" http://hm.baidu.com/h.js?887bcaca634b40591b6e9953c168af21"
		type="text/javascript"></script> -->

	<script src="/FriendsPlatform/resources/js/highcharts.js"></script>
	<script src="/FriendsPlatform/resources/js/exporting.js"></script>
</body>
</html>