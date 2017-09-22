<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加员工</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
  	<script type='text/javascript' src='${ctx}/dwr/interface/DwrService.js'></script>
  	<script type='text/javascript' src='${ctx}/js/jquery-1.8.3.js'></script>
	
	<script type="text/javascript">
		/** 文件上传 */
		var changeFn = function(obj){
			DwrService.uploadPic(obj, function(imgUrl){
				$("#logoImage").attr("src", "${ctx}" + imgUrl);
			});
		};
		$(function(){
			/** 填充部门与职位 */
			DwrService.loadDeptJobs(function(data){
				data = $.parseJSON(data);
				// 填充部门
				$.each(data.depts, function(i, item){
					$("<option/>").val(item.id).html(item.name).appendTo("#deptSelect");
				});
				// 填充职位
				$.each(data.jobs, function(i, item){
					$("<option/>").val(item.id).html(item.name).appendTo("#jobSelect");
				});
			});
		});
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 添加员工</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	<s:form theme="simple" action="/employee/addEmp.action" id="empForm" method="post">
    	  <!-- 隐藏表单用来传员工的肖像URL -->
    	  <input type="hidden" name="employee.picture" id="picture"/>
    	  <s:token></s:token>
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<s:if test="tip != null">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${tip }</font></s:if>
		    	<table>
		    		<tr><td rowspan="5"><img src="${ctx}/images/none.jpg" width="139" height="147" id="logoImage"/></td></tr>
		    		<tr>
		    			<td class="font3 fftd">姓名：<input type="text" name="employee.name" id="name" size="20"/></td>
		    			<td class="font3 fftd">身份证号码：<input type="text" name="employee.cardId" id="cardId" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">性别：<s:select list="#{1:'男', 2:'女' }" name="employee.sex" id="sex"/></td>
		    			<td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：<select  name="employee.job.id" id="jobSelect"></select></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">学历：<s:textfield name="employee.education" id="education" size="20"/></td>
		    			<td class="font3 fftd">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<s:textfield name="employee.email" id="email" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">手机：<s:textfield name="employee.phone" id="phone" size="20"/></td>
		    			<td class="font3 fftd">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<s:textfield name="employee.tel" id="tel" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td width="135px" align="center">
		    				<div style="width:70px;height:17px;background-image:url('${ctx}/images/bt_file.jpg');overflow:hidden;">
					    		<input type='file' id="image" onchange="changeFn(this);" style="filter:alpha(opacity=0);opacity:0.0;width:70px;height:17px;"/>
					    	</div>
		    			</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					政治面貌：<s:textfield name="employee.party" id="party" size="40"/>&nbsp;&nbsp;
					QQ&nbsp;&nbsp;号码：<s:textfield name="employee.qqNum" id="qqNum" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					联系地址：<s:textfield name="employee.address" id="address" size="40"/>&nbsp;&nbsp;
					邮政编码：<s:textfield name="employee.postCode" id="postCode" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					出生日期：<s:textfield cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="employee.birthday" id="birthday" size="40"/>&nbsp;&nbsp;
					民&nbsp;&nbsp;&nbsp;&nbsp;族：<s:textfield name="employee.race" id="race" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					所学专业：<s:textfield  name="employee.speciality" id="speciality" size="40"/>&nbsp;&nbsp;
					爱&nbsp;&nbsp;&nbsp;&nbsp;好：<s:textfield name="employee.hobby" id="hobby" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					备&nbsp;&nbsp;&nbsp;&nbsp;注：<s:textfield name="employee.remark" id="remark" size="40"/>
					&nbsp;&nbsp;所属部门：<select name="employee.dept.id" id="deptSelect" style="width:120px;"></select>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="确定 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
	  	</s:form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>