hostname = "http://vip.baihe.com/vipImp/";


var _baiheHead_start_time = new Date();

/*
 检验电话
 @s 待检验的字符
*/

function isMobile(s)
	{
		if (s.length!= 11) return false;
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
		if (!patrn.exec(s)) return false;
		return true;
}
 
function isTel(s)
	{
		if (s.length!= 11) return false;
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
		if (!patrn.exec(s)) return false;
		return true;
} 

/*
	检验数字
	@num 待检验的字符
*/
	isnum = function(num)
	{
		var test = /^[1-9]\d*$/;
		if(test.test(num))
		  return true;
		else
		  return false;
	}


	var pars;
	
	var options = {
		method:'post',
		parameters:pars,
		evalScripts:true
	}
	showAjax = function(url,pars,elementId)
	{
		options.parameters = pars;
		var myAjax = new Ajax.Updater(
		elementId,
		url,options
		);
	}
	

	var value1;
	showAjaxValue = function(url,pars)
	{
		optionsValue.parameters = pars;
		var myAjax = new Ajax.Request(
			url,
			optionsValue
		);
		return value1;
	}
	var optionsValue = {
		method:'post',
		parameters:pars,
		onComplete:function(request){showResponse(request);
		}
	}
	
	/*jquery ajax*/
	var ajax = {
		Success : function(){},
		error : function(){},
		complete : function(){},
		ajaxReturn : function(Url,para){
			$.ajax({type:'post',data:para,url:Url,success:this.Success,error:this.error});
		},
		ajaxHtml : function(Url,para){
			$.ajax({type:'post',data:para,url:Url,complete:this.complete,error:this.error});
		}
	};	
	
	function trim(str){  
	 return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	function ltrim(str){  
	 return str.replace(/(^\s*)/g,"");
	}
	function rtrim(str){  
	 return str.replace(/(\s*$)/g,"");
	}	
	
	/*验证单选框*/
	checkRadio = function(obj){
       	var Check = false;
       	obj.each(function(){
       		if(this.checked == true){
       			Check = true;
       		}
       	});
       	if(Check == false){
       		return false;
       	}else{
       		return true;
       	}	
	}
	
	/*设置cookies*/
	setCookies = function(name ,value){
		var Days = 60; 
		var exp = new Date(); 
		exp.setTime(exp.getTime()+Days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
		
	}
	
	/* 
	功能：获取cookies函数 
	参数：name，cookie名字 
	*/ 
	function getCookie(name){ 
		var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)")); 
		if(arr != null) {
			if(arr[2] == 'undefined')
				return null;
			else
				return unescape(arr[2]); 
		}
		return null; 
	
	} 
	
	/* 
	功能：删除cookies函数 
	参数：name，cookie名字 
	*/ 
	
	function delCookie(name){ 
		var exp = new Date(); 
		exp.setTime(exp.getTime() - 1); 
		var cval=getCookie(name); 
		if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
	}
	
	function getParameter(param)
	{
		var query = window.location.search;
		var iLen = param.length;
		var iStart = query.indexOf(param);
		if (iStart == -1)
		   return "";
		iStart += iLen + 1;
		var iEnd = query.indexOf("&", iStart);
		if (iEnd == -1){
		   return query.substring(iStart);
		}
		return query.substring(iStart, iEnd);
	}
	
	/*js 样式变换
	 *id 控件的id，数字
	 *name 控件的名字
	 *clasname Class name
	*/
	changClass = function(id,name,classname,length){
	 	for(var i = 1 ;i <= length;i++){
	 		var obj = document.getElementById(name + i);
	 		if(i == id){
	 			obj.className = 'on';
	 		}
	 		else{
	 			obj.className = '';	 		
	 		}
	 	}		
	}	
	
	/*点击变换内容 display:none
	 *id 控件的id，数字
	 *name 控件的名字
	 *clasname Class name
	*/
	changeDisplay = function(id,name,length){
	 	for(var i = 1 ;i <= length;i++){
	 		var obj = document.getElementById(name + i);
	 		if(i == id){
	 			obj.style.display = 'block';
	 		} 			
	 		else{
	 			obj.style.display = 'none'; 		
	 		}
	 	}	
	}	
	
	/*设置cookies[url],不同的渠道回到不同的首页*/
	setWhichIndex = function(){
		var channel = getParameter('Channel');
		

		/*不同频道不同首页*/
		var index = window.location.pathname.indexOf("mainsiteIndex");
		var cookies = (channel == 'mainsite' || (channel == '' && index > -1) ) ? 'false' : 'true';
		delCookie('url');
		setCookies('url',cookies);
	}
	
	/*获取cookies[url],不同的渠道回到不同的首页*/
	getWhichIndex = function(name){
		var name = getCookie(name);
		if(name == 'false'){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**检查下拉框*/
	checkSelect = function(value){
		if(value == "-1"){
			return false;
		}else{
			return true;
		}
		
	}
$(function(){

	var Channel = getQueryString("Channel");
	var code1 = getQueryString("Code");
	var policy = getQueryString("policy");
	if(Channel!=null&&Channel!=""){
		setCookies("Channel",Channel);
	}
	if(Channel!=null&&Channel!=""){
		setCookies("Code",code1);
	}
	if(Channel!=null&&Channel!=""){
		setCookies("policy",policy);
	}
});	