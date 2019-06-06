package cn.digitalpublishing.springmvc.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * Interface Controller
 */
@Controller
@RequestMapping("/pages/digital")
public class InterfaceController extends BaseController {

	/**
	 * Index
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {	
		ModelAndView mav = new ModelAndView();
		mav.addObject("scheme", request.getScheme());
		mav.addObject("serverName", request.getServerName());
		mav.addObject("port", request.getServerPort());
		mav.setViewName("interface/InterfaceList.ftl");
		return mav;
	}
	
	@RequestMapping(value = "invoke", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String invoke() throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		JSONObject obj = new JSONObject();
		obj.put("resourceList", resourceService.getPagingList(condition, "", 10, 0));
		obj.put("status", "success");
		return obj.toString();
	}

}
