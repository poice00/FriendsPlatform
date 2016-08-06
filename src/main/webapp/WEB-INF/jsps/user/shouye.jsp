<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<title>西安高校非诚勿扰</title>
<link rel="stylesheet" href="/FriendsPlatform/resources/baihe_files/jquery.Jcrop.css">
<link href="/FriendsPlatform/resources/baihe_files/lovecss.css" rel="stylesheet" type="text/css">
<link href="/FriendsPlatform/resources/baihe_files/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="/FriendsPlatform/resources/baihe_files/jquery.min.js"></script>

<script type="text/javascript">
function educationClose() {
	window.location.href="/FriendsPlatform/user/shouye";
}
function blewdazhuan() {
	$("#blewdazhuan").addClass("active");
	$("#dazhuan").removeClass("active");
	$("#benke").removeClass("active");
	$("#suoshi").removeClass("active");
	$("#boshi").removeClass("active");
}
function dazhuan() {
	$("#blewdazhuan").removeClass("active");
	$("#dazhuan").addClass("active");
	$("#benke").removeClass("active");
	$("#suoshi").removeClass("active");
	$("#boshi").removeClass("active");
}
function benke() {
	$("#blewdazhuan").removeClass("active");
	$("#dazhuan").removeClass("active");
	$("#benke").addClass("active");
	$("#suoshi").removeClass("active");
	$("#boshi").removeClass("active");
}
function suoshi() {
	$("#blewdazhuan").removeClass("active");
	$("#dazhuan").removeClass("active");
	$("#benke").removeClass("active");
	$("#suoshi").addClass("active");
	$("#boshi").removeClass("active");
}
function boshi() {
	$("#blewdazhuan").removeClass("active");
	$("#dazhuan").removeClass("active");
	$("#benke").removeClass("active");
	$("#suoshi").removeClass("active");
	$("#boshi").addClass("active");
}
function save() {
	var education;
	var blewdazhuan = document.getElementById("blewdazhuan").className;
	var dazhuan = document.getElementById("dazhuan").className;
	var benke = document.getElementById("benke").className;
	var suoshi = document.getElementById("suoshi").className;
	var boshi = document.getElementById("boshi").className;

	if (blewdazhuan=="active") {
		alert(education);
	}
	if (dazhuan=="active") {
		education = "大专";
	}
	if (benke=="active") {
		education = "本科";
	}
	if (suoshi=="active") {
		education = "硕士";
	}
	if (boshi=="active") {
		education = "博士";
	}

	 $.ajax({
		url : "/FriendsPlatform/user/educationModify",
		type : "post",
		data : {
			education:education
 		},
		success : function(response) {
		window.location.href="/FriendsPlatform/user/shouye";
		},
	});  
}
</script>
</head>

