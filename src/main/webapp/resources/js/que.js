$(function() {
	daoQue();
});
//倒数据
function daoQue(){
	var url="/que/daoPersonDone?page=1&pagesize=1500";
	$.getJSON(url, function(data) {
		
	});
}

function MM_showHideLayers() { //v9.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}
function artDialogClose() {
		var list = art.artDialog.list;
		for (var i in list) {
			list[i].close();
		}
	}    

function showQueDiv(){
	var url="/que/index";
	$.getJSON(url, function(data) {
		if(data.result.queInfo==null){
			showDoneQueDiv();return;
		}		
		var html_="";
		 html_+="	<div class=\"ask\">";
		 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
			 html_+="<h3>";
				 html_+="<span>你已回答"+data.result.myCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
					 html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\" style=\"z-index:10\">";
						 html_+="<div class=\"menu\">";
						 $.each(data.result.tagQuelist,function(i,item){							 
							 html_+="<a href=\"javascript:goNext("+1+","+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
						 });
							               
										 html_+="<a href=\"javascript:goNext("+1+","+0+");\">全部（"+data.result.myCountDone+"/"+data.result.countQue+"）</a>";
											 html_+=" </div>";
												 html_+="</div>";
													 html_+=" </h3>";
			if(data.result.queInfo.pic){
				 html_+=" <div class=\"askPic\">";
					 html_+="<img src=\""+data.result.queInfo.pic+"\" alt=\""+data.result.queInfo.que+"\" />";
					 html_+=" <h5>"+data.result.queInfo.que+"</h5>";
              
					html_+=" <a href=\"javascript:doneQue("+data.result.queInfo.id+","+0+","+data.result.page+","+data.result.tag+");\" class=\"veryLike\">非常喜欢<div class=\"black\"></div><span>非常喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+1+","+data.result.page+","+data.result.tag+");\" class=\"Like\">喜欢<div class=\"black\"></div><span>喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+2+","+data.result.page+","+data.result.tag+");\" class=\"noLike\">不喜欢<div class=\"black\"></div><span>不喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+3+","+data.result.page+","+data.result.tag+");\" class=\"veryHate\">非常讨厌<div class=\"black\"></div><span>非常讨厌</span></a>";
					html_+=" </div>";
			}else{										 
			 html_+=" <h4>"+data.result.queInfo.que+"</h4>";
			 html_+=" <table>";
			html_+="   <tr>";
			 html_+=" <td  onclick=\"javascript:doneQue("+data.result.queInfo.id+","+0+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[0]+"</td>";
		   html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+1+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[1]+"</td>";
			 html_+="</tr>";		
			 if(data.result.queInfo.ans[2]){
				 html_+=" <tr>";				
					 html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+2+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[2]+"</td>";
					 if(data.result.queInfo.ans[3]){
					 html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+3+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[3]+"</td>";
					 }
					 html_+=" </tr>";
			 }
								 html_+="</table>";
			}						 
 html_+="<div class=\"askPage\">";
	 html_+="<a href=\"javascript:goNext("+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
		 html_+="  <span>"+data.result.page+"/"+data.result.notCountDone+"</span>";
		        html_+="   <a href=\"javascript:goNext("+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
		        	html_+="  </div>";
		        		html_+="</div>";
		
		art.dialog({content: html_});
	});
}

