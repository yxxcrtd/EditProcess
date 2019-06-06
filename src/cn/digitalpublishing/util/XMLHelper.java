package cn.digitalpublishing.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
/**
 * 转换成xml
 * @author	zhouwenqian
 *
 */
public class XMLHelper {
	/**
	 * 转换成xml
	 * @param content	要转换的内容
	 * @param savePath	保存路径
	 * @param enCoding	编码
	 * @throws IOException	
	 */
	public static void txtToXML(String content, String savePath,String enCoding) throws IOException{
       
		XMLWriter xmlWriter = null;
		try {
			
			Document document = DocumentHelper.createDocument();
            Element books = DocumentHelper.createElement("books");
            document.add(books);
            Element contents = DocumentHelper.createElement("content");
            contents.setText(content);
            books.add(contents);
         
            Writer filewriter = new FileWriter(savePath);
            xmlWriter = new XMLWriter(filewriter);
            xmlWriter.write(document);
			
		} catch(Exception e){
			throw e;
		}finally{
			xmlWriter.close();
		}
			
    }
}