<body class="noon" style="background:url(<%=request.getContextPath()%>/resources/baihe_files/bj_body.jpg);">
	<!-- 左侧导航栏 -->
		<jsp:include page="/WEB-INF/jsps/common/left_nav.jsp"></jsp:include>
	<div id="content">

		<div class="personal">
			<dl>
				<dt>
					<a href="http://xq.ibaihe.com/index/index" class="Return">〈 返回</a><strong>我的资料</strong>
				</dt>
				<dd>
					<a href="http://xq.ibaihe.com/index/preview" class="alter"><i></i>查看封面照</a>
					<a href="javascript:;" data-action="lovestatus" class="alter"><i></i>修改恋爱状态</a>
					<a href="<%=request.getContextPath() %>/resources/baihe_files/百合相亲.htm" style="background: #BC363F;"
						class="alter"><i></i>修改我的资料</a> <a
						href="http://xq.ibaihe.com/index/match" class="alter"><i></i>修改择偶条件</a>
					<a href="javascript:;" data-action="password" class="alter"><i></i>修改帐号密码</a>
					<a href="javascript:;" data-action="creditphone" class="alter"><i></i>修改认证手机</a>
				</dd>
			</dl>
			<dl class="h01">
				<dt style="width: 380px;">
					<img class="mainPhoto"
						src="<%=request.getContextPath() %>/resources/baihe_files/142345_144498191942_87_87.jpg">
					<p>ID：663140</p>
					<div class="txt">
						<span></span><span>27岁</span><span>180cm</span><span
							id="education">${info.education }</span>
						<div class="txtIcon">
							<a href="http://xq.ibaihe.com/index/credit" title="点击完成实名认证"><em
								class="v01">实名认证</em></a><em class="m" title="已完成手机认证">手机认证</em>
						</div>
					</div>
				</dt>
				<dd></dd>
			</dl>
			<div class="clear"></div>
			<div class="list active">
				<ul>
					<li id="photo-142345"><img class="showphoto" data-type="200"
						src="<%=request.getContextPath() %>/resources/baihe_files/142345_144498191942_200_200.jpg">
						<div class="setUp" style="display: none;"></div></li>
					<li class="bj01">
						<div>
							<strong>基本信息</strong>
							<p>年龄：27岁</p>
							<a data-action="education">学历：${info.education }</a>
							<p>身高：180cm</p>
						</div>
					</li>
					<li class="bj03">
						<div>
							<strong>我的位置</strong> <a data-action="city">居住地：陕西省西安市</a> <a
								data-action="homeTown">家乡：澳门特别行政区</a> <a
								data-action="registered">户口：天津市河东区</a>
						</div>
					</li>
					<li class="bj04">
						<div>
							<strong>我的工作</strong> <a data-action="newnature">公司性质：事业单位</a> <a
								data-action="industry">公司行业：计算机/网络</a> <a
								data-action="newWorkStatus">工作时间：常有应酬</a>
						</div>
					</li>
					<li class="bj10">
						<div>
							<strong>经济现状</strong> <a data-action="income">月收入：20000-25000</a>
							<a data-action="housing">购房情况：租房</a> <a data-action="newcar">购车情况：已购车</a>
						</div>
					</li>
					<li id="photo-142698"><img class="showphoto"
						src="<%=request.getContextPath() %>/resources/baihe_files/saved_resource"> <a href="javascript:;"
						style="display: none;" data-id="142698" class="close"
						data-action="delphoto?photoId=142698">删除</a> <em>因不合要求被驳回</em></li>
					<li class="bj05">
						<div>
							<div class="pic">
								<p class="cons12">双鱼座</p>
								<p class="Zodiac05">属龙</p>
								<p class="type">AB型血</p>
							</div>
						</div>
					</li>
					<li><a href="javascript:;" class="addPic" data-ap="1"
						data-action="/basic/photo?AP=1"><i
							data-action="/basic/photo?AP=1">点击添加照片</i></a></li>
					<li class="bj06">
						<div>
							<strong>个人状况</strong> <a data-action="marriage">婚姻状况：未婚</a> <a
								data-action="havechildren">有无子女：有，和我住一起</a>
						</div>
					</li>
					<li class="bj07">
						<div>
							<strong>生活习惯</strong> <a data-action="housework">家务：希望对方承担家务</a>
							<a data-action="cuisine" title="能做几样可口的小菜">厨艺：能做几样可口的小菜</a>
							<div class="pic">
								<a data-action="newsmoking" class="Smoke" title="不吸烟，但不反感">不吸烟...</a>
								<a data-action="newdrinking" class="Wine" title="社交需要喝">社交需...</a>
							</div>
						</div>
					</li>
					<li class="bj08">
						<div>
							<strong>民族信仰</strong> <a data-action="religion">宗教信仰：基督教</a>
							<p>民族：汉族</p>
						</div>
					</li>
					<li class="bj09">
						<div>
							<strong>家庭背景</strong> <a data-action="parentssituationu">父母情况：父母健在</a>
							<p>家中排行：老大</p>
						</div>
					</li>
					<li class="bj02">
						<div>
							<strong>内心独白</strong> <a style="display: block"
								data-action="familydescr"
								title="大家好，我来自陕西省西安市，在百合网真心寻找一个靠谱的她，希望在以后的生活中相互扶持，过上幸福安逸的美好生活，我的愿望并不大，只希望有你相伴。">大家好，我来自陕西省西安市，在百合网真心寻找一个靠谱的她，希望在以后的生活中相互扶持，过上幸福安逸的美好生活，我的愿望并不大，只希望有你相伴...</a>
						</div>
					</li>
					<li class="bj11">
						<div>
							<strong>相亲问答</strong> <a>已完成问答：15 题</a> <a>未完成问答：1226 题</a> <a
								href="javascript:showQueDiv();" class="link">继续答题</a>
						</div>
					</li>
					<li><a href="javascript:;" class="addPic" data-ap="2"
						data-action="/basic/photo?AP=2"><i
							data-action="/basic/photo?AP=2">点击添加照片</i></a></li>


					<li><a href="javascript:;" class="addPic" data-ap="3"
						data-action="/basic/photo?AP=3"><i
							data-action="/basic/photo?AP=3">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="4"
						data-action="/basic/photo?AP=4"><i
							data-action="/basic/photo?AP=4">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="5"
						data-action="/basic/photo?AP=5"><i
							data-action="/basic/photo?AP=5">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="6"
						data-action="/basic/photo?AP=6"><i
							data-action="/basic/photo?AP=6">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="7"
						data-action="/basic/photo?AP=7"><i
							data-action="/basic/photo?AP=7">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="8"
						data-action="/basic/photo?AP=8"><i
							data-action="/basic/photo?AP=8">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="9"
						data-action="/basic/photo?AP=9"><i
							data-action="/basic/photo?AP=9">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="10"
						data-action="/basic/photo?AP=10"><i
							data-action="/basic/photo?AP=10">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="11"
						data-action="/basic/photo?AP=11"><i
							data-action="/basic/photo?AP=11">点击添加照片</i></a></li>
					<li><a href="javascript:;" class="addPic" data-ap="12"
						data-action="/basic/photo?AP=12"><i
							data-action="/basic/photo?AP=12">点击添加照片</i></a></li>

				</ul>

			</div>
		</div>
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