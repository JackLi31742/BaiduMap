<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

 <style>


    
@keyframes shadow {
    0%, 100% {transform: scaleX(1);}
    50% {transform: scaleX(1.2);}
}

@keyframes rotate {
    0% {
   		 transform: translateY(0) ;
  	}
    
    50% {
        transform: translateY(20px);
       
    }
    
    100% {
        transform: translateY(0) ;
    }
}

@keyframes twinkle {
            from {
                background: #F3F1EC;
            }
            to {
                background: #FF0000;
            }
        }

</style>
<script src="${ctx}/static/js/jquery.js"></script>
<script src="${ctx}/static/js/pintuer.js"></script>


<!--界面显示框架-->
<link rel="stylesheet" href="${ctx}/baidu_offline/assets/css/layui.css">
<!--实例样式-->
<link rel="stylesheet" href="${ctx}/baidu_offline/assets/css/map.css">
<link rel="stylesheet"
	href="${ctx}/baidu_offline/assets/font/mine-font/iconfont.css">
<!--百度基础地图-->
<script src="${ctx}/baidu_offline/assets/baiduJs/apiv.2.1.js"></script>
<script src="${ctx}/baidu_offline/assets/baiduJs/getmodules.js"></script>

<script src="${ctx}/baidu_offline/assets/jquery-2.1.4.min.js"></script>

<!--百度绘制、测量-->
<script src="${ctx}/baidu_offline/assets/baiduJs/DrawingManager.js"></script>
<script src="${ctx}/baidu_offline/assets/baiduJs/DistanceTool.js"></script>
<script src="${ctx}/baidu_offline/assets/baiduJs/AreaTool.js"></script>

<!--框架即功能javascript-->
<script src="${ctx}/baidu_offline/assets/layui.js"></script>
<script src="${ctx}/baidu_offline/assets/common.js "></script>
<script src="${ctx}/baidu_offline/assets/tinycolor.js "></script>

