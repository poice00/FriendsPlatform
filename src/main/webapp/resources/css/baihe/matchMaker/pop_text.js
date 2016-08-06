/*显示公告浮层*/

function getMessage(){

    var dh='';
	
	 dh+='<div class="popup">';
     dh+='		<a class="close" id="clo" href="#">关闭</a>';
	 dh+='		<h2><span>欢迎留言！</span></h2>';
	 dh+='		<p>您也可以拨打免费爱情专线：<span class="text">400-1520-555</span></p>';
	 dh+=' 		<div class="leaveword">';
	 dh+='			<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	 dh+='			<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	 dh+='		</div>';
	 dh+='		<div class="formList">';
	 dh+='			<dl>';
	 dh+='				<dt>您的称呼</dt>';
	 dh+='				<dd><input id="username1" name="" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='  		<dl>';
	 dh+='				<dt>性别</dt>';
	 dh+='				<dd>';
	 dh+='					<a href="#" class="sex" id="gender_0" onclick="Csex(1)">男</a>';
	 dh+='					<a href="#" class="sex check" id="gender_1" onclick="Csex(0)">女</a>';
	 dh+='					<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	 dh+=' 				</dd>';
	 dh+='  		</dl><br />';
	 dh+='			<dl style="clear:left;">';
	 dh+='				<dt>出生日期</dt>';
	 dh+='				<dd id="birthDay" style="z-index:6;">';
	 dh+='					<div class="datepicker"></div>';
	 dh+='				</dd>';
	 dh+='			</dl><br />';
	 dh+='			<dl style="position:relative; clear:left;">';
	 dh+='				<dt>工作地点</dt>';
	 dh+='				<dd id="districtInfo" style="z-index:5;">';
	 dh+='					<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	 dh+='				</dd>';
	 dh+='			</dl>';
	 dh+='			<dl>';
	 dh+='				<dt>联系方式</dt>';
	 dh+='				<dd><input name="contact" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='			<a href="javascript:;" class="btn" id="aaa" >确认提交</a>';
	 dh+='		</div>';
	 dh+='</div>';
	
    $.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    }); 
	$('#clo').click($.unblockUI);
	var a=$(".datepicker");
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){
        if ($(this).text() == "1950") {
            $(this).removeClass("selected");
        }
    });
    $("span").each(function(){
        if ($(this).text() == "1950") {
            $(this).text("1990");
        }
    });
    $("#district").attr("data-val", "861108");
    $("#district").val("中国-北京市-海淀区");
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#aaa").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'ly'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}

function getMatchmaker(){
	var dh='';
	
	dh+='<div class="popup">';
	dh+='	<a class="close" id="clo1" href="#">关闭</a>';
	dh+='	<h2>咨询一下<span>TA的红娘</span>吧，看看你们是否是最合适的一对！</h2>';
	dh+='	<p>您也可以拨打免费爱情专线：<span>400-017-5155</span></p>';
	dh+='	<div class="leaveword">';
	dh+='		<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	dh+='		<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	dh+='	</div>';
	dh+='	<div class="formList">';
	dh+='		<dl>';
	dh+='			<dt>您的称呼</dt>';
	dh+='			<dd><input id="username1" name="" type="text" /></dd>';
	dh+='		</dl>';
	dh+='		<dl>';
	dh+='			<dt>性别</dt>';
	dh+='			<dd>';
	dh+='				<a href="#" class="sex" id="gender_1" onclick="Csex(0)">男</a>';
	dh+='				<a href="#" class="sex check" id="gender_0" onclick="Csex(1)">女</a>';
	dh+='				<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	dh+='			</dd>';
	dh+='		</dl><br />';
	dh+='		<dl style="clear:left;">';
	dh+='			<dt>出生日期</dt>';
	dh+='			<dd id="birthDay" style="z-index:6;">';
	dh+='				<div class="datepicker"></div>';
	dh+='			</dd>';
	dh+='		</dl><br />';
	dh+='		<dl style="position:relative; clear:left;">';
	dh+='			<dt>工作地点</dt>';
	dh+='			<dd id="districtInfo" style="z-index:5;">';
	dh+='				<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	dh+='			</dd>';
	dh+='		</dl>';
	dh+='		<dl>';
	dh+='			<dt>联系方式</dt>';
	dh+='			<dd><input name="contact" type="text" /></dd>';
	dh+='		</dl>';
	dh+='		<a href="javascript:;" class="btn" id="bbb" >确认提交</a>';
	dh+='	</div>';
	dh+='</div>';
	
	
	$.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    }); 
	$('#clo1').click($.unblockUI);
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){

        if ($(this).text() == "1950") {

            $(this).removeClass("selected");

        }

    });

    $("span").each(function(){

        if ($(this).text() == "1950") {

            $(this).text("1990");

        }

    });

    $("#district").attr("data-val", "861108");

    $("#district").val("中国-北京市-海淀区");
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#bbb").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'ly'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}

