<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——文档管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		
		$(function(){
			// 为下面迭代的所有tr绑定mouserover与mouseout事件
			$("tr[id^='tr']").hover(
					function(){ // mouserover
						$(this).css({"background-color" : "#FFFFBF", "cursor" : "pointer"});
					}, function(){ // mouseout
						$(this).css("background-color", "#FFFFFF");
					});
			// 为全选绑定事件
			$("#checkAll").on("click", function(){
				// 获取下面所有checkbox
				$("input[id^='box_']").attr("checked", this.checked);
			});
		});
		
		// 文档删除
		var deleteFn = function(id){
			if (confirm("您确定要删除吗？")){
				window.location = "${ctx}/document/deleteDocument.action?pageModel.pageIndex=${pageModel.pageIndex}&document.id=" + id;
			}
		};		
		
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：文档管理 &gt; 文档查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<s:form  method="post" id="documentForm" action="/document/selectDocument.action" theme="simple">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	文档标题：<s:textfield type="text" name="document.title"/>
					    	<input type="submit" value="搜索"/>
					    	<s:if test="tip != null">
					    		<font color="red">${tip}</font>
					    	</s:if>
					    </td> 
					  </tr>
					</table>
				</s:form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit">
		      <td width="5%"><input type="checkbox" id="checkAll"/>全选</td>
			  <td>编号</td>
			  <td>文档标题</td>
			  <td>备注</td>
			  <td>创建日期</td>
			  <td>创建人</td>
			  <td colspan="3" align="center">操作</td>
			</tr>
			<s:iterator value="documents" status="stat">
				<tr class="main_trbg" id="tr_${stat.index}">
				  <td><input type="checkbox" id="box_${stat.index}" value="${id}"/>${stat.count}</td>
				  <td><s:property value="id"/></td>
				  <td><s:property value="title"/></td>
				  <td><s:property value="remark"/></td>
				  <td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				  <td><s:property value="user.name"/></td>
				  <td align="center" width="40px;"><a href="${ctx}/document/showUpdateDocument.action?document.id=${id}">
						<img title="修改" src="${ctx}/images/update.gif"/></a>
				  </td>
				  <td align="center" width="40px;"><a href="javascript:void(0)" onclick="deleteFn(${id})">
						<img title="删除" src="${ctx}/images/delete.gif"/></a>
				  </td>
				   <td align="center" width="40px;">
				   		<a href="${ctx}/document/downDocument.action?document.id=${id}">下载</a>
				  </td>
				</tr>
			</s:iterator>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  		<fkjava:pager pageIndex="${pageModel.pageIndex}" 
	  					  pageSize="${pageModel.pageSize}" 
	  					  recordCount="${pageModel.recordCount}" 
	  					  submitUrl="${ctx}/document/selectDocument.action?pageModel.pageIndex={0}&document.title=${document.title}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>