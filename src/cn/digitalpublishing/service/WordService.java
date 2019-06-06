package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.po.Word;

public interface WordService extends BaseService  {


	Integer getCount(Map<String, Object> condition) throws Exception;
	
	List<Word> getPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer countStart)throws Exception;
	
	public void insert(Word obj) throws Exception;
	
	void delete(String id) throws Exception;

	public Word getId(String id) throws Exception;
	
	void update(Word obj, String id, String[] properties) throws Exception;
	
	public void saveContent(Content content)throws Exception;
	
	void updateContent(Content content, String id, String[] properties) throws Exception;
	
	public List<Content> getContent(Map<String, Object> condition, String sort) throws Exception;
	
	public Content getContent(String id) throws Exception;
	
	void deleteContent(String id) throws Exception;
	
	Integer getContentCount(Map<String, Object> condition) throws Exception;
}
