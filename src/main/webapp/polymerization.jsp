<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<link rel="stylesheet" href="${ctx}/static/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/static/css/admin.css">
<script src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/pintuer.js"></script>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>监控</title>
<link rel="stylesheet" href="${ctx}/static/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/static/css/admin.css">
<script src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/pintuer.js"></script>


<!--界面显示框架-->
<link rel="stylesheet" href="${ctx}/baidu_offline/assets/css/layui.css">
<!--实例样式-->
<link rel="stylesheet" href="${ctx}/baidu_offline/assets/css/map.css">
<link rel="stylesheet"
	href="${ctx}/baidu_offline/assets/font/mine-font/iconfont.css">
<!--百度基础地图-->
<script src="${ctx}/baidu/map_load.js"></script>
<script src="${ctx}/baidu_offline/assets/jquery-2.1.4.min.js"></script>

<!--框架即功能javascript-->
<script src="${ctx}/baidu_offline/assets/layui.js"></script>
<script src="${ctx}/baidu_offline/assets/common.js "></script>
<script src="${ctx}/baidu_offline/assets/tinycolor.js "></script>

<script src="${ctx}/static/js/Heatmap_min.js"></script>

<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<style type="text/css">
		ul,li{list-style: none;margin:0;padding:0;float:left;}
		html{height:100%}
		body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
		#container{height:500px;width:100%;}
		#r-result{width:100%;}
    </style>	
    
</head>
<body>
	<div id="map_container" class="map-container" ></div>
	


	<script type="text/javascript">

	var map = new BMap.Map("map_container");
	var point = new BMap.Point(116.418261, 39.921984);
	map.centerAndZoom(point, 10); // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	
	var tilelayer = new BMap.TileLayer(); // 创建地图层实例    
    tilelayer.getTilesUrl = function(tileCoord, zoom) { // 设置图块路径     
        return 'baidu_offline/beijingTMS/' + zoom + '/' + (tileCoord.x + "").replace(/-/gi, "M") + '/' + (tileCoord.y + "").replace(/-/gi, "M") + '.jpg';
    };
    map.addTileLayer(tilelayer);
	
	
    var MAX = 10;
	var markers = [];
	var pt = null;
	var i = 0;
	for (; i < MAX; i++) {
	   pt = new BMap.Point(Math.random()  + 116, Math.random()  + 39);
	   markers.push(new BMap.Marker(pt));
	}
	//最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
	var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
             </script>

</body>
</html>