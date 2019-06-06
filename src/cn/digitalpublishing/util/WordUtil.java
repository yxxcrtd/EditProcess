package cn.digitalpublishing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.extractor.WordExtractor;


public class WordUtil {

	public static String stringContent(File file) throws IOException{
		FileInputStream fis = null;
		WordExtractor wordExtractor = null;
		try {
			    fis = new FileInputStream(file);
			    wordExtractor = new WordExtractor(fis);
		   } catch (FileNotFoundException e) {
			    e.printStackTrace();
		   } catch (IOException e) {
			    e.printStackTrace();
		   }finally{
			   if(fis!=null){
				   fis.close();
			   }
		   }
		return wordExtractor.getText();
	}

}