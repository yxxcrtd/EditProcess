package cn.digitalpublishing.util;


import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.PDStandardEncryption;



import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

@SuppressWarnings("deprecation")
public class PdfHelper {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {  
		//copyPdf("L:/9786000000004.pdf","L:/9786000000004_part.pdf","1-2");
		textMark("清华大学  192.168.1.108", 50, "127.0.0.1","D:/tmp/9780000000019_110.pdf","D:/tmp/9780000000019_110_water.pdf",Color.GRAY,"STSong-Light","UniGB-UCS2-H",BaseFont.EMBEDDED);
		String path = "D:/metadata/9781613501306_water.pdf";
	}
	
	/**
	 * 加水印
	 * 
	 * @param watermark 水印的文字
	 * @param fontSize 水印的字体大小
	 * @param ip 水印的IP地址
	 * @param sourceFile PDF源文件的全路径
	 * @param targetFile PDF目标文件的全路径
	 * @param color 
	 * @param fontName
	 * @param encoding
	 * @param embedded
	 * @throws Exception
	 */
	public static void textMark(String watermark, float fontSize, String ip, String sourceFile, String targetFile, Color color, String fontName, String encoding, boolean embedded) throws Exception {
		try{
			// 待加水印的文件  
			PdfReader reader = new PdfReader(sourceFile);
			
			// 加完水印的文件  
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetFile));  
			
			int total = reader.getNumberOfPages() + 1;  
			
			PdfContentByte content;  
			// 设置字体  
			BaseFont base = BaseFont.createFont(fontName,encoding,embedded);  
			// 水印文字 
			float high = 0;// 高度
			float width = 0;//宽度


