/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.PageBean;
import com.pmpt.interfaces.Dao.ManageUsersDAOCus;

/**
 * @ClassName: ManageUsersDaoImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月23日上午11:11:05
 */
@Repository
public class ManageUsersDaoImpl implements ManageUsersDAOCus {

	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	/*
	 *@param pageSize 每页内容条数
	 *@param currentPage 当前页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageBean<Object[]> findAll(int pageSize, int currentPage) {
		PageBean<Object[]> pageBean = new PageBean<>();
		Query query1 = getEm().createNativeQuery("select count(1) from LoginAccout");
		pageBean.setCurrentPage(currentPage); // 当前页
		pageBean.setPageSize(pageSize); // 每页内容条数
		String totalStr = (String)query1.getSingleResult().toString();
		Integer total = Integer.parseInt(totalStr);
		pageBean.setTotalCount(total);
		int startLine = 0; // 总页数
		startLine = (total % pageSize == 0)?  total / pageSize : (total / pageSize) + 1;
		pageBean.setTotalPage(startLine);
		int pageFirst = 0;
		pageFirst = (currentPage - 1) * pageSize;
		Query query = getEm().createNativeQuery("select log.id,log.phone,us.name,us.nickName,us.sex from LoginAccout as log left join User as us on log.user = us.id");
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.setMaxResults(pageSize).setFirstResult(pageFirst).getResultList(); // 页面内容条数，
			pageBean.setBeanList(list);
		} catch (PersistenceException e) {
			MainUtilityTools.catchException(e, this.getClass(), "分页查询所有用户异常");
			return pageBean;
		}
		return pageBean;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.manage.dao.ManageUsersDAOCus#conditionalQuery(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageBean<Object[]> conditionalQuery(String condition, int pageSize, int currentPage) {
		PageBean<Object[]> pageBean = new PageBean<>();
		Query query1 = getEm().createNativeQuery("select count(1) \r\n" + 
				"from loginaccout as log \r\n" + 
				"left join user as us \r\n" + 
				"on log.user = us.id where locate(?1,phone) > 0 or locate(?1,name) > 0 or locate(?1,nickName) > 0").setParameter(1, condition);
		pageBean.setCurrentPage(currentPage); // 当前页
		pageBean.setPageSize(pageSize); // 每页内容条数
		String totalStr = (String)query1.getSingleResult().toString();
		Integer total = Integer.parseInt(totalStr);
		pageBean.setTotalCount(total);
		int startLine = 0; // 总页数
		startLine = (total % pageSize == 0)?  total / pageSize : (total / pageSize) + 1;
		pageBean.setTotalPage(startLine);
		int pageFirst = 0;
		pageFirst = (currentPage - 1) * pageSize;
		
		Query query = getEm().createNativeQuery("select log.id,log.phone,us.name,us.nickName,us.sex \r\n" + 
				"from loginaccout as log \r\n" + 
				"left join user as us \r\n" + 
				"on log.user = us.id where locate(?1,phone) > 0 or locate(?1,name) > 0 or locate(?1,nickName) > 0").setParameter(1, condition);
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.setMaxResults(pageSize).setFirstResult(pageFirst).getResultList();
			pageBean.setBeanList(list);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "分页模糊查询用户信息异常");
			return pageBean;
		}
		return pageBean;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.manage.dao.ManageUsersDAOCus#queryUserInformation(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryUserInformation(String loginAccoutId) {
		Query query = getEm().createNativeQuery("select log.id,log.phone,us.name,us.nickName,us.sex \r\n" + 
				"from loginaccout as log \r\n" + 
				"left join user as us \r\n" + 
				"on log.user = us.id where log.id = ?1").setParameter(1, loginAccoutId);
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "查询用户信息异常");
			return list;
		}
		return list;
	}

}
