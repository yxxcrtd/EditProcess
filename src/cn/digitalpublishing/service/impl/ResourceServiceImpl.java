package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Resource;
import cn.digitalpublishing.service.ResourceService;
import cn.digitalpublishing.util.PdfUtil;

public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService{

	@Override
	public void insert(Resource obj) throws Exception {
		try {
			this.daoFacade.getResourceDao().insert(obj);
	} catch (Exception e) {
		throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "插入信息出错", e);
		}
		
	}

	
	@Override
	public Integer getCount(Map<String, Object> map) throws Exception {
		Integer num = 0;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getCount");
		try {
			num = this.daoFacade.getResourceDao().getCount(map, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "transaction.info.getCTransactionCount.error", e);
		}
		return num;
	}


	@Override
	public List<Resource> getPagingList(Map<String,Object> condition, String sort, Integer pageCount, Integer countStart) throws Exception {
		List<Resource> list = null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getPagingList");
		try {
			list = this.daoFacade.getResourceDao().getPagingList(condition, sort, pageCount, countStart, hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt(): "失败！", e);
		}
		return list;
	}


	@Override
	public Resource getId(String id) throws Exception {
		Resource resource = null;
		try {
			resource = (Resource) this.daoFacade.getResourceDao().get(Resource.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "查找失败", e);
		}
		return resource;
	}


	@Override
	public void createPDF(String soucePath, String savePath, String enCoding) throws Exception {
		String html = PdfUtil.readFile(soucePath, enCoding);  
		PdfUtil.doConversion(html, savePath);
	}


	@Override
	public void delete(String id) throws Exception {
		
		try {
			this.daoFacade.getResourceDao().delete(Resource.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除用户信息失败", e);
		}
	}


	@Override
	public List<Resource> getIsbn(Map<String, Object> condition, String sort) throws Exception {
		List<Resource> list = null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ResourceDao").get("getIsbn");
		try {
			list = this.daoFacade.getResourceDao().getIsbn(condition, sort, hqlBean);
													
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt(): "获取信息列表失败！", e);
		}
		return list;
	}


	

}
