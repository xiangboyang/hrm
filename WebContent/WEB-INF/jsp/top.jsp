<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>人事管理系统 ——后台登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
		<!-- 引用jquery时间插件 -->
		<script type="text/javascript" src="${ctx}/js/jquery.timer.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#nowTime").timeRun();
			});
			// 退出函数
			function exit(){
				
			};
		</script>
	</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="320" height="80" class="topbg"><img src="images/top_logo.gif" width="320" height="80"/></td>
	<td class="topbg">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		  <td height="50" class="toplink" align="right"><img src="images/top_home.gif"/>&nbsp;&nbsp;<a href="javascript:void(0);">网站首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/top_exit.gif"/>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="exit();">注销退出</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
		  <td height="30" class="topnavbg">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td width="60"><img src="images/StatBarL.gif" width="60" height="30"/></td>
				<td class="topnavlh" align="left"><img src="images/StatBar_admin.gif"/>&nbsp;&nbsp;当前用户：【${session_user.name}】</td>
				<td class="topnavlh" align="right"><img src="images/StatBar_time.gif"/>&nbsp;&nbsp;<span id="nowTime"></span>
  			 </td>
                <td width="3%"></td>
			  </tr>
			</table>
		  </td>
		</tr>
	  </table>
	</td>
  </tr>
</table>
</body>
</html>