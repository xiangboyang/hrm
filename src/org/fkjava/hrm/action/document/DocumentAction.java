package org.fkjava.hrm.action.document;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.fkjava.hrm.action.base.AbstractAction;
import org.fkjava.hrm.common.WebConstant;
import org.fkjava.hrm.common.io.FileUtil;
import org.fkjava.hrm.domain.Document;

/**
 * 文档管理
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-5 下午3:51:32
 * @version 1.0
 */
public class DocumentAction extends AbstractAction {
	
	private static final long serialVersionUID = -8512290066347370938L;
	private Document document;
	private List<Document> documents;
	
	/** 定义文件上传的三个属性 */
	private File doc;
	private String docFileName;
	private String docContentType;
	

	/** 分页查询文档 */
	public String selectDocument(){
		try{
			// GET请求中文转码
			if (document != null){
				document.setTitle(WebConstant.isoToUtf8(document.getTitle()));
			}
			documents = hrmService.getDocumentByPage(document, pageModel);
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 添加文档 */
	public String addDocument(){
		try{
			String url = FileUtil.uploadFile(doc, docFileName, "/WEB-INF/doc");
			document.setUrl(url);
			hrmService.saveDocument(document);
			setTip("添加成功！");
		}catch(Exception ex){
			setTip("添加失败！");
			log(ex);
		}
		return SUCCESS;
	}

	/** 修改文档 */
	public String updateDocument(){
		try{
			// 获取文件以前的URL
			String oldUrl = document.getUrl();
			// 判断是不是选择了新的上传文件
			if (doc != null){
				// 调用工具类上传文件
				String url = FileUtil.uploadFile(doc, docFileName, "/WEB-INF/doc");
				document.setUrl(url);
			}
			hrmService.updateDocument(oldUrl, document);
			setTip("修改成功！");
		}catch(Exception ex){
			setTip("修改失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 跳转到修改文档页面 */
	public String showUpdateDocument(){
		try{
			document = hrmService.getDocument(document.getId());
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 删除文档 */
	public String deleteDocument(){
		try{
			hrmService.deleteDocument(document.getId());
			setTip("删除成功！");
		}catch(Exception ex){
			setTip("删除失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	
	/** ########### 文件下载 ############## */
	public String downDocument(){
		try{
			document = hrmService.getDocument(document.getId());
			// 判断下载的文件是否存在
			if (ServletActionContext.getServletContext()
					.getResourceAsStream(document.getUrl()) != null){
				return SUCCESS;
			}
		}catch(Exception ex){
			log(ex);
		}
		document.setTitle(null);
		setTip("下载的文件不存在！");
		return INPUT;
	}
	
	
	
	/** setter and getter method */
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	public File getDoc() {
		return doc;
	}
	public void setDoc(File doc) {
		this.doc = doc;
	}
	public String getDocFileName() {
		return docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}
	public String getDocContentType() {
		return docContentType;
	}
	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}
}