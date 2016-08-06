<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0032)http://xq.ibaihe.com/index/basic -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<title>散点图</title>

<script type="text/javascript" src="/FriendsPlatform/resources/highCharts/jquery-2.1.4.min.js"></script>

<script type="text/javascript" src="/FriendsPlatform/resources/highCharts/highcharts.js"></script>
<script type="text/javascript" src="/FriendsPlatform/resources/highCharts/exporting.js"> </script>
<script type="text/javascript" src="/FriendsPlatform/resources/highCharts/highcharts-3d.js"></script>	




<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="/FriendsPlatform/resources/baihe_files/jquery.Jcrop.css">
<link href="/FriendsPlatform/resources/baihe_files/lovecss.css" rel="stylesheet" type="text/css">
<link href="/FriendsPlatform/resources/baihe_files/public.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>

		<script type="text/javascript" src="/FriendsPlatform/resources/highCharts/jquery-2.1.4.min.js"></script>
		<style type="text/css">
#container {
	height: 800px; 
	width: 800px;
	margin: 0 auto;
	margin-top: 200px;
}
</style>
<script type="text/javascript">
$(function () {

    // Give the points a 3D feel by adding a radial gradient
    Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.4,
                cy: 0.3,
                r: 0.5
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.2).get('rgb')]
            ]
        };
    });

    // Set up the chart
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            margin: 100,
            type: 'scatter',
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 30,
                depth: 250,
                viewDistance: 5,

                frame: {
                    bottom: { size: 1, color: 'rgba(0,0,0,0.02)' },
                    back: { size: 1, color: 'rgba(0,0,0,0.04)' },
                    side: { size: 1, color: 'rgba(0,0,0,0.06)' }
                }
            }
        },
        title: {
            text: 'SVD特征抽取'
        },
        subtitle: {
            text: '将高维数据映射到3维空间'
        },
        plotOptions: {
            scatter: {
                width: 10,
                height: 10,
                depth: 10
            }
        },
        yAxis: {
            min: -0.5,
            max: 0.5,
            title: null
        },
        xAxis: {
            min: -0.5,
            max: 0.5,
            gridLineWidth: 0.0001
        },
        zAxis: {
            min: -0.5,
            max: 0.5,
            showFirstLabel: false
        },
        legend: {
            enabled: false
        },
        series: [
        	<c:forEach var="cluster" items="${clusters}">
        	{
        		name:'${cluster.name}',
        		data:[[${cluster.x},${cluster.y},${cluster.z}]]
        		}, 
        	</c:forEach>
        	]
    });

    // Add mouse events for rotation
    $(chart.container).bind('mousedown.hc touchstart.hc', function (e) {
        e = chart.pointer.normalize(e);

        var posX = e.pageX,
            posY = e.pageY,
            alpha = chart.options.chart.options3d.alpha,
            beta = chart.options.chart.options3d.beta,
            newAlpha,
            newBeta,
            sensitivity = 5; // lower is more sensitive

        $(document).bind({
            'mousemove.hc touchdrag.hc': function (e) {
                // Run beta
                newBeta = beta + (posX - e.pageX) / sensitivity;
                chart.options.chart.options3d.beta = newBeta;

                // Run alpha
                newAlpha = alpha + (e.pageY - posY) / sensitivity;
                chart.options.chart.options3d.alpha = newAlpha;

                chart.redraw(false);
            },
            'mouseup touchend': function () {
                $(document).unbind('.hc');
            }
        });
    });

});
		</script>
	</head>
	<body class="noon" style="background:url(<%=request.getContextPath()%>/resources/baihe_files/bj_body.jpg);">

	<!-- 左侧导航栏 -->
		<jsp:include page="/WEB-INF/jsps/common/left_nav.jsp"></jsp:include>
<div id="container" style="height: 800px;text-align:center;">
</div>
	</body>
</html>



