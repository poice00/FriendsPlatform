
//首页焦点图
function FocusSy(){
	var curr = 0;
	$("#rollSy .tab").each(function(i){
		$(this).click(function(){
			curr = i;
			$("#rollSycont a").eq(i).fadeIn("slow").siblings("a").hide();
			$(this).siblings("").removeClass("active").end().addClass("active");
			return false;
		});
	});

	var pg = function(flag){
		//flag:true表示前翻， false表示后翻
		if (flag) {
			if (curr == 0) {
				todo = 3;
			} else {
				todo = (curr - 1) % 2;
			}
		} else {
			todo = (curr + 1) % 2;
		}
		$("#rollSy .tab").eq(todo).click();
	};

	//自动翻
	var timer = setInterval(function(){
		todo = (curr + 1) % 3;
		$("#rollSy .tab").eq(todo).click();
	},7000);

	//鼠标悬停在触发器上时停止自动翻
	$("#rollSycont a").hover(function(){
		clearInterval(timer);
	},
	function(){
		timer = setInterval(function(){
			todo = (curr + 1) % 2;
			$("#rollSy .tab").eq(todo).click();
		},7000);
	}
	);

};
//]]>

//成功案例
function FocusCgal(){
	var curr = 0;
	$("#rollCgal .tab").each(function(i){
		$(this).click(function(){
			curr = i;
			$("#rollCgalcont a").eq(i).fadeIn("slow").siblings("a").hide();
			$(this).siblings("").removeClass("active").end().addClass("active");
			return false;
		});
	});

	var pg = function(flag){
		//flag:true表示前翻， false表示后翻
		if (flag) {
			if (curr == 0) {
				todo = 7;
			} else {
				todo = (curr - 1) % 6;
			}
		} else {
			todo = (curr + 1) % 6;
		}
		$("#rollCgal .tab").eq(todo).click();
	};

	//自动翻
	var timer = setInterval(function(){
		todo = (curr + 1) % 7;
		$("#rollCcgal .tab").eq(todo).click();
	},5000);

	//鼠标悬停在触发器上时停止自动翻
	$("#rollCgal a").hover(function(){
		clearInterval(timer);
	},
	function(){
		timer = setInterval(function(){
			todo = (curr + 1) % 6;
			$("#rollCgal .tab").eq(todo).click();
		},5000);
	}
	);

};
//]]>

//----首页图片列表
function picList(){ 
	$(".vipSyList dl dd").hover(function(){
		$(".intr",this).stop().css({display:"block"});
	},function(){
		$(".intr",this).stop().css({display:"none"});
	});
};
//----会员页列表
function picList_member(){ 
	$(".member_tabLabel ul li").hover(function(){
		$(".intr",this).stop().css({display:"block"});
	},function(){
		$(".intr",this).stop().css({display:"none"});
	});
};

//------实体店列表页
function shopList(){ 
    $(".serList ul li").hover(function(){
      $(".intr",this).stop().css({display:"block"});
    },function(){
      $(".intr",this).stop().css({display:"none"});
    });
  };
  
function selectShopList(){ 
    $("#shoplist ul li").hover(function(){
      $(".shoplist-intr",this).stop().css({display:"block"});
    },function(){
      $(".shoplist-intr",this).stop().css({display:"none"});
    });
  };

//----首页成功案例
function storyList(){ 
	$(".vipSyStory .cont .picBox > div").hover(function(){
		$("p",this).stop().css({display:"block"});
		$("img",this).css("opacity","1");
	},function(){
		$("p",this).stop().css({display:"none"});
		$("img",this).css("opacity","0.6");
	});
};

//----成功案例页焦点图
function caseList(){ 
	$(".photoList dd a img").hover(function(){
		$(this).css("opacity","1");
		$(".photoList dt img").attr("src",$(this).attr("bigImg"));
	},function(){
		$(this).css("opacity","0.5");
	});
};

