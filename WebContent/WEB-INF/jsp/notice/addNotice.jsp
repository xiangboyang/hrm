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
		<!-- TinyMCE -->
		<script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
		<!-- jQuery文件上传的插件 -->
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		
		<script type="text/javascript">
			tinyMCE.init({
				
				mode : "exact", // mode指定附加的模式: textareas所有的<textarea>html元素都附加上编辑器  、exact精确的 elements : "元素id属性值",
				elements : "content", // 指定元素的id
				theme : "advanced", // 主题 (advanced | simple)
				language : "zh-cn", // 国际化
				
				// 指定工具按钮资源存放的文件夹
				plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",
	
				// 指定放四排工具按钮
				theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
				theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
				theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
				theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",
				
				// 指定工具按钮上下显示的位置 (默认在下面 bottom, top)
				theme_advanced_toolbar_location : "top",
				// 指定工具按钮水平显示的位置 (left、center、right)
				theme_advanced_toolbar_align : "left",
				// 指定状态栏显示的位置(bottom、top) 默认不显示
				theme_advanced_statusbar_location : "bottom",
				// 指定编辑器是否可以拖动
				theme_advanced_resizing : true
			});
			$(function(){
				// 文件异步上传(第一种方式)
			   /** $('#picForm').on('submit', function(e) {
				  	// 取消事件默认行为
		            e.preventDefault(); 
				  	// 异步上传
		            $(this).ajaxSubmit({
		                url : "${ctx}/uploadAjax.action", // 请求的url
		                type : "post", // 请求的方式
		                dataType : "text", // 响应的数据类型
		                async : true, // 异步
		                success : function(imgUrl){
		                	alert(imgUrl);
		                },
		                error : function(){
		                	alert("数据加载失败！");
		                }
		            });
		        });**/
		        
				// 异步上传(第二种方式)
	            $("#picForm").ajaxForm({
	                url : "${ctx}/uploadAjax.action", // 请求的url
	                type : "post", // 请求的方式
	                dataType : "text", // 响应的数据类型
	                async : true, // 异步
	                success : function(imgUrl){
	                	tinyMCE.execCommand('mceInsertContent',false,'<img width="200" src="${ctx}'+ imgUrl +'"/>');
	                },
	                error : function(){
	                	alert("数据加载失败！");
	                }
	            });
		        
		        /** 添加公告 */
		        $("#btn").bind("click", function(){
		        	var title = $("#title");
		        	var content =  tinyMCE.get('content').getContent();
		        	var msg = "";
		        	if ($.trim(title.val()) == ""){
		        		msg = "公告标题不能为空！";
		        		title.focus();
		        	}else if ($.trim(content) == ""){
		        		msg = "公告内容不能为空！";
		        	}
		        	if (msg != ""){
		        		alert(msg);
		        	}else{
		        		$("#noticeForm").submit();
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
				<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：公告管理  &gt; 添加公告</td>
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
				  	<s:form id="noticeForm" action="/notice/addNotice.action" theme="simple" method="post">
				  		<!-- 防表单重复提交需要传的一个token -->
						<s:token></s:token>
					    <tr><td class="font3 fftd">公告标题：<s:textfield name="notice.title" size="30" id="title"/>
					    		<s:if test="tip != null"><font color="red">${tip }</font></s:if>
					    	</td>
					    </tr>
						<tr><td class="main_tdbor"></td></tr>
						
						<tr><td class="font3 fftd">公告内容：<br/>
							<s:textarea name="notice.content" cols="88" rows="11" id="content"/>
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
						
						
						<tr><td class="font3 fftd">
								<input type="button" id="btn" value="确定">
								<input type="reset" value="重置">
						</td></tr>
						<tr><td class="main_tdbor"></td></tr>
					</s:form>
					
						
					<!-- 图片上传 -->
					<s:form id="picForm" method="post" enctype="multipart/form-data">
						<tr><td>
							<input type="file" name="pic"  size="30"/><input type="submit" value="上传"/>
						</td></tr>
					</s:form>

				  </table>
				</td>
		  	</tr>
		</table>
		<div style="height:10px;"></div>
	</body>
</html>