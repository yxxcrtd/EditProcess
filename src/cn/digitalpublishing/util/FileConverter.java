package cn.digitalpublishing.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileConverter implements Converter<String, CommonsMultipartFile>{

	@Override
	public CommonsMultipartFile convert(String source) {
		return null;
	}

}
