package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Word;
import cn.digitalpublishing.springmvc.form.WordForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * word
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/pages/notation")
public class NotationController extends BaseController {

	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("notation/NotationList.ftl");
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
			if (!Strings.isNullOrEmpty(form.getName())) {
				condition.put("name", "%" + form.getName() + "%");
			}
			form.setiTotalRecords(this.wordService.getCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if (0 < form.getiTotalRecords()) {
				list = this.wordService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
		} catch (Exception e) {
			form.setMsg(exMsg(e));
			forwardString = "msg";
		}
		form.setAaData(list);
		return form;
	}

	/**
	 * 下载
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/form/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		try {
			Word word = this.wordService.getId(id);
			// 取得文件存储路径
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).append(File.separator).toString());
			//下载后的名字                   
			String name = new String(word.getChainedFile().getBytes("gbk"), "iso-8859-1");
			//下载
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(Path.toString()+File.separator+word.getChainedFile()));
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
	@RequestMapping("/form/editor")
	public ModelAndView edit(WordForm form) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		forwardString = "notation/NotationEdit.ftl";
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
		Word word = form.getObj();
		try {
			//删除修改前的文件
			Word w = this.wordService.getId(word.getId());
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).toString());
			File file = new File(Path+File.separator + w.getLastName());
		    if(file.exists()){
		        file.delete();
		    }
			// 当前资源的前缀命名
			String prefix = DateFormatUitl.formatString();
			if (form.getUpLoadWord() != null) {
				// word存放路径
				word.setLastName(form.getUpLoadWord().getOriginalFilename());
				String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.DOCPATH).append(File.separator).toString();
				String fileName = prefix + form.getUpLoadWord().getOriginalFilename().substring(form.getUpLoadWord().getOriginalFilename().lastIndexOf("."));
				UploadFileUtil.writeFile(filePath, fileName, form.getUpLoadWord().getBytes());
				word.setChainedFile(fileName);
				wordService.update(word, word.getId(), null);
			}
			form.setMsg("修改成功!");
			form.setIsSuccess(SUCCESS);
		}catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		} finally {
			form.setUpLoadWord(null);
		}
		return form;
	}

}
