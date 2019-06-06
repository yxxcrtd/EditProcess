package cn.digitalpublishing.springmvc.form;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Word;

public class WordForm extends DataTableForm<Word> {
	private Word obj;
	private String title;
	private String name;
	private String photo;
	private String text;
	private CommonsMultipartFile upLoadPhoto = null;
	private CommonsMultipartFile upLoadWord = null;
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CommonsMultipartFile getUpLoadPhoto() {
		return upLoadPhoto;
	}

	public void setUpLoadPhoto(CommonsMultipartFile upLoadPhoto) {
		this.upLoadPhoto = upLoadPhoto;
	}

	public CommonsMultipartFile getUpLoadWord() {
		return upLoadWord;
	}

	public void setUpLoadWord(CommonsMultipartFile upLoadWord) {
		this.upLoadWord = upLoadWord;
	}

	public Word getObj() {
		return obj;
	}

	public void setObj(Word obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
