<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<%--     <title>地图 javascript 实例</title>
    <!--界面显示框架-->
    <link rel="stylesheet" href="${ctx}/baidu_offline/assets-bak/css/layui.css">
    <!--实例样式-->
    <link rel="stylesheet" href="${ctx}/baidu_offline/assets-bak/css/map.css">
    <link rel="stylesheet" href="${ctx}/baidu_offline/assets-bak/font/mine-font/iconfont.css">
    <!--百度基础地图-->
    <script src="${ctx}/baidu_offline/assets-bak/baiduJs/apiv.2.1.js"></script>
    <!--百度绘制、测量-->
    <script src="${ctx}/baidu_offline/assets-bak/baiduJs/DrawingManager.js"></script>
    <script src="${ctx}/baidu_offline/assets-bak/baiduJs/DistanceTool.js"></script>
    <script src="${ctx}/baidu_offline/assets-bak/baiduJs/AreaTool.js"></script>


<!--框架即功能javascript-->
    <script src="${ctx}/baidu_offline/assets-bak/layui.js"></script>
    <script src="${ctx}/baidu_offline/assets-bak/common.js "></script>
    <script src="${ctx}/baidu_offline/assets-bak/tinycolor.js "></script> 
    <script src="${ctx}/baidu_offline/assets-bak/baiduJs/getmodules.js"></script> --%>
    
    
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
    <script src="${ctx}/baidu_offline/map3.js "></script>
<script src="${ctx}/baidu_offline/assets/jquery-2.1.4.min.js"></script>

<!--框架即功能javascript-->
<script src="${ctx}/baidu_offline/assets/layui.js"></script>
<script src="${ctx}/baidu_offline/assets/common.js "></script>
<script src="${ctx}/baidu_offline/assets/tinycolor.js "></script>
</head>

<body>
    <!--头部导航-->
    <div class="layui-header nav-header">
        
        <ul class="layui-nav nav-header-menu" lay-filter="">
           
            <li class="layui-nav-item">
                <a href="javascript:;">地图标注</a>
                <dl class="layui-nav-child">
                    <!-- 二级菜单 -->
                    
                   <!--  <dd><a onclick="marking(1)">绘点</a></dd>
                    <dd><a onclick="marking(2)">绘线</a></dd>
                    <dd><a onclick="marking(4)">绘矩形</a></dd>
                    <dd><a onclick="marking(5)">绘圆</a></dd> -->
                    <dd><a onclick="marking(3)">绘多边形</a></dd>
                    <dd><a onclick="marking(6)">编辑</a></dd>
                    
                    
                </dl>
            </li>
           
        </ul>
    </div>
    <!--地图容器-->
    <div id="map_container" class="map-container"></div>
   
    <!--标注面板-->
    <div id="map-marking-panel" class="map-marking-panel">
        <div class="map-marking-panel-head">
            <label id="map-marking-panel-title">绘制点标注</label>
            <i class="icon-font icon-guanbi" onclick="closeMarkingPanel()"></i>
        </div>
        <div class="map-marking-panel-container">
            <div id="map-marking-panel-tip" class="map-marking-panel-tip">地图上待标注位置鼠标左键点击地图添加一个点标注，可填写名称、备注、图标，点击保存生效！</div>

            <!--编辑-->
            <div id="map-mark-edit" class="map-mark-edit">
                <!--<button class="layui-btn layui-btn-small " onclick="addSelectInteraction()" title="选择">
                  <i class="layui-icon icon-font icon-xuanze"></i>
                </button>
                <button class="layui-btn layui-btn-small " onclick="addTranslateInteraction()" title="点拖动">
                  <i class="layui-icon icon-font icon-move"></i> 
                </button>-->
                <button class="layui-btn layui-btn-small " onclick="addModifyInteraction()" title="线、面编辑">
                  <i class="layui-icon icon-font icon-nodeEdit"></i>
                </button>
                <button class="layui-btn layui-btn-small layui-btn-danger" onclick="addDeleteInteraction()" title="删除">
                  <i class="layui-icon icon-font icon-shanchu"></i>
                </button>
                <!--<button class="layui-btn layui-btn-small " title="详情">
                  <i class="layui-icon icon-font icon-xiangqing"></i>
                </button>-->
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>操作提示</legend>
                    <div id="map-mark-edit-tip" class="layui-field-box">

                    </div>
                </fieldset>
            </div>

            <!--标注-->
            <div class="map-marking-info" id="map-marking-info">
                <div id="map-marking-info-container" class="layui-form map-marking-info-container">
                    <div class="layui-form-item">
                       
                    </div>
                    <div class="layui-form-item layui-form-text">
                        
                    </div>
                    <div class="layui-form-item mark-style" id="point-style-item">
                        

                    </div>
                    <div class="layui-form-item mark-style" id="line-style-item">
                       
                    </div>
                    <div class="layui-form-item mark-style" id="polygon-style-item">
                        
                    </div>
                    <div class="layui-form-item">
                        
                    </div>
                </div>
            </div>

            <!--点图标面板-->
            <div class="point-style-edit" id="point-style-edit">
               
            </div>

            <!--线样式面板-->
            <div class="line-style-edit" id="line-style-edit">
               
            </div>

            <!--面样式面板-->
            <div class="polygon-style-edit" id="polygon-style-edit">
                
            </div>

            <!--测量信息面板-->
            <div class="measure-info" id="measure-info">
                

            </div>

            <!--自定义地图面板-->
            <div class="map-config" id="map-config">
               
            </div>

        </div>

    </div>


	<div id="testList" style="display:none">
		<c:forEach items="${list}" var="obj" varStatus="status">

			<div class="longitude" style="display:none">${obj.longitude}</div>
			<div class="latitude" style="display:none">${obj.latitude}</div>
			<div class="detail" style="display:none">${obj.detail}</div>
			<div class="imgtest" style="display:none"><img style='float: right; margin: 4px' id='imgDemo' src=${obj.picPath } width='139' height='104' /></div>

		</c:forEach>
	</div>
	

    

</body>

</html>