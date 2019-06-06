package cn.digitalpublishing.util;

import java.util.Map;



public class TreeNode {
	public TreeNode() {
		
	}
	public TreeNode(String id,String name,boolean isParent,boolean checked,String url,String otherId){
		this.id= id;
		this.name=name;
		this.isParent = isParent;
		this.checked = checked ;
		this.url = url;
		this.otherId = otherId;
	}
	public TreeNode(String id,String name,boolean isParent,boolean checked,String url){
		this.id= id;
		this.name=name;
		this.isParent = isParent;
		this.checked = checked ;
		this.url = url; 
	}
	public TreeNode(String id,String name,boolean isParent,boolean nocheck){
		this.id= id;
		this.name=name;
		this.isParent = isParent;
		this.nocheck = nocheck ;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param isParent
	 * @param otherId
	 * @param attr 用于将其他数据存储在node节点中，页面使用方式：selectNode.attr.key
	 */
	public TreeNode(String id,String name,boolean isParent,String otherId,Map<String,String> attr){
		this.id= id;
		this.name=name;
		this.isParent = isParent;
		this.otherId = otherId;
		this.attr = attr ;
	} 
	private String id;//节点id
	private String name;//节点名称
	private boolean isParent;//是否为叶子节点
	private boolean checked;
	private String url;
	private String otherId;
	private boolean nocheck;//用于控制是否显示某个节点的 checkbox / radio
	private boolean chkDisabled;
	private Map<String,String> attr;

	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
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
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, String> getAttr() {
		return attr;
	}
	public void setAttr(Map<String, String> attr) {
		this.attr = attr;
	}
	public boolean getNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
}
