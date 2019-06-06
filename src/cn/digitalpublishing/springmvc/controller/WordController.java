package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
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
import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.po.Word;
import cn.digitalpublishing.springmvc.form.ContentForm;
import cn.digitalpublishing.springmvc.form.WordForm;
import cn.digitalpublishing.util.DOCHelper;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * word
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/pages/gao")
public class WordController extends BaseController {

	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("word/WordList.ftl");
	}

	/**
	 * word列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	public WordForm manager(WordForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Word> list = new ArrayList<Word>();

		try {
			if (!Strings.isNullOrEmpty(form.getTitle())) {
				condition.put("title", "%" + form.getTitle() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getName())) {
				condition.put("name", "%" + form.getName() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getPhoto())) {
				condition.put("photo", "%" + form.getPhoto() + "%");
			}
			form.setiTotalRecords(this.wordService.getCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if (0 < form.getiTotalRecords()) {
				list = this.wordService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
				for (Word word : list) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("contenid", word.getId());
					int num = this.wordService.getContentCount(map);
					word.setContenNum(num);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			form.setMsg(exMsg(e));
			forwardString = "msg";
		}
		form.setAaData(list);
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
	public WordForm delete(WordForm form) throws Exception {
		String id = request.getParameter("id");
		try {
			this.wordService.delete(id);
			form.setMsg("删除成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg("删除出错！");
		}
		return form;
	}
	
	/**
	 * 下载
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/form/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String prefix = DateFormatUitl.formatString();
		String id = request.getParameter("id");
		String filePath="";
		String fileName = "";
		try {
			Word word = this.wordService.getId(id);
			condition.put("objectid", id);
			List<Content> listcontent = this.wordService.getContent(condition, "");
			// 取得文件存储路径
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).toString());
			if(listcontent.get(0).getContent()!=null){
				File docFile = new File(getUploadPath() + File.separator+ProcessQueue.DOCPATH);
				if(!docFile.exists()){
					docFile.mkdirs();
				}
				fileName = prefix+".doc";
				filePath = docFile+File.separator+fileName;
				//去除样式
				String regEx_html = "<[^>]+>";
				Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		        Matcher m_html = p_html.matcher(listcontent.get(0).getContent());
		        String content = m_html.replaceAll("");
				DOCHelper.toDoc(content, filePath, "UTF-8");
				word.setLastName(fileName);
				this.wordService.update(word, word.getId(), null);
				//下载后的名字
				String name = new String(word.getName().getBytes("gbk"), "iso-8859-1");
				//下载
				header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
				header.set("Content-Disposition", "attachment; filename=" + name);
				data = FileUtils.readFileToByteArray(new File(Path.toString()+File.separator+word.getLastName()));
			}else{
				//下载后的名字
				String name = new String(word.getName().getBytes("gbk"), "iso-8859-1");
				//下载
				header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
				header.set("Content-Disposition", "attachment; filename=" + name);
				data = FileUtils.readFileToByteArray(new File(Path.toString()+File.separator+word.getChainedFile()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}
	
	/**
	 * 下载
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/form/loadphoto")
	public ResponseEntity<byte[]> downloadphoto() throws Exception {
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		try {
			Word word = this.wordService.getId(id);
			// 取得文件存储路径
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.IMAGE).toString());
			//下载后的名字
			String name = new String(word.getPhoto().getBytes("gbk"), "iso-8859-1");
			//下载
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(Path.toString()+File.separator+word.getPhotoLink()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}
	
	/**
	 * 显示 页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/add")
	public ModelAndView edit(WordForm form) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		forwardString = "word/WordEdit.ftl";
		try {
			if (null != form.getId() && !"".equals(form.getId())) {
				Word word = this.wordService.getId(form.getId());
				form.setObj(word);
			}
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		map.put("form", form);
		return new ModelAndView(forwardString, map);
	}

	/**
	 * 上传
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/form/addftl")
	public WordForm editftl(WordForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		Word word = form.getObj();
		try {
			// 当前资源的前缀命名
			String prefix = DateFormatUitl.formatString();
			if (form.getUpLoadPhoto() != null) {
				// 图片存放路径
				String photoPath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.IMAGE).append(File.separator).toString();
				// 获取图片名称
				String photoName = prefix + form.getUpLoadPhoto().getOriginalFilename().substring(form.getUpLoadPhoto().getOriginalFilename().lastIndexOf("."));
				UploadFileUtil.writeFile(photoPath, photoName, form.getUpLoadPhoto().getBytes());
				word.setPhoto(form.getUpLoadPhoto().getOriginalFilename());
				word.setPhotoLink(photoName);
			}
			if (form.getUpLoadWord() != null) {
				// word存放路径
				String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).append(File.separator).toString();
				String fileName = prefix + form.getUpLoadWord().getOriginalFilename().substring(form.getUpLoadWord().getOriginalFilename().lastIndexOf("."));
				UploadFileUtil.writeFile(filePath, fileName, form.getUpLoadWord().getBytes());
				word.setChainedFile(fileName);
				word.setName(form.getUpLoadWord().getOriginalFilename());
			}
			String formTitle = word.getTitle();
			if (!Strings.isNullOrEmpty(formTitle)) {
				condition.put("title", formTitle);
			}
			List<Word> list = this.wordService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			if (list.size() > 0) {
				String iid = list.get(0).getId();
				if ((word.getId()).equals(iid)) {
					this.wordService.update(word, word.getId(), null);
					form.setMsg("修改成功");
					form.setIsSuccess(SUCCESS);
				} else {
					form.setMsg("标题不能重复!");
					form.setIsSuccess(SUCCESS);
				}
			} else {
				if (word.getId() != null&&!"".equals(word.getId())) {
					this.wordService.update(word, word.getId(), null);
					form.setMsg("修改成功");
					form.setIsSuccess(SUCCESS);
				} else {
					wordService.insert(word);
					form.setMsg("添加成功!");
					form.setIsSuccess(SUCCESS);
				}
			}
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		} finally {
			form.setUpLoadPhoto(null);
			form.setUpLoadWord(null);
		}
		return form;
	}

/**
	 * 修改页
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/edit")
	public ModelAndView uedit(ContentForm form) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		forwardString = "word/WordFileEdit.ftl";
		try {
			if (null != form.getId()) {
				map.put("objectid", form.getId());
				List<Content> contentLists = this.wordService.getContent(map, "");
				if(contentLists!=null&&contentLists.size()>0){
					List<Content> contentList = this.wordService.getContent(map, "");
					if(contentList!=null&&contentList.size()>0){
						form.setObj(contentList.get(0));
						
					}
				}else{
					Word word = this.wordService.getId(form.getId());
					Content content = new Content();
					
					String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).append(File.separator)+word.getChainedFile().toString();
					File f = new File(filePath); 
					FileInputStream fis = new FileInputStream(f);     
			        HWPFDocument doc = new HWPFDocument(fis);     
			        Range rang = doc.getRange();     
			        String text = rang.text();     
			        fis.close(); 
			        
			        content.setHid(form.getId());
			        content.setContent(text);
					wordService.saveContent(content);
					List<Content> contentList = this.wordService.getContent(map, "");
					if(contentList!=null&&contentList.size()>0){
						form.setObj(contentList.get(0));
						
					}
				}
				
				form.setWordId(form.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			form.setMsg(exMsg(e));
			form.setMsg((e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage());
		}
		map.put("form", form);
		return new ModelAndView(forwardString, map);
	}

	/**
	 * Save
	 * 
	 * @param content
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/save/word")
	public ModelAndView save(ContentForm form )throws Exception {
		Content content =form.getObj();
		String wordId = form.getWordId();
		try {
			
			Map<String, Object> condition = new HashMap<String, Object>();
			if(form.getObj().getContent()!=null){
				condition.put("content", new String(form.getObj().getContent().getBytes("ISO8859_1"), "UTF-8"));
			}
			List<Content> contentlist= this.wordService.getContent(condition, "");
			if (contentlist.size() >0) {
				form.setMsg("文本已存在");
				form.setIsSuccess(SUCCESS);
			} else {
				Map<String, Object> conditionmap = new HashMap<String, Object>();
				conditionmap.put("objectid", form.getWordId());
				List<Content> listid= this.wordService.getContent(conditionmap, "");
				
				if(listid.size()>0){
					for (Content con:listid) {
						this.wordService.deleteContent(con.getId());
					}
					content.setContent(new String(content.getContent().getBytes("ISO8859_1"), "UTF-8"));
					content.setHid(wordId);
					wordService.saveContent(content);
					form.setMsg("保存成功");
					form.setIsSuccess(SUCCESS);
				}else{
					content.setContent(new String(content.getContent().getBytes("ISO8859_1"), "UTF-8"));
					content.setHid(wordId);
					wordService.saveContent(content);
					form.setMsg("保存成功");
					form.setIsSuccess(SUCCESS);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/pages/gao/form/index");
	}
	
	@RequestMapping("/save/html")
	public ResponseEntity<byte[]> downHtml(ContentForm form) throws Exception {
		byte[] data = null;
		Map<String, Object> condition= new HashMap<String, Object>();
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		String filePath="";
		String fileName = "";
		try {
			Word word = this.wordService.getId(id);
			condition.put("objectid", id);
			List<Content> listcontent = this.wordService.getContent(condition, "");
			if(listcontent.get(0).getContent()!=null){
				File docFile = new File(getUploadPath() + File.separator+ProcessQueue.DOCPATH);
				if(!docFile.exists()){
					docFile.mkdirs();
				}
				fileName = word.getTitle()+".doc";
				filePath = docFile+File.separator+fileName;
			//去除样式	
			String regEx_html = "<[^>]+>";
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(listcontent.get(0).getContent());
	        String content = m_html.replaceAll("");
			DOCHelper.toDoc(content, filePath, "UTF-8");
			}
			String name = new String(fileName.getBytes("gbk"), "iso-8859-1");
			//下载
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(filePath));
			form.setMsg("导出成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			form.setIsSuccess(FAILURE);
			forwardString = "msg";
			form.setMsg((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}
}