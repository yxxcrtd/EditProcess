package cn.digitalpublishing.util;

public class UploadConstants {
	
	public static final String COLUMN_TYPE_STRING = "String";
	public static final String COLUMN_TYPE_NUMBER = "Number";

	/***************Excel导入 start*****************************************************************************************************/
	
	/**
	 * SaleItem导入文件的Label名称
	 */
	public static final String SALE_ITEM_IMPORT_NUM = "订单项数量";
	public static final String SALE_ITEM_IMPORT_CURR = "订单币制";
	public static final String SALE_ITEM_IMPORT_TOTAL = "目录价格";
	public static final String SALE_ITEM_IMPORT_DISCOUNT = "折扣";
	public static final String SALE_ITEM_IMPORT_EXCHANGE = "汇率";
	public static final String SALE_ITEM_IMPORT_TAX_TOTAL = "税钱";
	public static final String SALE_ITEM_IMPORT_TAX_RATE = "税率";
	public static final String SALE_ITEM_IMPORT_MODULUS = "系数";
	public static final String SALE_ITEM_IMPORT_ACCOUNT_CODE = "客户编号";
	public static final String SALE_ITEM_IMPORT_ISBN = "isbn";
	public static final String SALE_ITEM_IMPORT_SUPPLIER_DISCOUNT = "供应商折扣";
	
	/***************Excel导入 end*****************************************************************************************************/
	/***************Excel导出 start*****************************************************************************************************/
	
	/**
	 * SaleItem导出文件的Label名称
	 */
	public static final String SALE_ITEM_EXPORT_CODE = "订单编号";
	public static final String SALE_ITEM_EXPORT_NUM = "订单项数量";
	public static final String SALE_ITEM_EXPORT_CURR = "订单币制";
	public static final String SALE_ITEM_EXPORT_TOTAL = "目录价格";
	public static final String SALE_ITEM_EXPORT_DISCOUNT = "折扣";
	public static final String SALE_ITEM_EXPORT_EXCHANGE = "汇率";
	public static final String SALE_ITEM_EXPORT_TAX_TOTAL = "税钱";
	public static final String SALE_ITEM_EXPORT_TAX_RATE = "税率";
	public static final String SALE_ITEM_EXPORT_MODULUS = "系数";
	
	/***************Excel导出 end*****************************************************************************************************/
	
	public static final int IMAGE_BODY_FEATURE_LIBRARY_EXIST = 0;
	
	public static final String XLS_FILE = "xls";
	
	public static final String CSV_FILE = "csv";
	
	public static final char CSV_SPLIT_SYMBOL = ',';
	
	public static final String UPLOAD_ENCODING_GBK = "GBK";
	
	public static final String UPLOAD_ENCODING_UTF8 = "UTF-8";
	
	public static final String JPG_FILE = "jpg";
	
	public static final String PNG_FILE = "png";
	
	public static final String GIF_FILE = "gif";
	
	public static final String ZIP_FILE = "zip";
	
	public static final String TXT_FILE = "txt";
	
	/**
	 * Excel文件后缀数组
	 */
	public static final String[] EXCEL_EXTENSIONS = {"xls", "xlsx"};
	
	/***************上传文件的文件格式  end*****************************************************************************************************/

	/***************sql语句加条件但不需要传变量  start*****************************************************************************************************/
	
	public static final String SQL_WHERE_WITHOUT_PARAMETER = "flag";
	
	/***************sql语句加条件但不需要传变量 end*****************************************************************************************************/
}