function goNext(page,tag){
	  if(page<1){
		  page=1;
	  }
		var url="/que/index";
		$.getJSON(url,{page:page,tag:tag}, function(data) {
			var html_="";
			 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
			 html_+="<h3>";
				 html_+="<span>你已回答"+data.result.myCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
					 html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\" style=\"z-index:10\">";
						 html_+="<div class=\"menu\">";
						 $.each(data.result.tagQuelist,function(i,item){							 
							 html_+="<a href=\"javascript:goNext("+1+","+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
						 });
							               
										 html_+="<a href=\"javascript:goNext("+1+","+0+");\">全部（"+data.result.myCountDone+"/"+data.result.countQue+"）</a>";
											 html_+=" </div>";
												 html_+="</div>";
													 html_+=" </h3>";
			if(data.result.queInfo.pic){
				 html_+=" <div class=\"askPic\">";
					 html_+="<img src=\""+data.result.queInfo.pic+"\" alt=\""+data.result.queInfo.que+"\" />";
					 html_+=" <h5>"+data.result.queInfo.que+"</h5>";
              
					html_+=" <a href=\"javascript:doneQue("+data.result.queInfo.id+","+0+","+data.result.page+","+data.result.tag+");\" class=\"veryLike\">非常喜欢<div class=\"black\"></div><span>非常喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+1+","+data.result.page+","+data.result.tag+");\" class=\"Like\">喜欢<div class=\"black\"></div><span>喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+2+","+data.result.page+","+data.result.tag+");\" class=\"noLike\">不喜欢<div class=\"black\"></div><span>不喜欢</span></a>";
					html_+="<a href=\"javascript:doneQue("+data.result.queInfo.id+","+3+","+data.result.page+","+data.result.tag+");\" class=\"veryHate\">非常讨厌<div class=\"black\"></div><span>非常讨厌</span></a>";
					html_+=" </div>";
			}else{										 
			 html_+=" <h4>"+data.result.queInfo.que+"</h4>";
			 html_+=" <table>";
			html_+="   <tr>";
			 html_+=" <td  onclick=\"javascript:doneQue("+data.result.queInfo.id+","+0+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[0]+"</td>";
		   html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+1+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[1]+"</td>";
			 html_+="</tr>";		
			 if(data.result.queInfo.ans[2]){
				 html_+=" <tr>";				
					 html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+2+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[2]+"</td>";
					 if(data.result.queInfo.ans[3]){	
					      html_+="<td onclick=\"javascript:doneQue("+data.result.queInfo.id+","+3+","+data.result.page+","+data.result.tag+");\">"+data.result.queInfo.ans[3]+"</td>";
					 }		
					 html_+=" </tr>";
			 }
								 html_+="</table>";
			}						 
 html_+="<div class=\"askPage\">";
	 html_+="<a href=\"javascript:goNext("+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
		 html_+="  <span>"+data.result.page+"/"+data.result.notCountDone+"</span>";
		        html_+="   <a href=\"javascript:goNext("+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
		        	html_+="  </div>";
		        	  $(".ask").html(html_);
		});
	  
}

function doneQue(queid,ans,page,tag){
	var url="/que/doneQue";
	$.getJSON(url,{queid:queid,ans:ans},function(data){
				if(data.result){
					 goNext(page,tag);
				}				
	});	
}

