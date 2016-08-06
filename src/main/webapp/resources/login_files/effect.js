(function($) {
	$.fn.extend({
		isChildOf: function(parent) {
			return $(parent).find(this).length > 0;
		}
	});
})(jQuery);


//左侧导航菜单 显示/隐藏 
function leftNavLayer(id,num){
	if(num==1)
	{
		$("#1"+id).attr("class","active"); 
		$("#2"+id).css("display","block");
	}else if(num==2){
		$("#1"+id).attr("class","portrait"); 
		$("#2"+id).css("display","none");
	}else if(num==3){
		$("#3"+id).attr("class","menuActive"); 
		$("#4"+id).css("display","block");
	}else if(num==4){
		$("#3"+id).attr("class","menuNav"); 
		$("#4"+id).css("display","none");
	}
}

//首页右侧浮层菜单
$(document).ready(function(){
	
	$("#floatShow").bind("click",function(){
		$('#rightMenuCont').animate({width: 'show', opacity: 'show'}, 'normal',function(){
			$('#rightMenuCont').show();
		});
		$('#floatShow').attr('style','display:none');
		$('#floatHide').attr('style','display:block');
		return false;
	});
	
	$("#floatHide").bind("click",function(){
		$('#rightMenuCont').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){
			$('#rightMenuCont').hide();
		});
		$('#floatShow').attr('style','display:block');
		$('#floatHide').attr('style','display:none');
	});
	
	$(document).bind("click",function(event){
		if($(event.target).isChildOf("#rightMenu") == false){
			$('#rightMenuCont').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){
				$('#rightMenuCont').hide();
			});
			$('#floatShow').attr('style','display:block');
			$('#floatHide').attr('style','display:none');
		}
	});
});

/*---忘记密码---*/

function ForgotPassword(){

var dhtml = "";

dhtml +="<div id=\"layer\">";
dhtml +="	 <div class=\"top black\">";
dhtml += "		<dl>";
dhtml += "			<dt>修改密码</dt>";
dhtml += "			<dd><a href=\"#\" id=\"close_box_01\" class=\"close\">关闭</a></dd>";
dhtml += "		</dl>";
dhtml += "	</div>";
dhtml += "	<div class=\"cont black\">";
dhtml += "		<div class=\"mobile\">";
dhtml += "			<dl>";
dhtml += "				<dt>原密码：</dt>";
dhtml += "				<dd>";
dhtml += "					<input name=\"\" type=\"text\" value=\"\" /><br />";
dhtml += "					<span>原密码不正确~~~</span>";
dhtml += "					<div class=\"ok\"></div>";
dhtml += "				</dd>";
dhtml += "			</dl>";
dhtml += "			<dl style=\"height:76px;\">";
dhtml += "				<dt>新密码：</dt>";
dhtml += "				<dd>";
dhtml += "					<input name=\"\" type=\"password\" value=\"\" />";
dhtml += "					<span class=\"txtRed\">6-16位英文字母或数字。建议使用英文字母加数字的混合密码。</span>";
dhtml += "					<div class=\"error\"></div>";
dhtml += "				</dd>";
dhtml += "			</dl>";
dhtml += "			<dl>";
dhtml += "				<dt>确认新密码：</dt>";
dhtml += "				<dd>";
dhtml += "					<input name=\"\" type=\"password\" value=\"\" /><br />";
dhtml += "					<span>与新密码不符~~~</span>";
dhtml += "					<div class=\"error\"></div>";
dhtml += "				</dd>";
dhtml += "			</dl>";
dhtml += "			<a href=\"#\" class=\"red\">修改密码</a>";
dhtml += "		</div>";
dhtml += "	</div>";
dhtml += "</div>";

	$.blockUI(dhtml);
	
	$("[id^=close_box_]").click(function(){
       $.unblockUI();
    });	

};

//倒计时
var interval = 1000; 
var now = new Date(); 
var nowMon=now.getMonth()+1;
var nowData=now.getDate()+1;

function ShowCountDown(year,month,day) 
{
	var endDate = new Date(year, month-1, day); 
	var newd =  new Date(); 
	var leftTime=endDate.getTime()-newd.getTime(); 
	var leftsecond = parseInt(leftTime/1000); 
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600);
	if(hour<10){
		hour = "0"+hour;
	}
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60);
	
	if(minute<10){
		minute = "0"+minute;
	}
	
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
	if(second<10){
		second = "0"+second;
	}
	$("#showtime").html(hour+":"+minute+":"+second);
} 
		