//实体店焦点图
$(function(){
	(function(){
	var curr = 0;
	$("#roll .tab").each(function(i){
		$(this).click(function(){
			curr = i;
			$("#rollcont a").eq(i).fadeIn("slow").siblings("a").hide();
			$(this).siblings(".tab").removeClass("active").end().addClass("active");
			return false;
		});
	});

	var pg = function(flag){
		//flag:true表示前翻， false表示后翻
		if (flag) {
			if (curr == 0) {
				todo = 4;
			} else {
				todo = (curr - 1) % 5;
			}
		} else {
			todo = (curr + 1) % 5;
		}
		$("#roll .tab").eq(todo).click();
	};

	//自动翻
	var timer = setInterval(function(){
		todo = (curr + 1) % 4;
		$("#roll .tab").eq(todo).click();
	},5000);

	//鼠标悬停在触发器上时停止自动翻
	$("#roll a").hover(function(){
		clearInterval(timer);
	},
	function(){
		timer = setInterval(function(){
			todo = (curr + 1) % 4;
			$("#roll .tab").eq(todo).click();
		},5000);
	}
	);
	
	
	
	/* 百合红娘页滚动效果 */
	$(".counselor").slide({
		titCell: "",
		mainCell: ".counselorBox ul",
		autoPage: true,
		effect: "leftLoop",
		autoPlay: false,
		pnLoop: true,
		vis: 1,
		prevCell:'.L',
		nextCell:'.R'
	});

	
	$(".datepicker").jsTransDate();
	$('input:checkbox').jqTransCheckBox();
	$("#district").selectDistrict();
	if( $('select').jqTransSelect().length > 0 ){jqTransformAddDocumentListener();} 

})();
	
});
//]]>


//首页tab切换
function wrapTag(numID,num)
{
	var num=num
	for(var id = 1;id<=num;id++)
	{
		var Tag="cont"+id;
		var Css = "on" + id;
		if(id==numID)
		{
			document.getElementById(Tag).style.display="block";
			document.getElementById(Css).className = "active";
		}
		else
		{
			document.getElementById(Tag).style.display="none";
			document.getElementById(Css).className = "";
		}
	}
}