function getKnow(){
	var dh='';
	dh+='<div class="popup">';
    dh+='<a class="close" id="clo2" href="#">关闭</a>';
	dh+='<h2>对TA<span>有感觉？</span> 那就快快发消息认识一下吧！</h2>';
	dh+='<p>您也可以直接咨询TA的婚恋顾问，请拨打免费爱情专线：<span>400-1520-555</span></p>';
	dh+='<div class="leaveword">';
	dh+='	<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	dh+='	<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	dh+='</div>';
	dh+='<div class="formList">';
	dh+='	<dl>';
	dh+='		<dt>您的称呼</dt>';
	dh+='		<dd><input id="username1" name="" type="text" /></dd>';
	dh+='	</dl>';
	dh+='	<dl>';
	dh+='		<dt>性别</dt>';
	dh+='		<dd>';
	dh+='			<a href="#" class="sex" id="gender_1" onclick="Csex(0)">男</a>';
	dh+='			<a href="#" class="sex check" id="gender_0" onclick="Csex(1)">女</a>';
	dh+='			<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	dh+='		</dd>';
	dh+='	</dl><br />';
	dh+='	<dl style="clear:left;">';
	dh+='		<dt>出生日期</dt>';
	dh+='		<dd id="birthDay" style="z-index:6;">';
	dh+='			<div class="datepicker"></div>';
	dh+='		</dd>';
	dh+='	</dl><br />';
	dh+='	<dl style="position:relative; clear:left;">';
	dh+='		<dt>工作地点</dt>';
	dh+='		<dd id="districtInfo" style="z-index:5;">';
	dh+='			<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	dh+='		</dd>';
	dh+='	</dl>';
	dh+='	<dl>';
	dh+='		<dt>联系方式</dt>';
	dh+='		<dd><input name="contact" type="text" /></dd>';
	dh+='	</dl>';
	dh+='	<a href="javascript:;" class="btn" id="ccc" >确认提交</a>';
	dh+='</div>';
	dh+='</div>';
	$.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    });
	$('#clo2').click($.unblockUI);
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){
        if ($(this).text() == "1950") {
            $(this).removeClass("selected");
        }
    });
    $("span").each(function(){
        if ($(this).text() == "1950") {
            $(this).text("1990");
        }
    });
    $("#district").attr("data-val", "861108");
    $("#district").val("中国-北京市-海淀区");
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#ccc").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'ly'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}

