package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * @name 源数据·                                                                                                                                                                        信息
 * @table 
 */
@SuppressWarnings("serial")
public class Content implements Serializable {
	
	private String id; // 征订号
	private String content;
	private String hid;
	
	public Content() {
		super();
	}
	
	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
