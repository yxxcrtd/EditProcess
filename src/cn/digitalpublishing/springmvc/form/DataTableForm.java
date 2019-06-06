package cn.digitalpublishing.springmvc.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableForm<T> {
	
	private String id;
	
	private String msg;
	
	private String isSuccess;
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	/**
	 * DataTable请求服务器端次数
	 */
	private Integer sEcho;

	/**
	 * 每页显示的数量
	 */
	private Integer iDisplayLength = 10;
	
	/**
	 * 过滤前总记录数
	 */
	private Integer iTotalRecords;
	
	/**
	 * 过滤后总记录数
	 */
	private Integer iTotalDisplayRecords;

	/**
	 * 分页时每页跨度数量
	 */
	private Integer iDisplayStart = 0;
	
	/**
	 * 返回的数据
	 */
	private List<T> aaData;

	/**
	 * 过滤文本
	 */
	//private String sSearch;

	/**
	 * 列数
	 */
	//private Integer iColumns = 8;

	/**
	 * 排序列的数量
	 */
	//private Integer iSortingCols;

	/**
	 * 逗号分割所有的列
	 */
	//private String sColumns = "";
	//private String sColumns = "'id','name','link','more','icon','must','height','width'";

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/*public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}*/

	/*public Integer getiColumns() {
		return iColumns;
	}

	public void setiColumns(Integer iColumns) {
		this.iColumns = iColumns;
	}

	public Integer getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(Integer iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}*/

	/*public String[][] getAaData() {
		return aaData;
	}

	public void setAaData(String[][] aaData) {
		this.aaData = aaData;
	}*/
	
}