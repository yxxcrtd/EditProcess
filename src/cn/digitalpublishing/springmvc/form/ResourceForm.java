package cn.digitalpublishing.springmvc.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Resource;

public class ResourceForm extends DataTableForm<Resource>{
	
	private Resource obj;
	private String name;
	private String isbn;
	private String author;
	
	/**
	 * 转换标记
	 * 1：pdf
	 * 2：xml
	 * 3：doc
	 */

	private int formatFlag;
	private CommonsMultipartFile upLoadFileXML = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public  Resource getObj() {
		return obj;
	}

	public void setObj(Resource obj) {
		this.obj = obj;
	}

	public CommonsMultipartFile getUpLoadFileXML() {
		return upLoadFileXML;
	}

	public void setUpLoadFileXML(CommonsMultipartFile upLoadFileXML) {
		this.upLoadFileXML = upLoadFileXML;
	}

	public int getFormatFlag() {
		return formatFlag;
	}

	public void setFormatFlag(int formatFlag) {
		this.formatFlag = formatFlag;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