function  showDoneQueDiv(){
	var url="/que/showMyDoneQue";
	$.getJSON(url,function(data){
		if(data.result.myDoneQue==null){
			showQueDiv();return;
		}
		var html_="";
		html_+="<div class=\"ask\">";
		 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
	html_+="<h3>";
	html_+="<span>你已回答"+data.result.myCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
	html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">";
	html_+="	<div class=\"menu\">";
	 $.each(data.result.tagQuelist,function(i,item){			
	 html_+="<a href=\"javascript:goDoneNext(1,"+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
	 });
		               
	 html_+="<a href=\"javascript:goDoneNext(1,0);\">全部（"+data.result.myCountDone+"/"+data.result.countQue+"）</a>";

		html_+=" </div>";
			html_+="</div>";
				html_+="</h3>";
				$.each(data.result.myDoneQue,function(i,item){
					html_+="<div class=\"answer\">";
					html_+="	<h6><em class=\"bj11\">白</em><span>"+item.queInfo.que+"</span></h6>";
						html_+=" <div class=\"clear\"></div>";
							html_+=" <select name=\"ans\" id=\"ans_"+item.id+"\">";
							 $.each(item.queInfo.ans,function(n,val){
								 if(n==item.doneInfo.ans){
									 html_+="  <option value=\""+n+"\" selected>"+val+"</option>";
								 }else{
								 html_+="  <option value=\""+n+"\">"+val+"</option>";
								 }
							 });
							
													html_+="</select>";
														html_+=" <a href=\"javascript:updateQue("+item.id+");\">修 改</a>";
															html_+=" </div>";
				});
					
																

	html_+="<div class=\"askPage\">";
		html_+="<a href=\"javascript:goDoneNext("+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
			html_+="  <span>"+data.result.page+"/"+data.result.topPage+"</span>";
				html_+=" <a href=\"javascript:goDoneNext("+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
					html_+="</div>";
						html_+="</div>";
						art.dialog({content: html_});
});	
}
function goDoneNext(page,tag){
	if(page<1){
		  page=1;
	  }
	var url="/que/showMyDoneQue";
	$.getJSON(url,{page:page,tag:tag},function(data){
		var html_="";
		 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
			html_+="<h3>";
			html_+="<span>你已回答"+data.result.myCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
			html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">";
			html_+="	<div class=\"menu\">";
			 $.each(data.result.tagQuelist,function(i,item){			
			 html_+="<a href=\"javascript:goDoneNext(1,"+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
			 });
				               
			 html_+="<a href=\"javascript:goDoneNext(1,0);\">全部（"+data.result.myCountDone+"/"+data.result.countQue+"）</a>";

				html_+=" </div>";
					html_+="</div>";
						html_+="</h3>";
						$.each(data.result.myDoneQue,function(i,item){
							html_+="<div class=\"answer\">";
							html_+="	<h6><em class=\"bj11\">白</em><span>"+item.queInfo.que+"</span></h6>";
								html_+=" <div class=\"clear\"></div>";
									html_+=" <select name=\"ans\" id=\"ans_"+item.id+"\">";
									 $.each(item.queInfo.ans,function(n,val){
										 if(n==item.doneInfo.ans){
											 html_+="  <option value=\""+n+"\" selected>"+val+"</option>";
										 }else{
										 html_+="  <option value=\""+n+"\">"+val+"</option>";
										 }
									 });
									
															html_+="</select>";
																html_+=" <a href=\"javascript:updateQue("+item.id+","+item.queid+");\">修 改</a>";
																	html_+=" </div>";
						});
							
																		

			html_+="<div class=\"askPage\">";
				html_+="<a href=\"javascript:goDoneNext("+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
					html_+="  <span>"+data.result.page+"/"+data.result.topPage+"</span>";
						html_+=" <a href=\"javascript:goDoneNext("+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
							html_+="</div>";
							  $(".ask").html(html_);
	});
}
function updateQue(doneId,queid){
	var url="/que/updateDoneQue";
	var ans=$("#ans_"+doneId+" option:selected").val();
	$.getJSON(url,{ans:ans,doneId:doneId,queid:queid},function(data){
		 
	});
}

////////////////////ta人页面 看问答

