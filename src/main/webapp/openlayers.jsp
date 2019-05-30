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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>监控</title>
<link rel="stylesheet" href="${ctx}/static/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/static/css/admin.css">
<script src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/pintuer.js"></script>





<link rel="stylesheet" type="text/css" href="${ctx}/openlayers/ol.css" />
    <style type="text/css">
        body, #mainMap {
            border: 0px;
            margin: 0px;
            padding: 0px;
            width: 100%;
            height: 100%;
            font-size: 13px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/openlayers/ol.js"></script>
    
</head>
<body>
	<div id="map_container" class="map-container" style="height: 100%"></div>
	




	<script type="text/javascript">
	
	
    var resolutions = [];
    var maxZoom = 18;
 
    // 计算百度使用的分辨率
    for (var i = 0; i <= maxZoom; i++) {
        resolutions[i] = Math.pow(2, maxZoom - i);
    }
    var tilegrid = new ol.tilegrid.TileGrid({
        origin: [0, 0],
        resolutions: resolutions    // 设置分辨率
    });
 
    // 创建百度地图的数据源
    var baiduSource = new ol.source.TileImage({
        projection: 'EPSG:3857',
        tileGrid: tilegrid,
        tileUrlFunction: function (tileCoord, pixelRatio, proj) {
            var z = tileCoord[0];
            var x = tileCoord[1];
            var y = tileCoord[2];
 
            // 百度瓦片服务url将负数使用M前缀来标识
            if (x < 0) {
                x = -x;
            }
            if (y < 0) {
                y = -y;
            }
 
            return "baidu_offline/beijingTMS/" + z + "/" + x + "/" + y + ".jpg";
        }
    });
 
    // 百度地图层
    var baiduMapLayer2 = new ol.layer.Tile({
        source: baiduSource
    });
 
    // 创建地图
    var map =new ol.Map({
        layers: [
            baiduMapLayer2
        ],
        view: new ol.View({
            
            center: ol.proj.transform([116.404, 39.915], 'EPSG:4326', 'EPSG:3857'),
            zoom: 13
        }),
        target: 'map_container'
    });
    

	 
	 
</script>

</body>
</html>