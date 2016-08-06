$(document).ready(function(){
	var sh;
	sh = setInterval(function() {
		showTip(sh);
},3000);
	//window.clearInterval(sh);
});
function showTip(sh){
	$.ajax({
		url:'/FriendsPlatform/visterSend',
		type:'post',
		dataType:'json',
		async:false,	//默认为true，这里需要改为false
		success:function(result) {
			//alert(objWin);
			//alert(markerId);
			if(result){
				var url='/FriendsPlatform/makerChat/'+result.markerId; //转向网页的地址;
				var name; //网页名称，可为空;
				var iWidth='806px'; //弹出窗口的宽度;
				var iHeight='641px'; //弹出窗口的高度;
				//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
				var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
				var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
				var myWindow = window.open(url,"_blank",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
				window.clearInterval(sh);
					/*sh = setInterval(function() {
						showTip(sh);
					},3000);*/
					
			//	}
			}
		}
	});
}
function isOpen(id) { 
    //目标页面 
	var objWin;
    var target = "/FriendsPlatform/makerChat/"+id; 
    //判断是否打开 
    if (objWin == null || objWin.closed) { 
        objWin = window.open(target); 
    } else { 
        objWin.location.replace(target); 
    } 
    //objWin.focus(); 
} 
function openwindow(id)
{
	var url='/FriendsPlatform/makerChat/'+id; //转向网页的地址;
	var name; //网页名称，可为空;
	var iWidth='936px'; //弹出窗口的宽度;
	var iHeight='685px'; //弹出窗口的高度;
	//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
	var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
	window.open(url,"_blank",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}