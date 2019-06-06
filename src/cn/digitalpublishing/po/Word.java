package cn.digitalpublishing.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Word implements Serializable {
	private String id; // 征订号
	private String title;//标题
	private String name;//word 稿件名
	private String lastName;//lastname 稿件名
	private String chainedFile;//word稿件链接
	private String photo; //图片名（格式：jpg或png；尺寸：300*400；文件大小：200K以内）
	private String photoLink;//图片链接
	
	private Integer contenNum;


	public Integer getContenNum() {
		return contenNum;
	}


	public void setContenNum(Integer contenNum) {
		this.contenNum = contenNum;
	}


	public Word() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChainedFile() {
		return chainedFile;
	}
	public void setChainedFile(String chainedFile) {
		this.chainedFile = chainedFile;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhotoLink() {
		return photoLink;
	}
	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
