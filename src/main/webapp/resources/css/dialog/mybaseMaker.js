var error = "";
var isInputMeet = false;		//标记用户所填写的密码否符合要求
$(document).ready(function(){
	/*setTimeout('autoRun()', 2000);
	//** 发送*/
	$("#enter").click(function(){
		  inputbox();
		  if(isInputMeet){
			  maker();
			  makerSend();
			  clearInput();
		  }
	  });
	//getData();
	/*3s轮询读取函数*/
	setInterval(function() {
	        	getData();
	    },2000);
});


function getData(){
	$.ajax({
		url:'/FriendsPlatform/visterGet',
		type:'post',
		async:false,	//默认为true，这里需要改为false
		success:function(result) {
			/*if(result=="fail"){
				
			}else{*/
				//拿到返回值
				var data = JSON.parse(result);
				//if(!data.content||!data.currentTime){
					$("#makerpadding")
					.append(" <div class='lim_time'>"
					+ " "+data.currentTime +"</div>"
					+ "  <div class='lim_visitor lim_clearfloat '>"
					+ "  	 <div class='lim_bubble lim_shadow'>"
					+ " 	 <p class='lim_dot'>"+ data.content +"</p>"
					+ "  	 </div>"
					+ "  	 <div class='lim_tale'>"
					+ "  	 	<div id='radiusborder'></div>"
					+ " 	 </div>"
					+ "  </div>");
				//}
//			}
		}
	});
}
		/**/
function clearInput(){
	$("#inputbox").val("");
}

function makerSend(){
	 var makercontent = $("#inputbox").val();
	 var myDate=new Date()
	 var makercurrentTime = fix(myDate.getHours()) + ':' + fix(myDate.getMinutes()) + ':' + fix(myDate.getSeconds());  
			$.ajax({
				url:'/FriendsPlatform/makerSend',
				data:{
					makercurrentTime:makercurrentTime,
					makercontent:makercontent
				},
				type:'post',
				async:false,	//默认为true，这里需要改为false
				success:function(result) {
					//clearInput();
				}
			});
}


function maker(){
	var inputbox = $("#inputbox").val();
	var myDate=new Date()
	var currentTime = fix(myDate.getHours()) + ':' + fix(myDate.getMinutes()) + ':' + fix(myDate.getSeconds());  
		$("#makerpadding")
		.append(" <div class='lim_time'>"
		+ " "+currentTime +"</div>"
		+ "  <div class='lim_operator lim_clearfloat '>"
		+ "  	 <div class='lim_bubble lim_shadow'>"
		+ " 	 <p class='lim_dot'>"+ inputbox +"</p>"
		+ "  	 </div>"
		+ "  	 <div class='lim_tale'>"
		+ "  	 	<div id='radiusborder'></div>"
		+ " 	 </div>"
		+ "  </div>");
	
}
function fix(n){
	return n<10?"0"+n:n;
}

/** 验证输入*/
function inputbox(){
	var inputbox = $("#inputbox").val();
	if(inputbox == ""){
		error = "输入不能为空";
		$("#inputbox_validate").text(error);
		isInputMeet = false;
	}else{
		$("#inputbox_validate").text("");
		isInputMeet=true;
	}
}
