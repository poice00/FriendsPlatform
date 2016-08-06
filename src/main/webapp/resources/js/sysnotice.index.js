$(function() {
	getSysNotice();
});
var noticeid=0;
function getSysNotice(){	
	var func = arguments.callee,
	self = this;
	var url="/sysnotice/index";
	$.getJSON(url,function(data){
		if(data.result[0].length){
		var _html="<em></em>"+data.result[0];
		noticeid=data.result[1];
		 $(".promptTxt02").html(_html);
		}
		setTimeout(function() {
			func.call(self);
		}, 5000);
	});
}

function saveNotice(){
	if(noticeid!=0){
		var url="/sysnotice/saveNotice?noticeid="+noticeid;
		$.getJSON(url,function(data){
			
		});
	}
}