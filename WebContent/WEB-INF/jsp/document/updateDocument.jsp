<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人事管理系统 ——后台管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
		
		<script type="text/javascript">
			$(function(){
				 /** 修改文档 */
		        $("#btn").bind("click", function(){
		        	var title = $("#title");
		        	var remark = $("#remark");
		        	var msg = "";
		        	if ($.trim(title.val()) == ""){
		        		msg = "文档标题不能为空！";
		        		title.focus();
		        	}else if ($.trim(remark.val()) == ""){
		        		msg = "文档备注不能为空！";
		        	}
		        	if (msg != ""){
		        		alert(msg);
		        	}else{
		        		$("#documentForm").submit();
		        	}
		        });
			});
		       
	
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr><td height="10"></td></tr>
			 <tr>
			    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
				<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：文档管理  &gt; 修改公告</td>
				<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
			 </tr>
		</table>
		
		<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
		  	<tr valign="top">
			    <td>
				  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
				  	<!-- Action全局的错误信息(表单重复提交) -->
				  	<s:actionerror cssStyle="font-size:12px;color:red;"/>
				  	<!-- Field级别错误信息(后台校验的出错信息) -->
				  	<s:fielderror cssStyle="font-size:12px;color:red;"/>
				  	<!-- 表单 -->
				  	<s:form id="documentForm" action="/document/updateDocument.action" theme="simple"
				  			 method="post" enctype="multipart/form-data">
				  		<!-- 防表单重复提交需要传的一个token -->
						<s:token></s:token>
						<!-- 文档的标识符属性 -->
						<s:hidden name="document.id"></s:hidden>
						<!-- 文档存放url的属性-->
						<s:hidden name="document.url"></s:hidden>
					    <tr><td class="font3 fftd">文档标题：<s:textfield name="document.title" size="51" id="title"/>
					    		<s:if test="tip != null"><font color="red">${tip }</font></s:if>
					    	</td>
					    </tr>
						<tr><td class="main_tdbor"></td></tr>
						
						 <tr><td class="font3 fftd">上传文档：
					    		<input type="file" name="doc" id="doc"/>
					    	</td>
					    </tr>
						<tr><td class="main_tdbor"></td></tr>
						
						<tr><td class="font3 fftd">文档备注：<br/>
							<s:textarea name="document.remark" cols="50" rows="5" id="remark"/>
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
						
						<tr><td class="font3 fftd">
								<input type="button" id="btn" value="确定">
								<input type="reset" value="重置">
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
					</s:form>
				  </table>
				</td>
		  	</tr>
		</table>
		<div style="height:10px;"></div>
	</body>
</html>