package cn.digitalpublishing.util;

import org.apache.commons.lang.StringUtils;

public class PathUtil {
	public static final String DOT = ".";
	public static final String SLASH_ONE = "/";
	public static final String SLASH_TWO = "\\";
	public static final String SLASH = "/";
	public static final String JUNCTION = "-";
	public static final String SEMICOLON = ";";
	/**
	 * 获取没有扩展名的文件名（或者路径）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getWithoutExtension(String fileName) {
		String ext = StringUtils.substring(fileName, 0,
				StringUtils.lastIndexOf(fileName, DOT));
		return StringUtils.trimToEmpty(ext);
	}
	/**
	 * 获取（文件或者路径的）扩展名（文件名中不能含有"."）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, DOT))
			return StringUtils.EMPTY;
		String ext = StringUtils.substring(fileName,
				StringUtils.lastIndexOf(fileName, DOT));
		return StringUtils.trimToEmpty(ext);
	}
	/**
	 * 判断是否同为扩展名（参数ext要包含"."的）
	 * 
	 * @param fileName
	 * @param ext
	 * @return
	 */
	public static boolean isExtension(String fileName, String ext) {
		return StringUtils.equalsIgnoreCase(getExtension(fileName), ext);
	}
	/**
	 * 判断是否存在扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean hasExtension(String fileName) {
		return !isExtension(fileName, StringUtils.EMPTY);
	}
	/**
	 * 得到正确的扩展名（拼接扩展名）
	 * 
	 * @param ext
	 * @return
	 */
	public static String trimExtension(String ext) {
		return getExtension(DOT + ext);
	}

	/**
	 * 替换文件的扩展名
	 * 
	 * @param ext
	 * @return
	 */
	public static String replaceExtension(String fileName, String ext) {
		if(hasExtension(fileName)) {
			StringBuffer sb = new StringBuffer();
			sb.append(getWithoutExtension(fileName));
			sb.append(DOT);
			sb.append(ext);
			return sb.toString();
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(fileName);
			sb.append(DOT);
			sb.append(ext);
			return sb.toString();
		}
	}
	
	/**
	 * 通过数组完整链接绝对PATH
	 * 
	 * @param paths
	 * @return
	 */
	public static String bulidAbsolutePath(String... paths) {
		StringBuffer sb = new StringBuffer();
		for (String path : paths) {
			path = StringUtils.replace(StringUtils.trimToEmpty(path), SLASH_ONE,
					SLASH_TWO);
			sb.append(path);
		}
		return sb.toString();
	}

	/**
	 * 向path中填充扩展名(如果没有或不同的话)
	 * 
	 * @param fileName
	 * @param ext
	 * @return
	 */
	public static String fillExtension(String fileName, String ext) {
		fileName = fileName + DOT;
		ext = trimExtension(ext);
		if (!hasExtension(fileName)) {
			return fileName + getExtension(ext);
		}
		if (!isExtension(fileName, ext)) {
			return getWithoutExtension(fileName) + getExtension(ext);
		}
		return fileName;
	}
	
	/**
	 * 判断是否是文件PATH
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFile(String fileName) {
		return hasExtension(fileName);
	}

	/**
	 * 判断是否是文件夹PATH
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFolder(String fileName) {
		return !hasExtension(fileName);
	}

	/**
	 * 通过数组完整链接PATH
	 * 
	 * @param paths
	 * @return
	 */
	public static String bulidFullPath(String... paths) {
		StringBuffer sb = new StringBuffer();
		for (String path : paths) {
			if(isFolder(path) && !path.endsWith(SLASH)) {
				path = path + SLASH;
			}
			sb.append(path);
		}
		return sb.toString();
	}
	
	/**
	* 得到路径分隔符在文件路径中指定位置前最后出现的位置。
	* 对于DOS或者UNIX风格的分隔符都可以。
	* @param fileName 文件路径
	* @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
	*/
	public static int getPathLastIndex(String fileName) {
		int point = fileName.lastIndexOf(SLASH);
		return point;
	}
	
	/**
	* 得到文件名中的父路径部分。
	* 对两种路径分隔符都有效。
	* 不存在时返回""。
	* 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
	* @param fileName 文件名
	* @return 父路径，不存在或者已经是父目录时返回""
	*/
	public static String getPathPart(String fileName) {
		int point = getPathLastIndex(fileName);
		if (point == -1) {
			return "";
		} else {
			return fileName.substring(0, point);
		}
	}
	
	/**
	 * 判断操作系统 Windows or Linux
	 * @return
	 */
	public static boolean isWindowsOS(){
	    boolean isWindowsOS = false;
	    String osName = System.getProperty("os.name");
	    if(osName.toLowerCase().indexOf("windows")>-1){
	      isWindowsOS = true;
	    }
	    return isWindowsOS;
	 }
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileName1 = "D:/images/face/ypm/";
		String fileName2 = "D:/images/face/ypm";
		String fileName3 = "D:/images/face/ypm/ggh.txt";
		String fileName4 = "D:/images/face/ypm/ggh";
	}
}
