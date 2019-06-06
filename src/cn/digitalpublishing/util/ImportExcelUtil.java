package cn.digitalpublishing.util;

import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import com.google.common.base.Strings;

public class ImportExcelUtil {
	
	/**
	 * 检查excel文件名是否合法
	 * @param filename
	 * @return
	 */
	public static boolean checkExcelExtension(String filename) {
		return FilenameUtils.isExtension(filename.toLowerCase(), UploadConstants.EXCEL_EXTENSIONS);
	}
	
	/**
	 * 检查所有列是否存在
	 * 
	 * @param map
	 * @return
	 */
	public static String checkColumnTemplate(Map<String, Object> map) {
		for (String key : map.keySet()) {
			if (map.get(key) == null) {
				return key;
			};
		}
		return null;
	}
	
	/**
	 * 校验xls文件中必填信息是否有信息
	 * 
	 * @param map
	 * @return
	 */
	public static String checkMandatoryFields(Map<String, Object> map, Map<String, String> columnMap) {
		for (String key : map.keySet()) {
			if (Strings.isNullOrEmpty(map.get(key).toString())) {
				return key;
			};
			if (UploadConstants.COLUMN_TYPE_NUMBER.equals(columnMap.get(key)) && !MathUtil.isNum(map.get(key).toString())) {
				return key;
			}
		}
		return null;
	}

}
