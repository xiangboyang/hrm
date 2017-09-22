<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>人事管理系统 ——公告管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="fkjava.org" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="css.css"/>
	<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		// 获取父窗口传过来的参数
		var id = window.dialogArguments;
		$(function(){
			$.ajax({
				url : "${ctx}/notice/noticeAjax.action", // 请求URL
				data : "id=" + id, // 请求参数
				type : "post", // 请求的方式
				dataType : "html", // 响应回来的数据类型
				async : true, // 异步
				success : function(data){ // 请求成功时要回调的函数
					$(document.body).html(data);
				},
				error : function(){ // 请求失败时要回调的函数
					alert("数据加载失败！");
				}
			});
		});
	</script>
</head>
<body>
   
</body>
</html>