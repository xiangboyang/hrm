package org.fkjava.hrm.common.web;
/**
 * 分页实体
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 下午2:49:51
 * @version 1.0
 */
public class PageModel {
	
	/** 定义默认每页显示的记录条数 */
	private static final int PAGE_SIZE = 2;
	
	/** 当前页码 */
	private int pageIndex; 
	/** 每页显示的数量 */
    private int pageSize;
    /** 总记录条数 */
    private int recordCount;
    
    /** setter and getter method */
	public int getPageIndex() {
		// 判断当前页码不能小于1
		this.pageIndex = pageIndex <= 1  ? 1 : pageIndex;
		// 判断当前页码不能大于总页数
		int totalPage = this.getRecordCount() % this.getPageSize() == 0 
						? this.getRecordCount() / this.getPageSize()
						: (this.getRecordCount() / this.getPageSize()) + 1;
		return pageIndex > totalPage ? totalPage : pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize <= 0 ? PAGE_SIZE : pageSize;
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
	// limit 第一个问号
	public int getStartRow(){
		return (this.getPageIndex() - 1) * this.getPageSize();
	}
}