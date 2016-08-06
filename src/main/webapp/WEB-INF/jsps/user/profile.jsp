<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>详细资料</title>
		
		<jsp:include page="/WEB-INF/jsps/common/link.jsp"></jsp:include>
		
	</head>
	<body class="afternoon">
		
		<!-- 左侧导航栏 -->
		<jsp:include page="/WEB-INF/jsps/common/left_nav.jsp"></jsp:include>
		
		<div id="content">
			<div class="personal">
				<dl>
					<dt><a href="/FriendsPlatform/user/recommend" class="Return">〈 返回</a></dt>
					<dd>&nbsp;</dd>
				</dl>
				<dl class="h01">
					<dt style="width:380px;">
						<img src="/FriendsPlatform${info.headImg }" />
						<div class="txt">
							<span>${info.realName }</span>
							<span>${info.age }</span>
							 <span>ID:${info.id }</span>
							<div class="txtIcon">
								<em class="v" title="已完成实名认证">实名认证</em>
								<em class="m" title="已完成手机认证">手机认证</em>
							</div>
						</div>
						<a data-action="/person/report?userId=669618" href="javascript:;" class="report">举报</a>
					</dt>
					<dd>
						<a href="javascript:;" title="有感觉" class="activeLike"
							data-userid="${info.id }" data-type="like">有感觉</a>
						<a href="javascript:;" title="考虑下" class="activePrem"
							data-userid="${info.id }" data-type="think">考虑下</a>
						<a href="javascript:;" title="不喜欢" class="activeNoLike"
							data-userid="${info.id }" data-type="unlike">不喜欢</a>
					</dd>
				</dl>
				<div class="clear"></div>
				<div class="list">
					<ul>
						<li>
							<img class="showphoto" data-type="200" data-action="/person/photolist?photoId=140655&amp;userId=${info.id }" 
							src="/FriendsPlatform${info.headImg }">
						</li>
						<li class="bj01">
							<div>
								<strong>基本信息</strong>
								<a>年龄：${info.age }</a>
								<a>学历：${info.education }</a>
								<a>身高：${info.height }</a>
							</div>
						</li>
						<li class="bj03">
							<div>
								<strong>她的位置</strong>
								<a>居住地：${info.liveCity }</a>
								<a>家乡：${info.homeCity }</a>
								<a>户口：${info.city }</a>
							</div>
						</li>
						<li class="bj04">
							<div>
								<strong>她的工作</strong>
								<a>公司性质：${info.companyType }</a>
								<a>公司行业：${info.companyIndustry }</a>
								<a>工作时间：${info.jobState }</a>
							</div>
						</li>
						<li class="bj10">
							<div>
								<strong>经济现状</strong>
								<a>月收入：${info.salary }</a>
								<a>购房情况：${info.house }</a>
								<a>购车情况：${info.car }</a>
							</div>
						</li>
						<li>
							<img class="showphoto" data-type="200" data-action="/person/photolist?photoId=140656&amp;userId=${info.id }" 
								src="/FriendsPlatform${info.headImg }">
						</li>
						<li class="bj05">
							<div>
								<br>
								<div class="pic">
									<a class="cons12">双鱼座</a>
									<a class="Zodiac08">属羊</a>
									<a class="type">AB型血</a>
								</div>
							</div>
						</li>
						<li class="bj06">
							<div>
								<strong>个人状况</strong>
								<a>婚姻状况：${info.ismarraied }</a>
								<a>有无子女：${info.children }</a>
							</div>
						</li>
						<li class="bj07">
							<div>
								<strong>生活习惯</strong>
								<a>家务：${info.hourseHold }</a>
								<a title="不太会，但愿为心爱的人学习">厨艺：不太会，但愿为心爱的...</a>
								<div class="pic">
									<a class="noSmoke" title="不吸，很反感">不吸，...</a>
									<a class="noWine" title="不喝酒">不喝酒</a>
								</div>
							</div>
						</li>
						<li class="bj08">
							<div>
								<strong>民族信仰</strong>
								<a>宗教信仰：${info.religion }</a>
								<a>民族：${info.nation }</a>
							</div>
						</li>
						<li class="bj09">
							<div>
								<strong>家庭背景</strong>
								<a>父母情况：${info.parentLive }</a>
								<a>家中排行：${info.familyRank }</a>
							</div>
						</li>
						<li class="bj02">
							<div>
								<strong>内心独白</strong>
								<div class="txt" style="height:132px;">
		                       		<a class="more" href="javascript:;" data-action="/person/familydescr?userId=${info.id }">More&gt;&gt;</a>
									${info.introduction }
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/jsps/common/script.jsp"></jsp:include>
		
	</body>
</html>