package cn.digitalpublishing.service;


import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Resource;


public interface ResourceService extends BaseService {
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void insert(Resource obj) throws Exception;
	
	Integer getCount(Map<String, Object> condition) throws Exception;
	
	List<Resource> getPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer countStart)throws Exception;
	
	public Resource getId(String id) throws Exception;
	
	public void createPDF(String soucePath, String savePath, String enCoding) throws Exception;
	
	void delete(String id) throws Exception;
	
	public List<Resource> getIsbn(Map<String, Object> condition, String sort) throws Exception;
}
