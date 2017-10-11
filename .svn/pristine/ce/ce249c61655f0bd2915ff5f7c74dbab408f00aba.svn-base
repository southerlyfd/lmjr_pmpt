/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.PageBean;
import com.pmpt.entities.LoginSession;
import com.pmpt.interfaces.Dao.PersonDAOCus;

/**
 * @ClassName: PersonDAOImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日下午2:55:07
 */
public class PersonDAOImpl implements PersonDAOCus {
	
	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.person.dao.PersonDAOCus#findAll(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageBean<Object[]> findAll(int pageSize, int currentPage) {
		PageBean<Object[]> pageBean = new PageBean<>();
		Query query1 = getEm().createNativeQuery("select count(1) from Person");
		pageBean.setCurrentPage(currentPage); // 当前页
		pageBean.setPageSize(pageSize); // 每页内容条数
		String totalStr = (String)query1.getSingleResult().toString();
		Integer total = Integer.parseInt(totalStr);
		pageBean.setTotalCount(total);
		int startLine = 0; // 总页数
		startLine = (total % pageSize == 0)? total / pageSize : (total / pageSize) + 1;
		pageBean.setTotalPage(startLine);
		int pageFirst = 0;
		pageFirst = (currentPage - 1) * pageSize;
		Query query = getEm().createNativeQuery("select p.id,p.name,p.loginName,p.englishName,p.code,p.idNumber,p.entryDate from Person as p where p.valid = 1");
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.setMaxResults(pageSize).setFirstResult(pageFirst).getResultList(); // 页面内容条数，
			pageBean.setBeanList(list);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "分页查询所有用户异常");
			return pageBean;
		}
		return pageBean;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.person.dao.PersonDAOCus#signIn(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> signIn(String loginName, String pwd) {
		Query query = getEm().createNamedQuery("PersonAvatar.signIn")
				.setParameter("loginName", loginName)
				.setParameter("pwd", pwd);
		List<Object[]> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return list;
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginAccoutDAOCus#updatePersonSession(com.pmpt.domain.LoginSession, java.lang.String)
	 */
	@Override
	@Transactional
	public int updatePersonSession(LoginSession loginSession, String loginName) {
		Query query = getEm().createNamedQuery("PersonAvatar.updatePersonSession")
				.setParameter("loginSession", loginSession)
				.setParameter("loginName", loginName);
		Integer result = 0;
		try {
			result = query.executeUpdate(); // 影响的数据数
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.person.dao.PersonDAOCus#updatePwd(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public int updatePwd(String loginName, String newPwd) {
		Query query = getEm().createNamedQuery("PersonAvatar.updatePwd")
				.setParameter("loginName", loginName)
				.setParameter("newPwd", newPwd);
		Integer result = 0;
		try {
			result = query.executeUpdate(); // 影响的数据数
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
		return result;
	}
	
}