function getInformation(){

    var dh='';
	
	 dh+='<div class="popup">';
     dh+='		<a class="close" id="clo3" href="#">关闭</a>';
	 dh+='		<h2><span>请填写以下信息！</span></h2>';
	 dh+='		<p>您也可以拨打免费爱情专线：<span class="text">400-1520-555</span></p>';
	 dh+=' 		<div class="leaveword">';
	 dh+='			<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	 dh+='			<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	 dh+='		</div>';
	 dh+='		<div class="formList">';
	 dh+='			<dl>';
	 dh+='				<dt>您的称呼</dt>';
	 dh+='				<dd><input id="username1" name="" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='  		<dl>';
	 dh+='				<dt>性别</dt>';
	 dh+='				<dd>';
	 dh+='					<a href="#" class="sex" id="gender_1" onclick="Csex(0)">男</a>';
	 dh+='					<a href="#" class="sex check" id="gender_0" onclick="Csex(1)">女</a>';
	 dh+='					<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	 dh+=' 				</dd>';
	 dh+='  		</dl><br />';
	 dh+='			<dl style="clear:left;">';
	 dh+='				<dt>出生日期</dt>';
	 dh+='				<dd id="birthDay" style="z-index:6;">';
	 dh+='					<div class="datepicker"></div>';
	 dh+='				</dd>';
	 dh+='			</dl><br />';
	 dh+='			<dl style="position:relative; clear:left;">';
	 dh+='				<dt>工作地点</dt>';
	 dh+='				<dd id="districtInfo" style="z-index:5;">';
	 dh+='					<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	 dh+='				</dd>';
	 dh+='			</dl>';
	 dh+='			<dl>';
	 dh+='				<dt>联系方式</dt>';
	 dh+='				<dd><input name="contact" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='			<a href="javascript:;" id="eee" class="btn" >确认提交</a>';
	 dh+='		</div>';
	 dh+='</div>';
	
    $.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    }); 
	$('#clo3').click($.unblockUI);
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){
        if ($(this).text() == "1950") {
            $(this).removeClass("selected");
        }
    });
    $("span").each(function(){
        if ($(this).text() == "1950") {
            $(this).text("1990");
        }
    });
    $("#district").attr("data-val", "861108");
    $("#district").val("中国-北京市-海淀区");
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#eee").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'zdhy'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}
function getActivities(){

    var dh='';
	
	 dh+='<div class="popup">';
     dh+='		<a class="close" id="clo4" href="#">关闭</a>';
	 dh+='		<h2><span>欢迎合作</span></h2>';
	 dh+='		<p>您也可以拨打活动招商热线：<span class="text">010-58208188 转 8176（8235）</span></p>';
	 dh+=' 		<div class="leaveword">';
	 dh+='			<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	 dh+='			<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	 dh+='		</div>';
	 dh+='		<div class="formList">';
	 dh+='			<dl>';
	 dh+='				<dt>您的称呼</dt>';
	 dh+='				<dd><input id="username1" name="" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='  		<dl>';
	 dh+='				<dt>性别</dt>';
	 dh+='				<dd>';
	 dh+='					<a href="#" class="sex" id="gender_1" onclick="Csex(0)">男</a>';
	 dh+='					<a href="#" class="sex check" id="gender_0" onclick="Csex(1)">女</a>';
	 dh+='					<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	 dh+=' 				</dd>';
	 dh+='  		</dl><br />';
	 dh+='			<dl style="position:relative; clear:left;">';
	 dh+='				<dt>活动地点</dt>';
	 dh+='				<dd id="districtInfo" style="z-index:5;">';
	 dh+='					<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	 dh+='				</dd>';
	 dh+='			</dl>';
	 dh+='			<dl>';
	 dh+='				<dt>联系方式</dt>';
	 dh+='				<dd><input name="contact" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='			<a href="javascript:;" class="btn" id="ddd" >确认提交</a>';
	 dh+='		</div>';
	 dh+='</div>';
	
    $.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    });
	$('#clo4').click($.unblockUI);
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){
        if ($(this).text() == "1950") {
            $(this).removeClass("selected");
        }
    });
    $("span").each(function(){
        if ($(this).text() == "1950") {
            $(this).text("1990");
        }
    });
    $("#district").attr("data-val", "861108");
    $("#district").val("中国-北京市-海淀区"); 
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#ddd").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'hzjm'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}

