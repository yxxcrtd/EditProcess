package cn.digitalpublishing.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @name 源数据·                                                                                                                                                                        信息
 * @table P_PRODUCT
 */
@SuppressWarnings("serial")
public class Resource implements Serializable {
	
	private String id; // 征订号
	private String name;
	private String author;
	private String isbn;
	private Date publishDate;//出版时间（文本格式）
	private BigDecimal price;//纸书价格
	private BigDecimal onPrice;//在线阅读价
	private BigDecimal offPrice;//离线阅读价
	private String publisher; //出版社
	private String edition;//版次
	private Integer pages; // 产品页数
	private String frame; // 产品装帧
	private String format;//开本
	private Integer sheet; // 印张
	private String cover; //封面（格式：jpg或png；尺寸：300*400；文件大小：200K以内）
	private String url;//回链地址
	private String introduction;//简介
	private String content;//内容全文
	
	
	public Resource() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getOnPrice() {
		return onPrice;
	}
	public void setOnPrice(BigDecimal onPrice) {
		this.onPrice = onPrice;
	}
	public BigDecimal getOffPrice() {
		return offPrice;
	}
	public void setOffPrice(BigDecimal offPrice) {
		this.offPrice = offPrice;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Integer getSheet() {
		return sheet;
	}
	public void setSheet(Integer sheet) {
		this.sheet = sheet;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
