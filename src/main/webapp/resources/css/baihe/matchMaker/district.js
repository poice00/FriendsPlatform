	$.fn.selectDistrict = function(options) {
		$(this).click(function(event) {
			var _this = this;
			if ($(this).siblings('.provinceCityAll').length == 0) {				
				$(this).after('<div class="provinceCityAll">\
				<input type="hidden" name="countryVal"/>\
				<input type="hidden" name="provinceVal"/>\
				<input type="hidden" name="cityVal"/>\
			  <!--<div class="tabs clearfix">\
			    <ul class="">\
			      <li><a href="javascript:" class="current" tb="hotCityAll">国家</a></li>\
			      <li><a href="javascript:" tb="provinceAll" id="provinceAll">省份</a></li>\
			      <li><a href="javascript:" tb="cityAll" id="cityAll">城市</a></li>\
			      <li><a href="javascript:" tb="countyAll" id="countyAll">区县</a></li>\
			    </ul>\
			  </div>-->\
			  <div class="con">\
			    <div class="hotCityAll invis">\
			      <!--<div class="pre"><a></a></div>-->\
			      <div class="list">\
			        <ul>\
			          <!--                  <li><a href="javascript:"  class="current">南京</a></li> -->\
			        </ul>\
			      </div>\
			      <!--<div class="next"><a class="can"></a></div>-->\
			    </div>\
			    <div class="provinceAll invis">\
			      <!--<div class="pre"><a></a></div>-->\
			      <div class="list">\
			        <ul>\
			          <!--                  <li><a href="javascript:"  class="current">江西省</a></li> -->\
			        </ul>\
			      </div>\
			      <!--<div class="next"><a class="can"></a></div>-->\
			    </div>\
			    <div class="cityAll invis">\
			      <!--<div class="pre"><a></a></div>-->\
			      <div class="list">\
			        <ul>\
			          <!--                  <li><a href="javascript:"  class="current">南京</a></li> -->\
			        </ul>\
			      </div>\
			      <!--<div class="next"><a class="can"></a></div>-->\
			    </div>\
			    <!--<div class="cityAll invis">\
			      <div class="pre"><a></a></div>\
			      <div class="list">\
			        <ul>\
			        </ul>\
			      </div>\
			      <div class="next"><a class="can"></a></div>\
			    </div>-->\
			  </div>\
			</div>');
			};

			if ($('body').data('CityAll') == null) {
				sendAllCitiesAjax(_this);
			}
			$(this).select();
			$('.provinceCity').hide();
			$('.provinceCityAll').hide();
			$('#dimCityQuery').hide();
			var o2 = $(this).offset();
			var l2 = o2.left;
			var t2 = o2.top;
			var h2 = $(this).height();
			$(this).siblings('.provinceCityAll').toggle();
			$(this).siblings('.provinceCityAll').click(function(event) {
				event.stopPropagation();
			});
			event.stopPropagation();
			$("html").click(function() {
				$(".provinceCityAll").hide();
				$(".universityCity").hide();
			});
			$("input.proCitySelAll").removeClass("current2");
			$(this).addClass('current2');			
			$(this).siblings(".provinceCityAll").find(".con").children().hide();
			$(this).siblings(".provinceCityAll").find(".con").find(".provinceAll").show();			
		});
	};
	var allAreas = null;
	var allProId = null;
	var cityIdAll = null;
	var provinceAllTotalPage = null;
	var pa_pageSize = 12;
	var pa_currentPage = 1;	

	function sendAllCitiesAjax(ele) {
		
		viewAllProvince(ele,86);
	}	

	function viewAllHotCities() {
		$.each(allCountries,
			function(i, country) {
				var newArr = country.split('|');
				var countryId = newArr[1];
				var countryName = newArr[0];
				$(".hotCityAll .list ul").append("<li><a><input type='button' style='background:none;border:0px;cursor: pointer;' onclick=hotCityAddrInputAll(\'" + countryId + "\',\'" + countryName + "\') id='" + countryId + "' value='" + countryName + "'></a></li>");

			});
	}

	function countAllProvincePages() {
		provinceAllTotalPage = Math.ceil(allProvinces.length / pa_pageSize);
		return provinceAllTotalPage;
	}

	function viewAllProvince(ele,countryId) {
		if (countryId == "86") {
			$("body").data("cAllName", "中国");
			$("body").data("cAllId", countryId);
			var nameValue = $(ele);
			nameValue.removeClass("iGrays");
			nameValue.val('中国');
			nameValue.attr('data-val','86');
			$('.provinceAll').each(function() {
				if ($(this).not(":hidden")) {
						//$("input[name='countryVal']", this).val('86');
						nameValue.attr('data-val','86');
					};
			});
		}
		$(".provinceAll .list ul li").empty();
		$(".provinceAll .list ul li").remove();
		var hasProvince = false;
		for (var i = 0; i < allProvinces.length; i++) {
			var newArr = allProvinces[i].split('|');
			if (countryId == newArr[0]) {
				hasProvince = true;
				var p_id = newArr[2];
				var p_name = newArr[1];

				var li = $('<li><a style="background: none repeat scroll 0% 0% transparent; border: 0px none;" href="javascript:onclick=viewAllCities(\'' + p_id + '\', \'' + p_name + '\');" id="' + p_id + '">' + p_name + '</a></li>');
				$(".provinceAll .list ul").append(li);
			}
		}
		if (hasProvince) {
			$(".provinceCityAll").find(".con").children().hide();
			$(".provinceCityAll").find(".con").find(".provinceAll").show();

		} else {
			$(".provinceCityAll").hide();
		}
		hasProvince = null;
	}

	function viewAllCities(proId, proName) {
		$(".cityAll .list ul li").empty();
		$(".cityAll .list ul li").remove();
		$(".provinceCityAll").find(".tabs").find("a").removeClass("current");
		$(".provinceCityAll .tabs").find("#cityAll").addClass("current");
		$(".con .provinceAll .list a").removeClass("current");
		$(".con .provinceAll .list a[id='" + proId + "']").addClass("current");
		if (proId !== "-1") {
			$("body").data("pAllName", proName);
			$("body").data("pAllId", proId);
			var hasCity = false;
			$.each(allCities,
				function(i, city) {
					var newArr = city.split('|');
					if (newArr[1] == proId) {
						hasCity = true;
						var c_id = newArr[3];
						var cityName = newArr[2];
						var li = $('<li><a href="javascript:onclick=addrInputAll(\'' + c_id + '\',\'' + cityName + '\')" id="' + c_id + '">' + cityName + '</a></li>');
						$(".cityAll .list ul").append(li);
					}
				});

			if (proName !== "有限") {
				var countryName = $("body").data("cAllName");
				var nameValue = $("input.current2");
				nameValue.removeClass("iGrays");
				nameValue.val(countryName + "-" + proName);
				nameValue.attr('data-val',proId);
				if (hasCity) {
					$(".provinceCityAll").find(".con").children().hide();
					$(".provinceCityAll").find(".con").find(".cityAll").show();
				} else {
					$(".provinceCityAll").hide();
				}

			} else {
				$(".provinceCityAll").hide();
			}
			hasCity = null;
		} else {
			viewAllHotCities();
			$(".provinceCityAll").find(".con").find(".provinceAll").scrollTop(0);
			$(".provinceCityAll").find(".con").children().hide();
			$(".provinceCityAll").find(".con").find(".hotCityAll").show();					
		}
	}	

	function addrInputAll(cityId, cityName) {		
		var allProId = $("body").data("pAllId");
		var cityIdAll = $("body").data("cAllId");
		var p = null;

		if (cityId) {
			var nameValue = $("input.current2");
			nameValue.removeClass("iGrays");
			$(".provinceCityAll").find(".tabs").find("a").removeClass("current");
			$(".provinceCityAll .tabs").find("#provinceAll").addClass("current");
			$(".con .cityAll .list a").removeClass("current");
			$(".con .cityAll .list a[id='" + cityId + "']").addClass("current");
			$(".provinceCityAll").hide();
			var nameOfCountry = $("body").data("cAllName");
			var nameOfProvince = $("body").data("pAllName");
			nameValue.val(nameOfCountry + "-" + nameOfProvince + "-" + cityName);
			nameValue.attr('data-val',cityId);
		}

		$(".backifname").hide();
		// var nameValue = $("input.current2").attr("name");
		// if (nameValue == "consignor.addrProCity") {
		// 	$("#provinceId").val(allProId);
		// 	$("#cityId").val(cityIdAll);
		// }
		// if (nameValue == "order.caddrProCity") {
		// 	$("input[name='order.caddrProCity']").trigger("change");
		// }
		// if (nameValue == "consigneeInfo.addrProCity") {
		// 	$("input[name='consigneeInfo.addrProCity']").trigger("change");
		// }
		// if (nameValue == 'template.caddrProCity') {
		// 	$("input[name='template.caddrProCity']").trigger("change");
		// }
	}

	function hotCityAddrInputAll(countryId, countryName) {
		$("body").data("cAllId", countryId);
		$("body").data("cAllName", countryName);
		var nameValue = $("input.current2");
		nameValue.removeClass("iGrays");
		nameValue.val(countryName);
		$(".provinceCityAll").find(".tabs").find("a").removeClass("current");
		$(".provinceCityAll .tabs").find("#provinceAll").addClass("current");
		$(".con .hotCityAll .list a input").removeClass("current");
		$(".con .hotCityAll .list a input[id='" + countryId + "']").addClass("current");
		viewAllProvince('',countryId);
	}