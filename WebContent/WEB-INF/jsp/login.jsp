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
		<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
		<script type="text/javascript">
			$(function(){
				// 为验证码看不清楚绑定事件
				$("#see").click(function(){
					$("#vimg").attr("src", "${ctx}/verify.action?random="+ Math.random());
				});
				// 为验证码图片绑定事件
				$("#vimg").mouseover(function(){
					$(this).css("cursor", "pointer");
				}).click(function(){
					// 事件触发
					$("#see").trigger("click");
				});
				
				// 登录按钮
				$("#loginFn").click(function(){
					// 做表单输入校验
					var userId = $("#userId");
					var password = $("#password");
					var code = $("#code");
					var msg = "";
					if ($.trim(userId.val()) == ""){
						msg = "用户名不能为空！";
						userId.focus(); // 获取焦点
					}else if (!/^\w{5,20}$/.test($.trim(userId.val()))){
						msg = "用户名长度必须在5-20之间！";
						userId.focus(); // 获取焦点
					}else if ($.trim(password.val()) == ""){
						msg = "密码不能为空！";
						password.focus(); // 获取焦点
					}else if (!/^\w{6,20}$/.test($.trim(password.val()))){
						msg = "密码长度必须在6-20之间！";
						password.focus(); // 获取焦点
					}else if ($.trim(code.val()) == ""){
						msg = "验证码不能为空！";
						code.focus(); // 获取焦点
					}else if (!/^[a-zA-Z0-9]{4}$/.test($.trim(code.val()))){
						msg = "验证码格式不正确！";
						code.focus(); // 获取焦点
					}
					if (msg != ""){
						alert(msg);
					}else{
						// 把表单中所有input元素序列化成get请求字符串
						var params = $("#loginForm").serialize();
						//alert(params);
						// 可以登录, 发送异步请求
						$.ajax({
							url : "${ctx}/loginAjax.action", // 请求的url
							type : "post", // 请求的方式
							data : params, // 请求参数
							dataType : "json", // 响应的数据类型
							async : true, // 异步请求
							success : function(data){ // 请求成功时回调的函数
								if (data.status == 0){
									window.location = "${ctx}/main.action";
								}else{
									alert(data.tip);
									// 让验证码变一下
									// 事件触发
									$("#see").trigger("click");
								}
								
							},
							error : function(){ // 请求出错时回调函数
								alert("数据加载失败！");
							}
						});
					}
				});
				
				// 为document绑定onkeydown事件(用来监听是不是按了回车键)
				$(document).keydown(function(event){
					if (event.keyCode === 13){
						$("#loginFn").trigger("click");
					}
				});
			});
		</script>
	</head>
	<body background="images/9.png">
		<div class="log_black1"></div>
		<div id="showOrHide">
			<div id="login_main">
			  <div class="login_main_tab">
			    <div class="log_main_t_top"></div>
			    <div class="log_main_t_mid">
				  	<div class="log_black2"></div>
					<form  method="post" id="loginForm" >
					  <div class="log_input">用户名:<input type="text" id="userId" name="userId" value="admin" size="20" /></div>
					  <div class="log_input">密　码:<input type="password" id="password" value="123456" name="password" /></div>
					  <div class="log_input">
					  	<table><tr>
					  		<td style="font-size:12px; color:#296dcc;">
					  			验证码:<input type="text" style="margin-left:5px;width:35px;" id="code" value="8888" name="code" maxlength="4"/>
					  		</td>
					  		<td>
					  			<img title="验证码" width="60" height="22" id="vimg" src="${ctx}/verify.action"/>
					  		</td>
					  		<td><a href="javascript:void(0)" id="see">看不清楚</a></td>
					  	</tr></table>
					  </div>
					  
					  <div class="log_button"><input type="button" value="登 录" id="loginFn" />&nbsp;&nbsp;<input type="reset" value="重 置" /></div>
					</form>
				</div>
			    <div class="log_main_t_bottom"></div>
			  </div>
			</div>
		</div>
	</body>
</html>