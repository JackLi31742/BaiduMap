<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
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
<script type="text/javascript">


   /*  function sendJson2() {
    	var defer = $.Deferred();
        $.ajax({
            type:"post",
            url:"${ctx}/test-requestbody2",
            data:'{"id":1234,"name":"电冰箱","price":"1999"}',
            contentType:"application/json;charset=utf-8",
            async: true,
            success:function(data) {
               // alert(data.id + ":" + data.name);
                //defer.resolve(data);
            }
        });
        
        return defer.promise(); 
    }
    
    
    $.when(sendJson2()).done(function(data) { // 在ajax请求完后调用

    	var responseData = data.id + ":" + data.name; // 将拿到的数据赋值到全局变量
    	alert("responseData:"+responseData);

    }).fail(function() { // 失败时调用

    });
	
	
	function forward() {
		$.ajax({
			type : "post",
			url : "${ctx}/testforward",
			data : '{"id":1234,"name":"电冰箱","price":"1999"}',
			contentType : "application/json;charset=utf-8",
			success : function() {
			}
		});
	} */
	
	function getData(){
		$.ajax({
			type : "GET",
			url : "${ctx}/getMessage",
			contentType : "application/json;charset=utf-8",
			async:true,
			success : function(data) {
				console.log("data:"+data);
				$.each(data, function(index,element) {//循环
                    $("#testList").append(element.name+"<br/>");
                    $("#testList").append(element.gender+"<br/>");
                    $("#testList").append(element.age+"<br/>");  
				});
			}
		});
		};
</script>
</head>
<body>
	<div style="padding:30px;">				 
	  	<a class="button button-little bg-red" href="${ctx}/effectScatter-bmap"> effectScatter-bmap</a> 
	  	<a class="button button-little bg-red" href="${ctx}/lines-bmap-bus"> lines-bmap-bus</a> 
	  	<a class="button button-little bg-red" href="${ctx}/neo4j"> neo4j</a> 
	  	<a class="button button-little bg-red" href="${ctx}/baidumap"> baidumap</a> 
	  	<a class="button button-little bg-red" href="${ctx}/baidu_offline_test2"> baidu_offline_test2</a> 
	  	
	  	<a class="button button-little bg-red" href="${ctx}/test-requestbody"> TEST REQUESTBODY</a> 
	  	<a class="button button-little bg-red" onclick="sendJson2()"> TEST REQUESTBODY2</a> 
	  	<a class="button button-little bg-red" onclick="forward()"> TEST forward</a> 
	  	
	  	<a class="button button-little bg-red" href="${ctx}/test-RequestParam?name=1&id=90"> test RequestParam</a> 
	  	
	  	<a class="button button-little bg-red" href="${ctx}/getDangerLevel?danger_type='行凶'"> test getDangerLevel</a> 
	  	<a class="button button-little bg-red" href="${ctx}/getTarget_info?id=1"> test getTarget_info</a> 
	  	<a class="button button-little bg-red" href="${ctx}/getDanger_rank"> test getDanger_rank</a> 
	  	<a class="button button-little bg-red" href="${ctx}/getGraph?id=1"> test getGraph</a> 
	  	<a class="button button-little bg-red" href="${ctx}/getPeople?id=1"> test getPeople</a> 
	  	
	  	
	  	<a class="button button-little bg-red" href="${ctx}/baidu_offline_video"> baidu_offline_video</a> 
	  	
	  	
	  	<a class="button button-little bg-red" href="${ctx}/baidu_offline_test?camId=1"> baidu_offline_test</a> 
	  	
	  	
	  	<a class="button button-little bg-red" href="${ctx}/findByName?name='王莹'&start=1539852509917&end=1545806658333"> test findByName</a> 
	  	<a class="button button-little bg-red" href="${ctx}/findByName?name='许伟'&start=1539852509917&end=1545806658333"> test findByName2</a> 
	  	<a class="button button-little bg-red" href="${ctx}/findByEvent?type=2&start=1537174196000&end=1537381396009"> test findByEvent</a> 
	  	<a class="button button-little bg-red" href="${ctx}/findByEvent?type=1&start=1537170586000&end=1537256996000"> test findByEvent2</a> 
	  	<a class="button button-little bg-red" href="${ctx}/findByMultipleEvent"> test findByMultipleEvent</a> 
	  	
	  	
	  	<a class="button button-little bg-red" href="${ctx}/initTopic"> test initTopic</a> 
	  	<a class="button button-little bg-red" href="${ctx}/insert"> test insert</a> 
	  	<a class="button button-little bg-red" href="${ctx}/getCameraIds"> test getCameraIds</a> 
	  	<a class="button button-little bg-red" onclick="getData()"> test getMessage</a> 
	  	
	  	
	  	
	</div>


<div id="testList" ></div>
	<c:forEach items="${messageList}" var="obj" varStatus="status">

			
			<div class="detail" style="display:none">姓名：${obj.name}<br>性别：${obj.gender }<br>年龄：${obj.age }<br></div>

		</c:forEach>
</body>
</html>