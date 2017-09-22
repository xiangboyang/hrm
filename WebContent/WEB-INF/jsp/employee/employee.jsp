<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——员工管理</title>
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
		function move(row) {
			row.style.backgroundColor = "#ebf0f5";
			row.style.cursor = "pointer";
		}
		function out(row) {
			row.style.backgroundColor = "#ffffff";
		}
		/** 生成Excel文件 */
		function excelFn(){
			/** 将表单中元素序列化get请求字符串 */
			var params = $("#employeeForm").serialize();
			document.location = "${ctx}/employee/downExcel.action?" + params;
		};
		$(function(){
			// 异步加载部门数据(填充部门下拉列表)
			$.ajax({
				url : "${ctx}/dept/deptAjax.action",
				type : "post",
				dataType : "json",
				async : true,
				success : function(data){
					// data数据格式：[{id : 1, name : '技术部'}, {id : 1, name : '技术部'}]
					var deptId = "${employee.dept.id}";
					$.each(data, function(i, item){
						$("<option/>").val(item.id)
									  .html(item.name)
									  .attr("selected", item.id == deptId)
									  .appendTo("#deptSelect");
						//$("#deptSelect").append("<option value='"+ item.id +"'>"+ item.name +"</option>");
					});
				},
				error : function(){
					alert("数据加载失败！");
				}
			});
		});
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
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
			  	<s:form method="post" id="employeeForm" action="/employee/selectEmployee.action" theme="simple">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	职位：<s:select name="employee.job.id" cssStyle="width:120px;"
					    			list="jobs" headerKey="0" headerValue="--请选择职位--"
					    			listKey="id" listValue="name"/>
					    	姓名：<s:textfield name="employee.name"/>
					    	身份证号码：<s:textfield name="employee.cardId" maxLength="18"/>
					    </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	性别：<s:select list="#{1 : '男', 2 : '女'}" headerKey="0" headerValue="--请选择性别--"
					    			cssStyle="width:120px;" name="employee.sex"/>
					    	
					    	手机：<s:textfield name="employee.phone"/>
					    	所属&nbsp;&nbsp;部门：<select id="deptSelect" name="employee.dept.id" style="width:100px;">
								<option value="0">--部门选择--</option>
							</select>&nbsp;
					    	<input type="submit" value="搜索"/>
					    	<input type="button" value="导出EXCEL" onclick="excelFn();"/>
					    	
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
			  <td>编号</td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>手机号码</td>
			  <td>邮箱</td>
			  <td>职位</td>
			  <td>学历</td>
			  <td>身份证号码</td>
			  <td>部门</td>
			  <td>联系地址</td>
			  <td>创建日期</td>
			  <td colspan="2" align="center">操作</td>
			</tr>
			<s:iterator value="employees">
				<tr class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
				  <td><s:property value="id"/></td>
				  <td><s:property value="name"/></td>
				  <td>${sex == 1 ? "男" : "女"}</td>
				  <td><s:property value="phone"/></td>
				  <td><s:property value="email"/></td>
				  <td><s:property value="job.name"/></td>
				  <td><s:property value="education"/></td>
				  <td><s:property value="cardId"/></td>
				  <td><s:property value="dept.name"/></td>
				  <td><s:property value="address"/></td>
				  <td>
					<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
				  </td>
				  <td align="center" width="40px;"><a href="${pageContext.request.contextPath}/emp/showUpdateEmp.do?id=${id}">
						<img title="修改" src="${ctx}/images/update.gif"/></a>
				  </td>
				  <td align="center"  width="40px;"><a href="${pageContext.request.contextPath}/emp/deleteEmp.do?id=${id}">
						<img title="删除" src="${ctx}/images/delete.gif"/></a>
				  </td>
				</tr>
			</s:iterator>
			
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  		<fkjava:pager pageIndex="${pageModel.pageIndex }" 
	  		              pageSize="${pageModel.pageSize }" 
	  		              recordCount="${pageModel.recordCount}" 
	  		              submitUrl="${ctx}/employee/selectEmployee.action?pageModel.pageIndex={0}&employee.job.id=${employee.job.id}&employee.name=${employee.name}&employee.cardId=${employee.cardId}&employee.sex=${employee.sex}&employee.phone=${employee.phone}&employee.dept.id=${employee.dept.id}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>