function foundXzwdt(){

    var dh='';
	
	 dh+='<div class="popup">';
     dh+='		<a class="close" id="clo5" href="#">关闭</a>';
	 dh+='		<h2><span>请填写以下信息！</span></h2>';
	 dh+='		<p>您也可以拨打免费爱情专线：<span class="text">400-1520-555</span></p>';
	 dh+=' 		<div class="leaveword">';
	 dh+='			<textarea id="message" onkeyup="count1()" onpaste="count1()">请留言...</textarea>';
	 dh+='			<p>限<span>300</span>字，目前已输入<span id="span1">0</span>字，您还可以输入<span id="span2">300</span>字</p>';
	 dh+='		</div>';
	 dh+='		<div class="formList">';
	 dh+='			<dl>';
	 dh+='				<dt>您的称呼</dt>';
	 dh+='				<dd><input id="username1" name="" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='  		<dl>';
	 dh+='				<dt>性别</dt>';
	 dh+='				<dd>';
	 dh+='					<a href="#" class="sex" id="gender_1" onclick="Csex(0)">男</a>';
	 dh+='					<a href="#" class="sex check" id="gender_0" onclick="Csex(1)">女</a>';
	 dh+='					<input type="hidden" name="goldUser_gender" id="gender" value="1" />';
	 dh+=' 				</dd>';
	 dh+='  		</dl><br />';
	 dh+='			<dl style="clear:left;">';
	 dh+='				<dt>出生日期</dt>';
	 dh+='				<dd id="birthDay" style="z-index:6;">';
	 dh+='					<div class="datepicker"></div>';
	 dh+='				</dd>';
	 dh+='			</dl><br />';
	 dh+='			<dl style="position:relative; clear:left;">';
	 dh+='				<dt>工作地点</dt>';
	 dh+='				<dd id="districtInfo" style="z-index:5;">';
	 dh+='					<div class="selCity"><input name="" id="district" autocomplete="off" type="text" value="请选择所在地区" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></div>';
	 dh+='				</dd>';
	 dh+='			</dl>';
	 dh+='			<dl>';
	 dh+='				<dt>联系方式</dt>';
	 dh+='				<dd><input name="contact" type="text" /></dd>';
	 dh+='			</dl>';
	 dh+='			<a href="javascript:;" id="fff" class="btn" >确认提交</a>';
	 dh+='		</div>';
	 dh+='</div>';
	
    $.blockUI({
        message:dh
    });

    $("[id^=close_box_]").click(function(){
        $.unblockUI();
    }); 
	$('#clo5').click($.unblockUI);
	$(".datepicker").jsTransDate();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 
	$("a[index='40']").addClass("selected");
    $("a[index='0']").each(function(){
        if ($(this).text() == "1950") {
            $(this).removeClass("selected");
        }
    });
    $("span").each(function(){
        if ($(this).text() == "1950") {
            $(this).text("1990");
        }
    });
    $("#district").attr("data-val", "861108");
    $("#district").val("中国-北京市-海淀区");
	$("#message").blur(function(){
		if ($("#message").val() == '') {
            $("#message").val("请留言...");
        }
	});
	$("#message").focus(function(){
		if($("#message").val()=="请留言..."){
			$("#message").val("");
		}
	});
	$("#fff").bind("click",function(){
		    if (validate1()) {
        var message = $("#message").val();
        var username = $("#username1").val();
        var gender = $("#gender").val();
        
        var city = $("#district").attr("data-val");
        
        var telphoto = $("input[name='contact']").val();
        var QQ = "";
        var email = "";
        var marry = "";
        var time = "";
        $("a.selected").each(function(){
            time = time + "-" + $(this).text();
        });
        time = time.substring(1, time.length)
        var timeStr = time.split("-");
        var year = timeStr[0];
        var month = timeStr[1];
        var marry = "";
        var income = "";
        var Channel=getCookie("Channel")==null?"":getCookie("Channel");
		var code1=getCookie("Code")==null?"":getCookie("Code");
		var policy=getCookie("policy")==null?"":getCookie("policy");
        
        $.ajax({
            url: '/ajax.php',
            type: 'get',
            data: ({
                "username": username,
                "gender": gender,
                "year": year,
                "month": month,
                "city": city,
                "marry": marry,
                "message": message,
                "income": income,
                "telphoto": telphoto,
                "Channel": Channel,
                "Code": code1,
                "policy": policy,
                'str2': 'baihevip',
                'str1': 'zdgs'
            }),
            dataType: "text",
            success: function(data){
                if (eval(data) == "ok") {
                    //Pager.Boxer.SeccBox.ShowMsg();
                    getSuccess();
                }
                else {
                    alert("申请失败，请稍后再试，或直接拨打我们的幸福热线4008191520");
                }
            }
        });
        
    }
	});
}

