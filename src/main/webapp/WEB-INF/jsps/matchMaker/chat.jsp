<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class=""><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link href="<%=request.getContextPath()%>/resources/css/dialog/dialog.css" rel="stylesheet" type="text/css"/>		
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/dialog/structure.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/baihe/jquery-1.8.3.min.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/css/dialog/mybase.js"></script>	
<title>VIP客户服务中心-对话窗口</title>

<script language="JavaScript"type="text/javascript">

window.onbeforeunload = function ()
{
		return "You will lose any unsaved content";
		window.opener.location.href = window.opener.location.href;
		window.close();
    	
}
</script>
</head>
<body>
		<div class="wrap" id="chatwrap" style="visibility: visible;">
			<div id="header" class="themes" style="">
            </div>
            <div id="body_wrap" class="themeborder hidLeft" style="height: 536px;">
            	<div id="left_banner" class="themeborder"></div>
            	<div id="content_wrap">
          <div id="leftSwitch" class="switchers nobg  right_bk" dirc="l" sta="c" style="display: none;">
            
          </div>
					<div id="rightSwitch" class="switchers right_bk nobg" dirc="r" sta="o">
						
					</div>
					<div id="history" class="themeborder">
						<div class="info" id="headinfo"><span id="headerBox">您正在和客服代表<span style="color: red">${detail.name }</span>对话</span>
							<span id="headerBoxTime"></span>
						</div>
						<div id="padding">
							<div class="lim_systemTip lim_clearfloat ">
								<div class="lim_bubble lim_shadow">
									<span class="lim_infotip">您好!  欢迎光临 ，请稍候......</span>
								</div>
								<div class="lim_tale"><div id="radiusborder"></div>
							</div>
						</div>
						<div class="lim_systemTip lim_clearfloat ">
							<div class="lim_bubble lim_shadow">
								<span class="lim_infotip">客服代表为您服务，请问有什么可以帮您？</span>
							</div>
							<div class="lim_tale">
								<div id="radiusborder"></div>
							</div>
						</div>
						<!-- 客服对话 -->
						<!-- <div class="lim_time">08:56:03</div>
						<div class="lim_operator lim_clearfloat ">
							<div class="lim_bubble lim_shadow">
							<p class="lim_dot">您好，欢迎您访问！请问有什么需要咨询？</p>
							</div>
							<div class="lim_tale">
								<div id="radiusborder"></div>
							</div>
						</div> -->
						<!-- 用户对话 -->
						<!-- <div class="lim_time">08:56:03</div>
						<div class="lim_visitor lim_clearfloat ">
							<div class="lim_bubble lim_shadow">
								<p class="lim_dot">你好啊</p>
							</div>
							<div class="lim_tale">
								<div id="radiusborder"></div>
							</div>
						</div> -->
					</div>
				</div>
					<div id="entry-bar">
						<div class="menubar">
							
						</div>
						<div class="inputbox themeborder">
							<div class="fix">
								<textarea id="inputbox" name="inputbox" placeholder="请输入"></textarea>
								<input type="hidden" id="makerId" value="${detail.id }" />
							</div>
							
						</div>
						<div class="actionbar themeborder">
							
							<div id="adLeftBotm">
								<a class="ahr_clear" href="#" target="_blank">
									<span class="desby">Designed&nbsp;by</span>
									<span class="brand">&nbsp;SICD</span>
								</a>
								
								<span id="inputbox_validate" style="color: red"></span>
							</div>
							
							<div class="enter normal themeborder" id="enterBt">								
								<span id="enter">发送</span>
								<span class="shortcut" id="shortcut" style="color:blue"></span>
							</div>
						</div>
						<div class="backdrop"></div>
				</div>
					
				</div>
            	<div id="right_banner" class="themeborder">
            		<div class="banContent">
            			<img class="banimg" src="<%=request.getContextPath()%>${detail.img }" border="1"/>
						
					
					<div><h4>${detail.name }<span>${detail.job }</span></h4></div>
					<p><span>从业时间：</span>${detail.workTime }</p>
					<p><span>咨询热线：</span>${detail.phone }</p>
					<p><span>服务时间：</span>${detail.serviceTime }</p>
					   
            		</div>
            	</div>
				
			</div>
			
		</div>


	

</body>
</html>