function showPersonDoneQueDiv(userid){
	  var url="/que/showPersonDone";
	  $.getJSON(url,{userid:userid},function(data){
		var html_="";
		html_+="<div class=\"ask\">";
		 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
		html_+="<h3>";
		html_+="<span>"+data.result.genderChn+"已回答"+data.result.personCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
		html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">";
		html_+="	<div class=\"menu\">";
		 $.each(data.result.tagQuelist,function(i,item){			
		 html_+="<a href=\"javascript:goPersonDoneNext("+userid+",1,"+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
		 });
			               
		 html_+="<a href=\"javascript:goPersonDoneNext("+userid+",1,0);\">全部（"+data.result.personCountDone+"/"+data.result.countQue+"）</a>";

			html_+=" </div>";
				html_+="</div>";
					html_+="</h3>";
					$.each(data.result.personDoneQue,function(i,item){
						
						if(item.myDoneInfo!=""){
							  if(item.doneInfo.ans==item.myDoneInfo.ans){
								  //相同 绿色
								  html_+=" <div class=\"answer\">";
									  html_+="<h6><em class=\"bj13\">绿</em><span>"+item.queInfo.que+"</span></h6>";
										  html_+="  <div class=\"clear\"></div>";
										  html_+=" <div class=\"txt\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
										  html_+=" <div class=\"txt\"><span>我：</span><div title=\""+item.queInfo.ans[item.myDoneInfo.ans]+"\">"+item.queInfo.ans[item.myDoneInfo.ans]+"</div></div>";
								
												  html_+=" </div>";
							  }else{
								  // 不同黄色
								  html_+=" <div class=\"answer\">";
								  html_+="<h6 class=\"yellow\"><em class=\"bj12\">黄</em><span>"+item.queInfo.que+"</span></h6>";
									  html_+="  <div class=\"clear\"></div>";
									
											  html_+=" <div class=\"txt yellow\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
											  html_+=" <div class=\"txt yellow\"><span>我：</span><div title=\""+item.queInfo.ans[item.myDoneInfo.ans]+"\">"+item.queInfo.ans[item.myDoneInfo.ans]+"</div></div>";
										
											  html_+=" </div>";
							  }
						}else{						
							html_+="<div class=\"answer\" id=\"answer_"+item.queid+"\">";
							html_+="	<h6><em class=\"bj11\">白</em><span>"+item.queInfo.que+"</span></h6>";
								html_+=" <div class=\"clear\"></div>";
									html_+=" <select name=\"ans\" id=\"ans_"+item.queid+"\">";
									html_+=" 	<option value=\"-1\">请你选择，查看你们俩的结果</option>";
									 $.each(item.queInfo.ans,function(n,val){									
										 html_+="  <option value=\""+n+"\">"+val+"</option>";										
									 });								
								html_+="</select>";
								html_+=" <a href=\"javascript:ajaxDoneQue("+item.queid+","+item.doneInfo.ans+");\">提 交</a>";
								html_+=" </div>";
								
								  html_+=" <div class=\"answer\" style=\"display:none\" id=\"after_answer_"+item.queid+"\">";
								  html_+="<h6 id=\"h6_"+item.queid+"\" class=\"yellow\"><em id=\"em_"+item.queid+"\" class=\"bj12\">黄</em><span>"+item.queInfo.que+"</span></h6>";
									  html_+="  <div class=\"clear\"></div>";
									
											  html_+=" <div  id=\"txt_p_"+item.queid+"\" class=\"txt yellow\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
											  html_+=" <div  id=\"txt_m_"+item.queid+"\" class=\"txt yellow\"><span>我：</span><div id=\"my_answer_"+item.queid+"\"></div></div>";
										  
											  html_+=" </div>";
						}
					});
						
																	

		html_+="<div class=\"askPage\">";
			html_+="<a href=\"javascript:goPersonDoneNext("+userid+","+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
				html_+="  <span>"+data.result.page+"/"+data.result.topPage+"</span>";
					html_+=" <a href=\"javascript:goPersonDoneNext("+userid+","+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
						html_+="</div>";
							html_+="</div>";
							art.dialog({content: html_});
	  });
	  
}

