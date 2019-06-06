package cn.digitalpublishing.facade;

import cn.digitalpublishing.dao.ContentDao;
import cn.digitalpublishing.dao.ResourceDao;
import cn.digitalpublishing.dao.WordDao;

/**
 * @author Stone
 */
public class DaoFacade {

	
	/**
	 * 原数据
	 */
	ResourceDao resourceDao;
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	/**
	 * word
	 */
	WordDao wordDao;

	public WordDao getWordDao() {
		return wordDao;
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	ContentDao contentDao;

	public ContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}
	
	
	
}