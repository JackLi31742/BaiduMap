<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <title></title>
   <!--  <link rel="stylesheet" href="https://openlayers.org/en/v4.0.1/css/ol.css" type="text/css"> -->
    <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <!-- <script src="https://openlayers.org/en/v4.0.1/build/ol.js"></script> -->
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
    <style>
      #map {
        position: relative;
      }
      #info {
        position: absolute;
        height: 1px;
        width: 1px;
        z-index: 100;
      }
      .tooltip.in {
        opacity: 1;
      }
      .tooltip.top .tooltip-arrow {
        border-top-color: white;
      }
      .tooltip-inner {
        border: 2px solid white;
      }
    </style>
  </head>
  <body>
     <input type="button" name="name" value="添加聚合标签" id="addFeatures" />
    <input type="button" name="name" value="移除聚合标签" id="removeFeatures" />
    <div id="map" class="map"></div>

    <script type="text/javascript">
        window.onload = function () {
        	
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
            
            //初始化地图
            var map = new ol.Map({
                target: 'map',
                layers: [
                    baiduMapLayer2
                    
                ],
                view: new ol.View({
                	center: ol.proj.transform([116.404, 39.915], 'EPSG:4326', 'EPSG:3857'),
                    zoom: 13
                })
            });
 
          //创建要素的数量
            //10000个点没有任何压力，50000个点稍微有些卡了，100000个点可以把浏览器卡崩溃
            var count = 100;
            //创建一个要素数组
            var features = new Array(count);
            //坐标偏移量
           // var e = 8;
 
            for (var i = 0; i < count; i++) {
                
                //新建点要素
                features[i] = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([Math.random()  + 116, Math.random()  + 39])));
            }
 
            //初始化矢量数据源
            var source = new ol.source.Vector({
                //要素
                features:features
            });
 
            //初始化聚合标注数据源
            var clusterSource = new ol.source.Cluster({
                //标注元素之间的间距
                distance: 40,
                //数据源
                source:source
            });
 
            //样式缓存
            var styleCache = {};
            //初始化矢量图层
            var clusters = new ol.layer.Vector({
                //数据源
                source: clusterSource,
                //样式
                style: function (feature, resolution) {
                    //当前聚合标注数据源的要素大小
                    var size = feature.get('features').length;
                    //定义样式
                    var style = styleCache[size];
                    //如果当前样式不存在则创建
                    if (!style) {
                        style = [
                            //初始化样式
                            new ol.style.Style({
                                //点样式
                                image: new ol.style.Circle({
                                    //点的半径
                                    radius: 10,
                                    //笔触
                                    stroke: new ol.style.Stroke({
                                        color: '#fff'
                                    }),
                                    //填充
                                    fill: new ol.style.Fill({
                                        color: '#3399cc'
                                    })
                                }),
                                //文本样式
                                text: new ol.style.Text({
                                    //文本内容
                                    text: size.toString(),
                                    //填充
                                    fill: new ol.style.Fill({
                                        color: '#fff'
                                    })
                                })
                            })
                        ];
                        styleCache[size] = style;
                    }
                    return style;
                }
            });
            //将聚合标注图层添加到map中
            map.addLayer(clusters);
 
            //获取添加聚合标注按钮
            document.getElementById('addFeatures').onclick = function () {
                //获取聚合标注数据源中的要素
                var currentFeatures = clusterSource.getSource().getFeatures();
                //如果当前数据源中没有任何要素则添加
                if (currentFeatures.length == 0) {
                    clusterSource.getSource().addFeatures(features);
                    clusters.setSource(clusterSource);
                    map.addLayer(clusters);
                }
            };
 
            //获取移除聚合标注的按钮
            document.getElementById('removeFeatures').onclick = function () {
                //清除聚合标注数据源中的所有元素
                clusterSource.getSource().clear();
                //从map中移除聚合标注图层
                map.removeLayer(clusters);
            };
        };
    </script>



  </body>
</html>