function getSuccess(){
	var dh='';
	dh+='<div class="popup_submit">';
    dh+='	<a class="close" id="clo7" href="#">关闭</a>';
	dh+='	<h2>提交成功！</h2>';
	dh+='	<p>爱情顾问会联系你</p>';
	dh+='	<p>您也可以拨打：<span>400-1520-555</span> 进行咨询</p>';
	dh+='</div>';
	$.blockUI({
        message:dh
    });
    $('#clo7').click($.unblockUI);
}


//  直客查询

function getquery(){
	var contractId = $("#contractId").val();
	var username = $("#username").val();
	
	$.ajax({
            url: 'contract_center.php',
            type: 'POST',
            data: ({
                "contractId": contractId,
                "username": username,
            }),
            dataType: "json",
            success: function(data){
                if (data == "no") {
                    getQueryFail();
                }else if(data == "noname"){
					alert('证件号码不能为空');
				}else {  
					var dh='';
					dh+='<div class="popup popup_query">';
					dh+='	<a class="close" id="clo9" href="#">关闭</a>';
					dh+='	<h2>欢迎您查询百合网VIP服务合同</h2>';
					dh+='	<h3>';
					dh+='		<table>';
					dh+='			<tr>';
					dh+='				<td>合同编号：<span>'+data.contractId+'</span></td>';
					dh+='				<td>VIP会员姓名：<span>'+data.cusName+'</span></td>';
					dh+='			</tr>';
					dh+='		</table>';
					dh+='	</h3>';
					dh+='	<div class="formList">';
					dh+='	<table>';
					dh+='		<tr>';
					dh+='			<td>订单编号：<span>'+data.orderId+'</span></td>';
					dh+='			<td>购买服务：<span>'+data.cusType+'</span></td>';
					dh+='		</tr>';
					dh+='		<tr>';
					dh+='			<td>缴费金额：<span>'+data.actualAmount+'</span></td>';
					dh+='			<td>服务期限：<span>'+data.serviceTime+'</span></td>';
					dh+='		</tr>';
					dh+='		<tr>';
					dh+='			<td>所属服务中心：<span>'+data.groupName+'</span></td>';
					dh+='			<td></td>';
					dh+='		</tr>';
					dh+='	</table>';
					dh+='</div>';
					dh+='<p><span>温馨提示：</span>如您所签署的合同金额及服务内容与查询结果不同，您可以致电<span>400-8191-520</span></p>';
					dh+='<a href="#" class="btn">继续查询</a>';
					

					getQuery(dh);
                }
            }
        });
}





function getQueryFail(){
	var dh='';
	dh+='<div class="popup popup_queryfail">';
	dh+='	<div>';
    dh+='		<a class="close" id="clo8" href="#">关闭</a>';
	dh+='		<h2>抱歉！<span>没有找到相关合同内容，请确认输入的合同编号和会员姓名。</span></h2>';
	dh+='		<a href="#" class="btn">返回重新查询</a>';
	dh+='	</div>';
	dh+='	<p><span>温馨提示：</span>请您在购买VIP服务一周后点击查询，此工具 查询只适用于2013年6月1日以后加入VIP服务的用户。</p>';
	dh+='</div>';
	$.blockUI({
        message:dh
    });
    $('#clo8').click($.unblockUI);
}

function getQuery(dh){
	$.blockUI({
        message:dh
    });
    $('#clo9').click($.unblockUI);
}

