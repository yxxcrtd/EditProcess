package cn.digitalpublishing.util;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.output.FileWriterWithEncoding;
/**
 * 转换成DOC
 * @author	zhouwenqian
 *
 */
@SuppressWarnings("deprecation")
public class DOCHelper {
	/**
	 * 转换成doc
	 * @param content	要转换的内容
	 * @param savePath	保存路径
	 * @param enCoding	编码
	 * @throws IOException
	 */
	public static void toDoc(String content, String savePath,String enCoding) throws IOException{
       
		FileWriterWithEncoding docWriter = null;
		try {
         
			docWriter = new FileWriterWithEncoding(savePath,enCoding);
			docWriter.write(content);
		} catch(Exception e){
			throw e;
		}finally{
			if(docWriter!=null){
				try {
					docWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
			
    }
}