//$(document).ready(function()
//{
//	ShowCountDown((new Date).getFullYear(),nowMon,nowData);
//	var buyObj=new Image();
//	buyObj.src="http://bhtg.baihe.com/stat.html?ggCode=jzzty1_5"; 
//});
window.setInterval(function(){ShowCountDown((new Date).getFullYear(),nowMon,nowData);}, interval); 

//--客户端下载页
function roll(){
	$(function(){
		(function(){
			var curr = 0;
			$("#roll .tab").each(function(i){
				$(this).click(function(){
					curr = i;
					$("#rollcont .left img").eq(i).fadeIn("slow").siblings("img").hide();
					$("#rollcont .pic").eq(i).fadeIn("slow").siblings(".pic").hide();
					$(this).siblings(".tab").removeClass("active").end().addClass("active");
					return false;
				});
			});
			
			var pg = function(flag){
				//flag:true表示前翻， false表示后翻
				if (flag) {
					if (curr == 0) {
						todo = 3;
					} else {
						todo = (curr - 1) % 4;
					}
				} else {
					todo = (curr + 1) % 4;
				}
				$("#roll .tab").eq(todo).click();
			};
			
			//前翻
			$("#prev").click(function(){
				pg(true);
				return false;
			});
			
			//后翻
			$("#next").click(function(){
				pg(false);
				return false;
			});
			
			//自动翻
			var timer = setInterval(function(){
				todo = (curr + 1) % 3;
				$("#roll .tab").eq(todo).click();
			},8000);
			
			//鼠标悬停在触发器上时停止自动翻
			$("#roll a").hover(function(){
					clearInterval(timer);
				},
				function(){
					timer = setInterval(function(){
						todo = (curr + 1) % 3;
						$("#roll .tab").eq(todo).click();
					},2500);			
				}
			);
		})();
	});
};
//]]>

//---自适应高度
$(function(){
	var winH = $(window).height();
	var winW = $(window).width();
	var cloudH = (winH-100)/2;
	var cloudW = (winW-300)/2;
	var blackW = cloudW - 30;
	var blackH = cloudH - 70;
	var lineW = cloudW - 240;
	var lineH = blackH - 90;
	
	var authW = $(window).width()-210;
	
//alert(cloudW)
	cloudCH(cloudH,cloudW)
	blackCH(blackW,blackH,lineW,lineH)
	authCW(authW);
	
	$(window).resize(function()
	{
		var changH = ($(this).height()-100)/2;
		var changW = ($(this).width()-300)/2;
		var changAuthW = $(this).width()-210;
		
		if(changH != cloudH || changW != cloudW )
		{
			cloudCH(changH,changW);
			var changBH = changH-70;
			var changBW = changW-30;
			var changLineW = changW - 240;
			var changLineH = changH - 160;
			blackCH(changBW,changBH,changLineW,changLineH);
		}
		else
		{
			authCW(authW);
			cloudCH(cloudH,cloudW);
			blackCH(cloudW,blackH,lineW,lineH)
		}
		
		if(authW != changAuthW)
		{
			authCW(changAuthW);	
		}
		else
		{
			authCW(authW);	
		}
    });
	
	function cloudCH(h,w)
	{

		$(".cloud .column").width(w+"px").height(h +"px");
	}
	
	function blackCH(w,h,lw,lh)
	{
		$(".cloud .black").height(h +"px").width(w + "px");
		$(".cloud .black .line_w").width(lw +"px");
		$(".cloud .black .line_h").height(lh +"px");
		
		var num = lh/6;
		$(".cloud .dlfx ul li").height(num +"px");
		
		if (typeof afterSetLiHeight != 'undefined' && $.isFunction(afterSetLiHeight)) {
            afterSetLiHeight(num);
        }
		
		var fh = (h/5)-5;
		var fw = (($(window).width()-360)/2)-134;
		$(".cloud .data .fixedW").width(fw +"px").height(fh + "px");
	}
	
	function authCW(w)
	{
		$("#content .auth").width(w+"px");
	}
})


//----认证中心横向滚动
function scroll()
{
	$(".list").niceScroll(
	{
		  cursoropacitymin:0,
		  cursoropacitymax:1,
		  cursorcolor:"#ababab",
		  cursorwidth:"3px",
		  cursorborder:"1px solid #ababab",
		  cursorborderradius:"3px",
		  scrollspeed:60,
		  mousescrollstep:8*3
	});
};