package cn.digitalpublishing.service.impl;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.po.Word;
import cn.digitalpublishing.service.WordService;

public class WordServiceImpl extends BaseServiceImpl implements WordService {

	@Override
	public Integer getCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.WordDao").get("getCount");
		try {
			num = this.daoFacade.getWordDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "transaction.info.getCTransactionCount.error", e);
		}
		return num;
	}

	@Override
	public List<Word> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception {
		List<Word> list = null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.WordDao").get("getPagingList");
		try {
			list = this.daoFacade.getWordDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt(): "失败！", e);
		}
		return list;
	}

	@Override
	public void insert(Word obj) throws Exception {
		try {
			this.daoFacade.getWordDao().insert(obj);
	} catch (Exception e) {
		throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "插入信息出错", e);
		}
		
		
	}

	@Override
	public void delete(String id) throws Exception {
		try {
			this.daoFacade.getWordDao().delete(Word.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除用户信息失败", e);
		}
		
	}

	@Override
	public Word getId(String id) throws Exception {
		Word word = null;
		try {
			word = (Word) this.daoFacade.getWordDao().get(Word.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "查找失败", e);
		}
		return word;
	}

	@Override
	public void update(Word obj, String id, String[] properties) throws Exception {
		try {
			this.daoFacade.getWordDao().update(obj, Word.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "修改用户信息失败", e);
		}
	}

	@Override
	public void saveContent(Content content) throws Exception {
		try {
			this.daoFacade.getWordDao().insert(content);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "插入信息出错", e);
		}
	}

	@Override
	public void updateContent(Content content, String id, String[] properties) throws Exception {
		try {
			this.daoFacade.getWordDao().update(content, Content.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "修改用户信息失败", e);
		}
	}

	@Override
	public List<Content> getContent(Map<String, Object> condition, String sort) throws Exception {
		List<Content> list = null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ContentDao").get("getContent");
		try {
			list = this.daoFacade.getContentDao().getContent(condition, sort, hqlBean);
													
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt(): "获取信息列表失败！", e);
		}
		return list;
	}

	@Override
	public Content getContent(String id) throws Exception {
		Content content = null;
		try {
			content = (Content) this.daoFacade.getContentDao().get(Content.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "查找失败", e);
		}
		return content;
	}

	@Override
	public void deleteContent(String id) throws Exception {
		try {
			this.daoFacade.getContentDao().delete(Content.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除用户信息失败", e);
		}
	}

	@Override
	public Integer getContentCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ContentDao").get("getCount");
		try {
			num = this.daoFacade.getContentDao().getCount(condition, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "transaction.info.getCount.error", e);
		}
		return num;
	}

	

	
}
