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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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


</head>
<body>
	<div id="map_container" class="map-container" style="height: 100%"></div>
	<div id="testList" style="display:none">
		<c:forEach items="${list}" var="obj" varStatus="status">

			<div class="longitude" style="display:none">${obj.longitude}</div>
			<div class="latitude" style="display:none">${obj.latitude}</div>
			<div class="detail" style="display:none">姓名：${obj.name}<br>性别：${obj.gender}<br>年龄：${obj.age}<br>时间：${obj.timestampStr}<br></div>
			<div class="imgtest" style="display:none"><img style='float: left; margin: 4px' id='imgDemo' src=${obj.pic_path } width='139' height='104' /></div>

		</c:forEach>
		
	</div>
	
	<div id="testpoint2list" style="display:none">
		
		<c:forEach items="${point2list}" var="obj" varStatus="status">

			<div class="longitude" style="display:none">${obj.lng}</div>
			<div class="latitude" style="display:none">${obj.lat}</div>

		</c:forEach>
	</div>




	<script type="text/javascript">
	/**/
	// 百度地图API功能
	//var sContent =
	//"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>天安门</h4>" + 
	//"<img style='float:right;margin:4px' id='imgDemo' src='20180815112716.jpg' width='139' height='104' title='天安门'/>" + 
	//"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	//"</div>";
	var map = new BMap.Map("map_container");
	var point = new BMap.Point(116.3523668982, 40.0263994550);
	map.centerAndZoom(point, 18); // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	
	var tilelayer = new BMap.TileLayer(); // 创建地图层实例    
    tilelayer.getTilesUrl = function(tileCoord, zoom) { // 设置图块路径     
        return 'baidu_offline/targetTMS/' + zoom + '/' + (tileCoord.x + "").replace(/-/gi, "M") + '/' + (tileCoord.y + "").replace(/-/gi, "M") + '.jpg';
    };
    map.addTileLayer(tilelayer);
	
	//var marker = new BMap.Marker(point);
	//var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
	
	
	//map.addOverlay(marker);
	
	
	//marker.addEventListener("click", function(){          
	   //this.openInfoWindow(infoWindow);
	   //图片加载完毕重绘infowindow
	   //document.getElementById('imgDemo').onload = function (){
		   //infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	  // }
	//});
	
	//var data_info = [[116.417854,39.921988,"地址：北京市东城区王府井大街88号乐天银泰百货八层"+"<img style='float:right;margin:4px' id='imgDemo' src='baidu_offline/20180815112716.jpg' width='139' height='104' title='天安门'/>"],
					// [116.406605,39.921585,"地址：北京市东城区东华门大街"+"<img style='float:right;margin:4px' id='imgDemo' src='baidu_offline/20180816151626.jpg' width='139' height='104' title='天安门'/>"],
					 // [116.412222,39.912345,"地址：北京市东城区正义路甲5号"+"<img style='float:right;margin:4px' id='imgDemo' src='baidu_offline/20180816151657.jpg' width='139' height='104' title='天安门'/>"]
					// ];
	//alert("Text: " + data_info);
	//var data_info =[$("#longitude").text(),$("#latitude").text(),$("#detail").text()+$("#imgtest").text()];
	
	
	//浮点
	
	
	
	var data_info_img= [];
	var data_info_longitude= [];
	var data_info_latitude= [];
	var data_info_detail= [];
	
	//折线
	var lineObj = new Array();
	
	
	
	
	$("#testList").each(function (index,element) {
		
		$(this).find("div.imgtest").each(function(){
			data_info_img.push($(this).html());
		})
		$(this).find("div.longitude").each(function(){
			data_info_longitude.push($(this).text());
		})
		$(this).find("div.latitude").each(function(){
			data_info_latitude.push($(this).text());
		})
		$(this).find("div.detail").each(function(){
			data_info_detail.push($(this).html());
		})
		
    })
    // console.log(data_info_img.length);
    //console.log(data_info_longitude.length);
    //console.log(data_info_latitude.length);
    //console.log(data_info_detail.length);
    
    var data_info =  new Array(data_info_img.length);   //表格有10行
	for(var i = 0;i < data_info.length; i++){
		data_info[i] = new Array(10);    //每行有10列
	}
    
    for (var i=0;i<data_info_longitude.length;i++){
    		j=0;
        	data_info[i][j]=data_info_longitude[i];
        	data_info[i][j+1]=data_info_latitude[i];
        	data_info[i][j+2]=data_info_detail[i]+data_info_img[i];
    	
        	var point=new BMap.Point(data_info_longitude[i],data_info_latitude[i]);
         	lineObj.push(point);
        
    }
//    console.log(data_info);

	//多边形
	var polygonArr=new Array();
	
	var polygonArr_lng=[];
	var polygonArr_lat=[];
	
	$("#testpoint2list").each(function (index,element) {
		
		$(this).find("div.longitude").each(function(){
			polygonArr_lng.push($(this).text());
		})
		$(this).find("div.latitude").each(function(){
			polygonArr_lat.push($(this).text());
		})
		
    })
	
    for (var i=0;i<polygonArr_lng.length;i++){
        	var point=new BMap.Point(polygonArr_lng[i],polygonArr_lat[i]);
        	polygonArr.push(point);
    }
	
	
	var opts = {
				//width : 250,     // 信息窗口宽度
				//height: 80,     // 信息窗口高度
				//title : "信息窗口" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };
	for(var i=0;i<data_info.length;i++){
		var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
		var content = data_info[i][2];
		map.addOverlay(marker);               // 将标注添加到地图中
		addClickHandler(content,marker);
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	}
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e)
		document.getElementById('imgDemo').onload = function (){
		   content.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	   }
		}
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	 
	 
	 //for (var i=0;i<data_info_longitude.length;i++){
     //	var point=new BMap.Point(data_info_longitude[i],data_info_latitude[i]);
     //	lineObj.push(point);
     
 	//}
	 //console.log(lineObj);
	 
	 var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
    scale: 0.6,//图标缩放大小
    strokeColor:'#fff',//设置矢量图标的线填充颜色
    strokeWeight: '2',//设置线宽
	});
	var icons = new BMap.IconSequence(sy, '10', '30');

	 
	 var polyline =new BMap.Polyline(lineObj, {
		   enableEditing: false,//是否启用线编辑，默认为false
		   enableClicking: true,//是否响应点击事件，默认为true
		   icons:[icons],
		   strokeWeight:'8',//折线的宽度，以像素为单位
		   strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
		   strokeColor:"#18a45b" //折线颜色
		});
	 
	 //var polyline = new BMap.Polyline(lineObj, {strokeColor:"yellow", strokeWeight:2, strokeOpacity:0.5});   //创建折线
     map.addOverlay(polyline);   //增加折线
     
     
     var polygon = new BMap.Polygon(polygonArr, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
	 map.addOverlay(polygon);   //增加多边形
	 
	 
</script>

</body>
</html>