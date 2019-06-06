package cn.digitalpublishing.springmvc.form;

import cn.digitalpublishing.po.Content;

public class ContentForm extends DataTableForm<Content>{
	
	private Content obj;
	
	private String content;
	
	private String wordId;
	

	public Content getObj() {
		return obj;
	}

	public void setObj(Content obj) {
		this.obj = obj;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWordId() {
		return wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

}