//横向滚动
/*$(function(){
	$("#sale_box").jCarouselLite({
		auto: 2000,//图片停留时间
		scroll: 1,//每次滚动覆盖的图片个数
		speed: 300, //设置速度，0是不动。其次就是数字越大 ，移动越慢。
		vertical: false,//横向（true），竖向（false）
		visible: 7, //显示的数量
		circular: true //是否循环
	});
	$("#sale_box").css("width",1000);
});
*/
/*
通用三级联动说明
参数配置如下，配置select的三个ID和默认值就行，无默认值填写为null
var defaults = {
s1:'Select1',
s2:'Select2',
s3:'Select3',
v1:null,
v2:null,
v3:null
};
*/
var threeSelectData={
"京津地区":{val:"jingjin",items:{"北京":{val:"beijing",items:{"北京":"beijing"}},"天津":{val:"tianjin",items:{"天津":"tianjin"}}}},
"华东":{val:"huadong",
items:{"上海":{val:"shanghai",items:{"上海":"shanghai"}},
"安徽省":{val:"anhui",items:{"合肥市":"hefei","芜湖市":"wuhu","六安市":"luan","淮南市":"huainan"}},
"江苏省":{val:"jiangsu",items:{"南京市":"nanjing","苏州市":"suzhou","无锡市":"wuyi","南通市":"nantong","常州市":"changzhou","徐州市":"xuzhou","扬州市":"yangzhou","淮安市":"huaian"}},
"浙江省":{val:"zhejiang",items:{"杭州市":"hangzhou","宁波市":"ningbo","金华市":"jinhua","绍兴市":"shaoxing","湖州市":"huzhou"}}}},
"华南":{val:"huanan",
items:{"广东省":{val:"guangdong",items:{"广州市":"guangzhou","深圳市":"shenzhen","东莞市":"dongguan","佛山市":"foshan","珠海市":"zhuhai","惠州市":"huizhou","江门市":"jiangmen","汕头市":"shantou","中山市":"zhongshan"}},
"福建省":{val:"fujian",items:{"厦门市":"xiamen","福州市":"fuzhou"}},
"广西壮族自治区":{val:"guangxizhuangzuzizhiqu",items:{"南宁市":"nanning","柳州市":"liuzhou"}}}},
"华北":{val:"huabei",
items:{"河南省":{val:"henan",items:{"郑州市":"zhengzhou","新乡市":"xinxiang"}},
"河北省":{val:"hebei",items:{"石家庄市":"shijiazhuang","唐山市":"tangshan","秦皇岛":"qinhuangdao","承德市":"chengde"}},
"山东省":{val:"shandong",items:{"青岛市":"qingdao","济南市":"jinan","泰安市":"taian","淄博市":"zibo","德州市":"dezhou","潍坊市":"weifang","东营市":"dongying"}},
"陕西省":{val:"shanxi",items:{"西安市":"xian"}},
"甘肃省":{val:"gansu",items:{"兰州市":"lanzhou"}},
"山西省":{val:"shanxia",items:{"晋中市":"jinzhong","太原市":"taiyuan"}},
"内蒙古自治区":{val:"neimenggu",items:{"呼和浩特市":"huhehaote"}}}},
"华中":{val:"huazhong",
items:{"湖南省":{val:"hunan",items:{"长沙市":"changsha","岳阳市":"yueyang"}},
"江西省":{val:"jiangxi",items:{"南昌市":"nanchang"}},
"湖北省":{val:"hubei",items:{"武汉市":"wuhan","襄阳市":"xiangyang"}}}},
"西南":{val:"xinan",
items:{"四川省":{val:"sichuan",items:{"成都市":"chengdu","乐山市":"leshan","绵阳市":"mianyang","宜宾市":"yibin","自贡市":"zigong"}},
"重庆市":{val:"chongqing",items:{"重庆市":"chongqing"}},
"云南省":{val:"yunnan",items:{"昆明市":"kunming","玉溪市":"yuxi"}},
"新疆维吾尔自治区":{val:"xinjiangweiwuerzizhiqu",items:{"乌鲁木齐市":"wulumuqi"}},
"贵州省":{val:"guizhou",items:{"贵阳市":"guiyang"}}}},
"东北":{val:"dongbei",
items:{"黑龙江省":{val:"heilongjiang",items:{"哈尔滨市":"haerbin","大庆市":"daqing"}},
"辽宁省":{val:"liaoning",items:{"沈阳市":"shenyang","大连市":"dalian","鞍山市":"anshan"}},
"吉林省":{val:"jilin",items:{"长春市":"changchun","吉林市":"jilin"}}}}  //"鞍山市":"anshan",
};
var showid="beijing";
function threeSelect(config){
	var $s1=$("#"+config.s1);
	var $s2=$("#"+config.s2);
	var $s3=$("#"+config.s3);
	var v1=config.v1?config.v1:null;
	var v2=config.v2?config.v2:null;
	var v3=config.v3?config.v3:null;
	$.each(threeSelectData,function(k,v){
		appendOptionTo($s1,k,v.val,v1);
	});
	$s1.change(function(){

		$s2.html("");
		$s3.html("");
		if(this.selectedIndex==-1) return;
		var s1_curr_val = this.options[this.selectedIndex].value;
		$.each(threeSelectData,function(k,v){
			if(s1_curr_val==v.val){
				if(v.items){
					$.each(v.items,function(k,v){
						appendOptionTo($s2,k,v.val,v2);
					});
				}
			}
		});
		if($s2[0].options.length==0){appendOptionTo($s2,"...","",v2);}
		$s2.change();
	}).change();
	$s2.change(function(){
		$s3.html("");
		var s1_curr_val = $s1[0].options[$s1[0].selectedIndex].value;
		if(this.selectedIndex==-1) return;
		var s2_curr_val = this.options[this.selectedIndex].value;
		$.each(threeSelectData,function(k,v){
			if(s1_curr_val==v.val){
				if(v.items){
					$.each(v.items,function(k,v){
						if(s2_curr_val==v.val){
							$.each(v.items,function(k,v){
								appendOptionTo($s3,k,v,v3);
							});
						}
					});
					if($s3[0].options.length==0){appendOptionTo($s3,"...","",v3);}
				}
			}
		});
		showid=$("#Select3").val();
		$(".aa").css("display","none");
		$("#"+showid).css("display","block");
	}).change();

	function appendOptionTo($o,k,v,d){
		var $opt=$("<option>").text(k).val(v);
		if(v==d){
			$opt.attr("selected", "selected");
		}
		$opt.appendTo($o);
	}

}


//-----地图
function map(id,num,layerID){
		
	var obj = $("#"+id);
	var aCoords = $(layerID).attr('coords').split(',');
	var mapTop = $('.mapCont').offset().top+parseInt(aCoords[1]);
	var mapLeft =$('.mapCont').offset().left+parseInt(aCoords[0])+80;
	
	if(num==1)
	{
		obj.css({"display":"block","left":mapLeft,"top":mapTop});
	}else if(num==2){
		obj.css("display","none");
	}
}