</head>
<body>
	<div id="map_container" class="map-container" style="height: 100%"></div>
	<div id="list_length" style="display:none">${fn:length(list)}</div>
	<div id="testList" style="display:none">
		<c:forEach items="${list}" var="obj" varStatus="status">

			<div class="longitude" style="display:none">${obj.longitude}</div>
			<div class="latitude" style="display:none">${obj.latitude}</div>
			<div class="type" style="display:none">${obj.type}</div>
			<div class="detail1" style="display:none">姓名：${obj.name}<br>性别：${obj.gender }<br>年龄：${obj.age }<br>时间：${obj.timestampStr }<br>
			
			</div>
			<div class="detail2" style="display:none">
			开始时间：${obj.start_timeStr}<br>
			结束时间：${obj.end_timeStr}<br>
			</div>
			<div class="imgtest" style="display:none"><img style='float: left; margin: 4px' id='imgDemo' src="${obj.pic_path}" width='139' height='104' /></div>

		</c:forEach>
	</div>

	


	<script type="text/javascript">
	
	/* 定时刷新 */
	/*  function myrefresh()
	{
	   window.location.reload();
	}
	setTimeout('myrefresh()',10000);  */
	
	//window.location.href = "${ctx}/initTopic";
	 $(function(){
		//initTopic();
		
		  setInterval(function(){
		   getData();
		  // getInfo();
		  }, 5000);
	}); 
	
	
		
		
		/* function initTopic(){
			$.ajax({
				type : "GET",
				url : "${ctx}/initTopic",
				contentType : "application/json;charset=utf-8",
				async: false,
				success : function() {
				}
			});
			}; */
	
	var map = new BMap.Map("map_container");
	var point = new BMap.Point(116.3523668982, 40.0263994550);
	map.centerAndZoom(point, 18); // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	
	var tilelayer = new BMap.TileLayer(); // 创建地图层实例    
    tilelayer.getTilesUrl = function(tileCoord, zoom) { // 设置图块路径     
        return 'baidu_offline/targetTMS/' + zoom + '/' + (tileCoord.x + "").replace(/-/gi, "M") + '/' + (tileCoord.y + "").replace(/-/gi, "M") + '.jpg';
    };
    map.addTileLayer(tilelayer);
	
	
	//浮点
	
	
	
	var data_info_img= [];
	var data_info_longitude= [];
	var data_info_latitude= [];
	var data_info_detail1= [];
	var data_info_detail2= [];
	var data_info_type=[];
	
	//折线
	//var lineObj = new Array();
	
	
    
    
   // function getInfo(){
    	var length=parseInt($("#list_length").text());
    	console.log("length:"+length);
    if(length>0){
    	var data_info =  new Array(length);   //表格有10行
    	for(var i = 0;i < length; i++){
    		data_info[i] = new Array(4);    //每行有10列
    	}
    	
    	$("#testList").each(function (index,element) {
    		
    		$(this).find("div.longitude").each(function(){
    			data_info_longitude.push($(this).text());
    		})
    		$(this).find("div.latitude").each(function(){
    			data_info_latitude.push($(this).text());
    		})
    		$(this).find("div.type").each(function(){
    			data_info_type.push($(this).text());
    		})
    		
    		$(this).find("div.imgtest").each(function(){
    			data_info_img.push($(this).html());
    		})
    		$(this).find("div.detail1").each(function(){
    			data_info_detail1.push($(this).html());
    		})
    		$(this).find("div.detail2").each(function(){
    			data_info_detail2.push($(this).html());
    		})
        })
        console.log("data_info_img:"+data_info_img+"\n");
        console.log("data_info_longitude:"+data_info_longitude+"\n");
        console.log("data_info_latitude:"+data_info_latitude+"\n");
        console.log("data_info_detail1:"+data_info_detail1+"\n");
        console.log("data_info_detail2:"+data_info_detail2+"\n");
        console.log("data_info_type:"+data_info_type+"\n");
        
        for (var i=0;i<length;i++){
        		j=0;
            	data_info[i][j]=data_info_longitude[i];
            	data_info[i][j+1]=data_info_latitude[i];
            	if(data_info_type[i]==0){
            		
            		data_info[i][j+2]="异常：黑名单人员<br>"+data_info_detail1[i]+data_info_img[i];
            	}
            	if(data_info_type[i]==1){
            		data_info[i][j+2]="异常：打架斗殴<br>"+data_info_detail2[i]+data_info_img[i];
            	}
            	if(data_info_type[i]==2){
            		data_info[i][j+2]="异常：人群聚集<br>"+data_info_detail2[i]+data_info_img[i];
            	}
            	data_info[i][j+3]=data_info_type[i];
            	//var point=new BMap.Point(data_info_longitude[i],data_info_latitude[i]);
             	//lineObj.push(point);
            
        }
        console.log("data_info:"+data_info);
        for(var i=0;i<data_info.length;i++){
    		//if(data_info==null){
    		//	getInfo();
    	//	}
    		var pt = new BMap.Point(data_info[i][0],data_info[i][1]);
    		var path="";
    		if(data_info[i][3]==0){
				path="baidu_offline/assets/img/pointIcon/Oval-1.png"
			}else if (data_info[i][3]==1){
				path="baidu_offline/assets/img/pointIcon/Oval-2.png"
			}else if (data_info[i][3]==2){
				path="baidu_offline/assets/img/pointIcon/Oval-3.png"
			}
    		var myIcon = new BMap.Icon(path, new BMap.Size(39,59));
    		//var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
    		
    		var content = data_info[i][2];
    		//map.addOverlay(marker);               // 将标注添加到地图中
    		//addClickHandler(content,marker);
    		//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    		console.log("标号："+(i+1));
    		addMarker(pt,(i+1),myIcon,content);
    	}
    }
        function addMarker(point,i,myIcon,content){
        	var label;
        	if(i<10){
        		
  	      		label = new BMap.Label(i,{offset:new BMap.Size(8,0)});
        	}else{
        		label = new BMap.Label(i,{offset:new BMap.Size(5,0)});
        	}
        	
  		  label.setStyle(
  			{
  				 //width:"30px",
  				 height : "10px",
  				 color : "white",
  				 borderStyle:"none",
  				 fontSize : "10px",
  				 textAlign:"center",
  				 lineHeight : "20px",
  				 fontFamily:"微软雅黑",
  				 backgroundColor:"none"
  				 //animation:"rotate 1s 0.8s infinite"
  				//type: 'effectScatter',
  			 }
  			
  		  );
  		  
  		  
  		  
  		  //label.style.animation=BMAP_ANIMATION_BOUNCE;
  		  var marker = new BMap.Marker(point,{icon:myIcon});
  		  map.addOverlay(marker);
		  //label.setAnimation(BMAP_ANIMATION_BOUNCE);
  	      marker.setLabel(label);
  		  //label.setPosition(point);
		 // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
  	      addClickHandler(content,marker);
		  marker.setAnimation(BMAP_ANIMATION_DROP);
  	    /* marker.setStyle(
  	  			{
  	  				animation:"rotate 0.8s infinite"
  	  			 }
  	  			
		  ); */
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
        var opts = {
    			//width : 250,     // 信息窗口宽度
    			//height: 80,     // 信息窗口高度
    			//title : "信息窗口" , // 信息窗口标题
    			enableMessage:true//设置允许信息窗发送短息
    	};
    	
    	
        function getData(){
    		$.ajax({
    			type : "GET",
    			url : "${ctx}/getMessage",
    			contentType : "application/json;charset=utf-8",
    			success : function(data) {
    				ajaxlength=data.length;
    				console.log("AJAX ajaxlength:"+ajaxlength+"\n");
    			if(ajaxlength>0){
    				var ajaxdata_info =  new Array(ajaxlength);   //表格有10行
    		    	for(var i = 0;i < ajaxlength; i++){
    		    		ajaxdata_info[i] = new Array(4);    //每行有10列
    		    	}
    				$.each(data, function(i,element) {//循环
    					j=0;
    					ajaxdata_info[i][j]=element.longitude;
    					ajaxdata_info[i][j+1]=element.latitude;
    					if(element.type==0){
    						
    						ajaxdata_info[i][j+2]="异常：黑名单人员<br>"+"姓名："+element.name+" <br>性别："+element.gender+"<br>年龄："+element.age+"<br>时间："+element.timestampStr +"<br>" 
								+ "<img style='float: left; margin: 4px' id='imgDemo' src="+element.pic_path+" width='139' height='104' />";
    					}
    					if(element.type==1){
    						
    						ajaxdata_info[i][j+2]="异常：打架斗殴<br>"+"开始时间："+element.start_timeStr+" <br>结束时间："+element.end_timeStr+"<br>" 
								+ "<img style='float: left; margin: 4px' id='imgDemo' src="+element.pic_path+" width='139' height='104' />";
    					}
    					if(element.type==2){
    						
    						ajaxdata_info[i][j+2]="异常：人群聚集<br>"+"开始时间："+element.start_timeStr+" <br>结束时间："+element.end_timeStr+"<br>" 
								+ "<img style='float: left; margin: 4px' id='imgDemo' src="+element.pic_path+" width='139' height='104' />";
    					}
						ajaxdata_info[i][j+3]=element.type;
    					
                        
                        
    				});
    				console.log("AJAX data_info:"+ajaxdata_info+"\n");
    				
    				for(var i=0;i<ajaxlength;i++){
    					//if(data_info==null){
    					//	getInfo();
    				//	}
    					var pt = new BMap.Point(ajaxdata_info[i][0],ajaxdata_info[i][1]);
    					var path="";
    					if(ajaxdata_info[i][3]==0){
    						path="baidu_offline/assets/img/pointIcon/Oval-1.png"
    					}else if (ajaxdata_info[i][3]==1){
    						path="baidu_offline/assets/img/pointIcon/Oval-2.png"
    					}else if (ajaxdata_info[i][3]==2){
    						path="baidu_offline/assets/img/pointIcon/Oval-3.png"
    					}
    					var myIcon = new BMap.Icon(path, new BMap.Size(39,59));
    					//var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
    					
    					var content = ajaxdata_info[i][2];
    					//map.addOverlay(marker);               // 将标注添加到地图中
    					//addClickHandler(content,marker);
    					//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    					length=length+i+1;
    					console.log("ajax标号："+length+"内容："+pt+","+content);
    					addMarker(pt,length,myIcon,content);
    				}
    				
    			}
    				
    				
    				
    			}
    		});
    		};
    		
    		
    		
   
</script>

</body>
</html>