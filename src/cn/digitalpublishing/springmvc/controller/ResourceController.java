package cn.digitalpublishing.springmvc.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Resource;
import cn.digitalpublishing.springmvc.form.ResourceForm;
import cn.digitalpublishing.util.DOCHelper;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.PdfHelper;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * 资源
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/pages/dynamic")
public class ResourceController extends BaseController {

	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("resource/ResourceList.ftl");
	}

	/**
	 * 信息列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	public ResourceForm manager(ResourceForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Resource> list = new ArrayList<Resource>();
		try {
			if (!Strings.isNullOrEmpty(form.getName())) {
				condition.put("name", "%" + form.getName() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getIsbn())) {
				condition.put("isbn", "%" + form.getIsbn() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getAuthor())) {
				condition.put("author", "%" + form.getAuthor()+ "%");
			}
			form.setiTotalRecords(this.resourceService.getCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if (0 < form.getiTotalRecords()) {
				list = this.resourceService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
		} catch (Exception e) {
			form.setMsg(exMsg(e));
			forwardString = "msg";
		}
		form.setAaData(list);
		return form;
	}

	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/add")
	public ModelAndView add() throws Exception {
		return new ModelAndView("resource/ResourceEdit.ftl");
	}
	
	/**
	 * 上传
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/form/addftl")
	public ResourceForm iterateWholeXML(ResourceForm form, HashMap<String, String> hm) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Resource> list = new ArrayList<Resource>();
		Resource resource = new Resource();
		String filePath = null;
		String FileXMLName = null;
		if (form.getUpLoadFileXML() != null) {
			FileXMLName = "Main" + form.getUpLoadFileXML().getOriginalFilename().substring(form.getUpLoadFileXML().getOriginalFilename().lastIndexOf("."));
			filePath = getUploadPath() + File.separator+ProcessQueue.XMLPATH +File.separator ;
			UploadFileUtil.writeFile(filePath, FileXMLName, form.getUpLoadFileXML().getBytes());
		}
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File(filePath + FileXMLName));
			Element root = document.getRootElement();
			Element book = root.element("book");
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			resource.setAuthor(String.valueOf(book.element("author").getText()));
			resource.setName(String.valueOf(book.element("title").getText()));
			resource.setPublishDate(sim.parse(String.valueOf(book.element("publishDate").getText())));
			resource.setIsbn(String.valueOf(book.element("isbn").getText()));
			resource.setPrice(new BigDecimal(String.valueOf(book.element("price").getText())));
			resource.setPublisher(String.valueOf(book.element("publisher").getText()));
			resource.setEdition(String.valueOf(book.element("edition").getText()));
			resource.setPages(Integer.valueOf(String.valueOf(book.element("pages").getText())));
			resource.setFrame(String.valueOf(book.element("frame").getText()));
			resource.setFormat(String.valueOf(book.element("format").getText()));
			resource.setSheet(Integer.valueOf(String.valueOf(book.element("sheet").getText())));
			resource.setCover(String.valueOf(book.element("cover").getText()));
			resource.setIntroduction(String.valueOf(book.element("introduction").getText()));
			resource.setContent(String.valueOf(book.element("content").getText()));
			condition.put("isbn", String.valueOf(book.element("isbn").getText()));
			list = this.resourceService.getIsbn(condition, "");
			if(list.size()>0){
				form.setMsg("不能上传isbn号相同的文件！");
				form.setIsSuccess(SUCCESS);
			}else{
			resourceService.insert(resource);
			form.setMsg("添加成功!");
			form.setIsSuccess(SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		form.setUpLoadFileXML(null);
		return form;
 }
	
	/**
	 * 删除
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/form/delete")
	public ResourceForm delete(ResourceForm form) throws Exception {
		String id = request.getParameter("id");
		try {
			this.resourceService.delete(id);
			form.setMsg("删除成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg("删除出错！");
		}
		return form;
	}
	
	/**
	 * 转换选择层
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/transform")
	public ModelAndView transform() throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String forwardString ="resource/ResourceTransform.ftl";
		model.put("id", id);
		return new ModelAndView(forwardString,model);
	}
	
	/**
	 * 转换
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/form/transformSubmit")
	public ResponseEntity<byte[]> transformSubmit(ResourceForm form) throws Exception {
		String id = form.getObj().getId();
		int formatFlag = form.getFormatFlag();
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String filePath = "";
		String prefix = DateFormatUitl.formatString();
		try {
			//获取待转换的内容
			Resource resource = resourceService.getId(id);
			String fileName = "";
			switch (formatFlag) {
				//转换成pdf
				case 1:
					String content = "作者： "+resource.getAuthor()+"<br>"+"封面： "+resource.getCover()+"<br>"+"版次： "+resource.getEdition()+"<br>"+"Isbn： "+resource.getIsbn()+"<br>"+"书名： "+resource.getName()+"<br>"
							+"出版社： "+resource.getPublisher()+"<br>"+"页数： "+resource.getPages()+"<br>"+"印张： "+resource.getSheet()+"<br>"+"纸书价格： "+resource.getPrice()+"<br>"
							+"出版时间： "+resource.getPublishDate()+"<br>"+"<br>"+"<b>简介：</b> "+"<br>"+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+resource.getIntroduction()+"<br>"+"<br>"+"<b>全文：</b> "+"<br>"+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+resource.getContent();
					//转换成html文件，并存储
					content = PdfHelper.stringHTML(content);
					File htmlFile = new File(getUploadPath() + File.separator+ProcessQueue.HTML);
					if(!htmlFile.exists()){
						htmlFile.mkdirs();
					}
					String htmlPath = htmlFile+File.separator+prefix+".html";
					PdfHelper.createHTML(htmlPath, content, "UTF-8");
					//转换成pdf文件，并存储
					File pdfFile = new File(getUploadPath() + File.separator+ProcessQueue.PDFPATH);
					if(!pdfFile.exists()){
						pdfFile.mkdirs();
					}
					fileName = prefix+".pdf";
					filePath = pdfFile+File.separator+fileName;
		 			this.resourceService.createPDF(htmlPath,filePath,  "UTF-8");
		 			break;
		 			//转换成xml
				case 2:
					File xmlFile = new File(getUploadPath() + File.separator+ProcessQueue.XMLPATH);
					if(!xmlFile.exists()){
						xmlFile.mkdirs();
					}
					fileName = prefix+".xml";
					filePath = xmlFile+File.separator+fileName;
					//创建一个document
		            Document document=DocumentHelper.createDocument();
		            //创建根结点
		            Element books=document.addElement("books");
		            //为根结点添加一个book节点
		            Element book=books.addElement("book");
		            //为book1添加name子节点
		            book.addElement("title").setText(resource.getName());
		            book.addElement("author").setText(resource.getAuthor());
		            book.addElement("isbn").setText(resource.getIsbn());
		            book.addElement("price").setText(resource.getPrice()+"");
		            book.addElement("publishDate").setText(resource.getPublishDate()+"");
		            book.addElement("publisher").setText(resource.getPublisher());
		            book.addElement("edition").setText(resource.getEdition());
		            book.addElement("pages").setText(resource.getPages()+"");
		            book.addElement("frame").setText(resource.getFrame());
		            book.addElement("format").setText(resource.getFormat());
		            book.addElement("sheet").setText(resource.getSheet()+"");
		            book.addElement("cover").setText(resource.getCover());
		            book.addElement("introduction").setText(resource.getIntroduction());
		            book.addElement("content").setText(resource.getContent());
		            Writer filewriter = new OutputStreamWriter(new FileOutputStream(new File(filePath)),"UTF-8");
		            XMLWriter xmlWriter = new XMLWriter(filewriter);
		            xmlWriter.write(document);
		            xmlWriter.close();	
					break;
				//转换成doc
				case 3:
					String doccontent = "作者： "+resource.getAuthor()+"\n"+"封面： "+resource.getCover()+"\n"+"版次： "+resource.getEdition()+"\n"+"Isbn： "+resource.getIsbn()+"\n"+"书名： "+resource.getName()+"\n"
							+"出版社： "+resource.getPublisher()+"\n"+"页数： "+resource.getPages()+"\n"+"印张： "+resource.getSheet()+"\n"+"纸书价格： "+resource.getPrice()+"\n"
							+"出版时间： "+resource.getPublishDate()+"\n"+"简介： "+resource.getIntroduction()+"\n"+"全文： "+resource.getContent();
					File docFile = new File(getUploadPath() + File.separator+ProcessQueue.DOCPATH);
					if(!docFile.exists()){
						docFile.mkdirs();
					}
					fileName = prefix+".doc";
					filePath = docFile+File.separator+fileName;
					DOCHelper.toDoc(doccontent, filePath, "UTF-8");
					break;
			}
			String name = new String(fileName.getBytes("UTF-8"), "UTF-8");
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(filePath));
			form.setMsg("转换成功");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			forwardString = "msg";
			form.setMsg((e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage());
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}
}