function goPersonDoneNext(userid,page,tag){
	if(page<1){
		  page=1;
	  }
	  var url="/que/showPersonDone";
	  $.getJSON(url,{userid:userid,page:page,tag:tag},function(data){
		var html_="";
		 html_+="<a href=\"javascript:artDialogClose();\" class=\"close\">关闭</a>";
			html_+="<h3>";
			html_+="<span>"+data.result.genderChn+"已回答"+data.result.personCountDone+"个问答</span><a href=\"#\" class=\"link\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">按分类查看</a>";
			html_+="<div class=\"askMenu\" id=\"askMenu\" onmouseout=\"MM_showHideLayers('askMenu','','hide')\" onmouseover=\"MM_showHideLayers('askMenu','','show')\">";
			html_+="	<div class=\"menu\">";
			 $.each(data.result.tagQuelist,function(i,item){			
			 html_+="<a href=\"javascript:goPersonDoneNext("+userid+",1,"+item.id+");\">"+item.tag_str+"（"+item.tagCountDone+"/"+item.tagCount+"）</a>";
			 });
				               
			 html_+="<a href=\"javascript:goPersonDoneNext("+userid+",1,0);\">全部（"+data.result.personCountDone+"/"+data.result.countQue+"）</a>";

				html_+=" </div>";
					html_+="</div>";
						html_+="</h3>";
						$.each(data.result.personDoneQue,function(i,item){							
							if(item.myDoneInfo!=""){
								  if(item.doneInfo.ans==item.myDoneInfo.ans){
									  //相同 绿色
									  html_+=" <div class=\"answer\" id=\"answer_"+item.queid+"\">";
										  html_+="<h6><em class=\"bj13\">绿</em><span>"+item.queInfo.que+"</span></h6>";
											  html_+="  <div class=\"clear\"></div>";
											  html_+=" <div class=\"txt\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
											  html_+=" <div class=\"txt\"><span>我：</span><div title=\""+item.queInfo.ans[item.myDoneInfo.ans]+"\">"+item.queInfo.ans[item.myDoneInfo.ans]+"</div></div>";
									
													  html_+=" </div>";
								  }else{
									  // 不同黄色
									  html_+=" <div class=\"answer\" id=\"answer_"+item.queid+"\">";
									  html_+="<h6 class=\"yellow\"><em class=\"bj12\">黄</em><span>"+item.queInfo.que+"</span></h6>";
										  html_+="  <div class=\"clear\"></div>";
										
												  html_+=" <div class=\"txt yellow\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
												  html_+=" <div class=\"txt yellow\"><span>我：</span><div title=\""+item.queInfo.ans[item.myDoneInfo.ans]+"\">"+item.queInfo.ans[item.myDoneInfo.ans]+"</div></div>";
											
												  html_+=" </div>";
								  }
							}else{						
							html_+="<div class=\"answer\" id=\"answer_"+item.queid+"\">";
							html_+="	<h6><em class=\"bj11\">白</em><span>"+item.queInfo.que+"</span></h6>";
								html_+=" <div class=\"clear\"></div>";
									html_+=" <select name=\"ans\" id=\"ans_"+item.queid+"\">";
									html_+=" 	<option value=\"-1\">请你选择，查看你们俩的结果</option>";
									 $.each(item.queInfo.ans,function(n,val){									
										 html_+="  <option value=\""+n+"\">"+val+"</option>";										
									 });								
								html_+="</select>";
								html_+=" <a href=\"javascript:ajaxDoneQue("+item.queid+","+item.doneInfo.ans+");\">提 交</a>";
								html_+=" </div>";
								
								  html_+=" <div class=\"answer\" style=\"display:none\" id=\"after_answer_"+item.queid+"\">";
								  html_+="<h6 id=\"h6_"+item.queid+"\" class=\"yellow\"><em id=\"em_"+item.queid+"\" class=\"bj12\">黄</em><span>"+item.queInfo.que+"</span></h6>";
									  html_+="  <div class=\"clear\"></div>";
									
											  html_+=" <div id=\"txt_p_"+item.queid+"\" class=\"txt yellow\"><span>"+data.result.personInfo.nickName+"：</span><div title=\""+item.queInfo.ans[item.doneInfo.ans]+"\">"+item.queInfo.ans[item.doneInfo.ans]+"</div></div>";
											  html_+=" <div id=\"txt_m_"+item.queid+"\" class=\"txt yellow\"><span>我：</span><div id=\"my_answer_"+item.queid+"\"></div></div>";
										  
											  html_+=" </div>";
								
								
							}
						});																		

			html_+="<div class=\"askPage\">";
				html_+="<a href=\"javascript:goPersonDoneNext("+userid+","+(parseInt(data.result.page)-1)+","+data.result.tag+");\" class=\"arrowL\"><em>前</em></a>";
					html_+="  <span>"+data.result.page+"/"+data.result.topPage+"</span>";
						html_+=" <a href=\"javascript:goPersonDoneNext("+userid+","+(parseInt(data.result.page)+1)+","+data.result.tag+");\" class=\"arrowR\"><em>后</em></a>";
							html_+="</div>";
							  $(".ask").html(html_);
	  });
	
}
function ajaxDoneQue(queid,personAns){
	var ans=$("#ans_"+queid+" option:selected").val();
	var ansStr=$("#ans_"+queid+" option:selected").text();
	var url="/que/doneQue";
	$.getJSON(url,{queid:queid,ans:ans},function(data){
				if(data.result){
					 //对比答案
					$("#answer_"+queid).attr("style","display:none");
					if(ans==personAns){
						$("#after_answer_"+queid).attr("style","display:block;");
						$("#h6_"+queid).attr("class","");
						$("#em_"+queid).attr("class","bj13");
						$("#my_answer_"+queid).html(ansStr);
						$("#txt_p_"+queid).attr("class","txt");
						$("#txt_m_"+queid).attr("class","txt");
					}else{
						$("#after_answer_"+queid).attr("style","display:block;");
						$("#my_answer_"+queid).html(ansStr);
					}
					
				}				
	});	
}


