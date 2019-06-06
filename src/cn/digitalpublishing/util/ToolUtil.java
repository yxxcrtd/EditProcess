package cn.digitalpublishing.util;

public class ToolUtil {
	public static String getUUID(){
		String uuid = java.util.UUID.randomUUID().toString();
		uuid=uuid.replace("-", "");
		return uuid;
	}
	
	
	/**
	 * 封装图书标题样式
	 * @param strContent
	 * @return
	 */
	public static String returnBookTitle(String strContent){
		String str = "<h1 style='font-size: 32px; font-weight: bold;"
		+"border-bottom: 2px solid rgb(204, 204, 204); padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;" 
		+"label='标题居中'>";
		str=str+strContent+"<br/>";
		return str;
	}
	/**
	 * 封装图书章样式
	 * @param strContent
	 * @return
	 */
	public static String returnChapter(String strContent){
		String str = "<p><strong><span style=\"font-size: 20px;\">";
		str=str+strContent+"</span></strong><br/>&nbsp; &nbsp; &nbsp; &nbsp;</p>";
		return str;
	}
	
	/**
	 * 封装图书节样式
	 * @param strContent
	 * @return
	 */
	public static String returnSection(String strContent){
		String str = "<p><strong><span style=\"font-size: 18px;\">";
		str=str+strContent+"</span></strong><br/>&nbsp; &nbsp; &nbsp; &nbsp;</p>";
		return str;
	}
	
	/**
	 * HTML格式
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String stringHTML(String content){
		StringBuffer sb =  new StringBuffer();
		sb.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title></title>");
		sb.append("<meta http-equiv=Content-Type content='text/html; charset=utf-8'>");
	
		sb.append("<style type='text/css'>");
		sb.append("*{");
		sb.append("font-family: KaiTi_GB2312;");
		sb.append("}");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(content);
		sb.append("<body>");
	
		return sb.toString();
	}
}
