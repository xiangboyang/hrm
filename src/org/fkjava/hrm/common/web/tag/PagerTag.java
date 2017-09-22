package org.fkjava.hrm.common.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 分页标签处理类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 上午10:00:01
 * @version 1.0
 */
public class PagerTag extends SimpleTagSupport {
	
	// 请求的URL: page.action?pageIndex={0}
	/** 定义占位符常量 */
	private static final String TAG = "{0}";
	/** 当前页码 */
	private int pageIndex; 
	/** 每页显示的数量 */
    private int pageSize;
    /** 总记录条数 */
    private int recordCount;
    /** 请求的URL */
    private String submitUrl;
    /** 样式名 */
	private String style = "sabrosus";
	
	/** 总页数 */
	private int totalPage;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		StringBuilder res = new StringBuilder();
		StringBuilder str = new StringBuilder();
		// 判断总记录条数是不是大于零
		if (recordCount > 0){
			// 计算出总页数
			totalPage = (recordCount % pageSize == 0) 
						? recordCount / pageSize 
						: (recordCount / pageSize) + 1;
			// 判断【上一页】、【下一页】是不是要加a标签
			if (pageIndex == 1){ // 在首页
				str.append("<span class='disabled'>上一页</span>");
				
				// 计算中间的页码
				calcPage(str);
				
				// 判断下一页
				if (totalPage > 1){
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex + 1));
					str.append("<a href='"+ tempUrl +"'>下一页</a>");
				}else{
					str.append("<span class='disabled'>下一页</span>");
				}
			}else if (pageIndex == totalPage){ // 在尾页
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一页</a>");
				
				// 计算中间的页码
				calcPage(str);
				
				str.append("<span class='disabled'>下一页</span>");
			}else{ // 在中间
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一页</a>");
				
				// 计算中间的页码
				calcPage(str);
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.pageIndex + 1));
				str.append("<a href='"+ tempUrl +"'>下一页</a>");
			}
			// 开始条数
			int beginNum = (this.pageIndex - 1) * this.pageSize + 1;
			// 结束条数
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;
			// 拼接其它的信息
			res.append("<table align='center' width='100%' style='font-size:13px;' class='"+ style +"'><tr><td>").append(str.toString())
			   .append("跳转到<input type='text' size='2' id='page_jump_size'/>")
			   .append("<input type='button' value='确定' id='page_jump_btn'/>")
			   .append("</td></tr>")
			   .append("<tr align='center'><td>总共<font color='red'>"+ this.recordCount +"</font>条记录，当前显示"+ beginNum +"-"+ endNum +"条记录。")
			   .append("</td></tr></table>");
			res.append("<script type='text/javascript'>")
			   .append("     document.getElementById('page_jump_btn').onclick = function(){")
			   .append("           var page_size = document.getElementById('page_jump_size').value;")
			   .append("           if (!/^\\d+$/.test(page_size) || page_size < 1 || page_size > "+ totalPage +"){")
			   .append("             alert('请输入[1-"+ totalPage +"]之间的页码！');")
			   .append("           }else{")
			   .append("             var tempUrl = '" + this.submitUrl + "';")
			   .append("             document.location.href = tempUrl.replace('"+ TAG +"', page_size);")
			   .append("          }")
			   .append("     };")
			   .append("</script>");
			
		}else{ // 没有总录条数(小于等于零)
			res.append("<table align='center'><tr><td>")
			   .append("总共<font color='red'>0</font>条记录，当前显示0-0条记录。")
			   .append("</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}

	/** 计算中间页码的方法 */
	private void calcPage(StringBuilder str) {
		// 判断总页数
		if (this.totalPage <= 10){
			// 中间全部显示出来
			for (int i = 1; i <= this.totalPage; i++){
				if (i == pageIndex){ // 当前页码
					str.append("<span class='current'>"+ i +"</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
				}
			}
		}else{
			// 大于10页
			// 靠首页近些12345678...100
			if (this.pageIndex <= 8){
				for (int i = 1; i <= 10 ; i++){
					if (i == pageIndex){ // 当前页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				str.append("...");
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("<a href='"+ tempUrl +"'>"+ totalPage +"</a>");
			}
			// 靠尾页近些1...93949596979899100
			else if (this.pageIndex + 8 >= totalPage){
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				for (int i = this.totalPage - 10; i <= this.totalPage ; i++){
					if (i == pageIndex){ // 当前页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
			}
			// 靠中间近些1...50515253545556...100
			else{
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				
				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4 ; i++){
					if (i == pageIndex){ // 当前页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				
				str.append("...");
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("<a href='"+ tempUrl +"'>"+ totalPage +"</a>");
			}
		}
	}


	/** setter and getter method */
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getSubmitUrl() {
		return submitUrl;
	}
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}