			// 循环对每页插入水印  
			for (int i = 1; i < total; i++) {
				Document document = new Document(reader.getPageSize(i));
				// 水印的起始  
				high = document.getPageSize().getHeight()/2;
				width = document.getPageSize().getWidth()/2;
				content = stamper.getOverContent(i);
				PdfGState gs = new PdfGState();  
				gs.setFillOpacity(0.2f);//设置透明度为0.2  
				content.setGState(gs);  

				//content.addImage(jpeg);
				// 开始  
				content.beginText();  
				// 设置颜色  
				content.setColorFill(color);  
				// 设置字体及字号  
				content.setFontAndSize(base, fontSize);  
				content.showTextAligned(Element.ALIGN_CENTER, watermark, width, high, 35);//水印文字成35度角倾斜
				
				// 同时添加IP地址
				content.showTextAligned(Element.ALIGN_BOTTOM, ip, width + 10, high / 2,  35);
				content.endText();
				content.saveState();
			}  
			stamper.close();
		}catch(Exception e){
			throw e;
		}
	}

	/** 
	 * According to the original pdf file to create a new pdf file, the new pdf can be part of the original pdf file
	 * @param sourceFile Path of original pdf file 
	 * @param targetFile Path of new pdf file 
	 * @param ranges   A range of pages, example 1-20  
	 */ 
	public static void copyPdf(String sourceFile ,String targetFile, String ranges)throws Exception{
		try{
			PdfReader pdfReader = new PdfReader(sourceFile);
			PdfStamper pdfStamper = new PdfStamper(pdfReader , new FileOutputStream(targetFile)); 
			pdfReader.selectPages(ranges); 
			pdfStamper.close();
		}catch(Exception e){
			throw e;
		} 
	}
	public boolean CreatePDFbyString(String content){ 
		try { 
			/** 实例化文档对象 */ 
			Document document = new Document(PageSize.A4, 50, 50, 50, 50); 
			
			/** 创建 PdfWriter 对象 */ 
			PdfWriter.getInstance(document,// 文档对象的引用 
					new FileOutputStream("d://ITextTest.pdf"));//文件的输出路径+文件的实际名称 
			document.open();// 打开文档 
			
			/** pdf文档中中文字体的设置，注意一定要添加iTextAsian.jar包 */ 
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", 
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);//加入document： 
			
			/** 向文档中添加内容，创建段落对象 */ 
			document.add(new Paragraph(content));// Paragraph添加文本 
			document.add(new Paragraph("我们是害虫", FontChinese)); //FontChinese:为中文字体的设置
			
			/** 创建章节对象 */ 
			Paragraph title1 = new Paragraph("第一章", FontChinese); 
			Chapter chapter1 = new Chapter(title1, 1); 
			chapter1.setNumberDepth(0); 
			/** 创建章节中的小节 */ 
			Paragraph title11 = new Paragraph("表格的添加", FontChinese); 
			Section section1 = chapter1.addSection(title11); 
			/** 创建段落并添加到小节中 */ 
			Paragraph someSectionText = new Paragraph("下面展示的为3 X 2 表格.", 
					FontChinese); 
			section1.add(someSectionText); 
			
			/** 创建表格对象（包含行列矩阵的表格） */ 
			Table t = new Table(3, 2);// 2行3列 
			t.setBorderColor(new Color(220, 255, 100)); 
			t.setPadding(5); 
			t.setSpacing(5); 
			t.setBorderWidth(1); 
			Cell c1 = new Cell(new Paragraph("第一格", FontChinese)); 
			t.addCell(c1); 
			c1 = new Cell("Header2"); 
			t.addCell(c1); 
			c1 = new Cell("Header3"); 
			t.addCell(c1); 
			// 第二行开始不需要new Cell() 
			t.addCell("1.1"); 
			t.addCell("1.2"); 
			t.addCell("1.3"); 
			section1.add(t); 
			
			/** 创建章节中的小节 */ 
			Paragraph title13 = new Paragraph("列表的添加", FontChinese); 
			Section section3 = chapter1.addSection(title13); 
			/** 创建段落并添加到小节中 */ 
			Paragraph someSectionText3 = new Paragraph("下面展示的为列表.", FontChinese); 
			section3.add(someSectionText3); 
			/** 创建列表并添加到pdf文档中 */ 
			List l = new List(true, true, 10);// 第一个参数为true，则创建一个要自行编号的列表， 
			// 如果为false则不进行自行编号 
			l.add(new ListItem("First item of list")); 
			l.add(new ListItem("第二个列表", FontChinese)); 
			section3.add(l); 
			document.add(chapter1); 
			
			/** 创建章节对象 */ 
			Paragraph title2 = new Paragraph("第二章", FontChinese); 
			Chapter chapter2 = new Chapter(title2, 1); 
			chapter2.setNumberDepth(0); 
			/** 创建章节中的小节 */ 
			Paragraph title12 = new Paragraph("png图片添加", FontChinese); 
			Section section2 = chapter2.addSection(title12); 
			
			/** 添加图片 */ 
			section2.add(new Paragraph("图片添加: 饼图", FontChinese)); 
			Image png = Image.getInstance("D:/pie.png");//图片的地址 
			section2.add(png); 
			
			document.add(chapter2); 
			document.close(); 
			return true; 
		} catch (Exception e2) { 
		} 
		return false; 
	} 
	
	/**
	 * 转如其名html文件 并存储
	 * @param savePath	保存路径
	 * @param content	要转换的内容
	 * @param enCoding	编码
	 * @throws IOException
	 */
	public static void createHTML(String savePath,String content,String enCoding) throws IOException{
		FileOutputStream outStream = null;
		OutputStreamWriter osw = null;
		try {
			outStream = new FileOutputStream(savePath);
			osw = new OutputStreamWriter(outStream,enCoding);
			osw.write(content);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(osw!=null){
				osw.close();
			}
			if(outStream!=null){
				outStream.close();
			}
		}
	}
	
	
	public static void CoderPDF(String filepath,String key){
		try
		  {
		       PDDocument pdf = PDDocument.load(filepath);
			   //create the encryption options
			   PDStandardEncryption encryptionOptions = new PDStandardEncryption();
			   encryptionOptions.setCanPrint(false);
			   pdf.setEncryptionDictionary(
			   encryptionOptions );
			   //encrypt the document
			   pdf.encrypt( "master", key );
			   //save the encrypted document
			   //to the file system
			   pdf.save(filepath);
		  }
		  catch (Exception e) {
		   // TODO: handle exception
		  }
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
