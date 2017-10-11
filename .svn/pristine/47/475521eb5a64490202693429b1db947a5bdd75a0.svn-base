package com.pmpt.Impl.DaoImpl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.LoginSession;
import com.pmpt.interfaces.Dao.LoginAccoutDAOCus;

/**
* @author jianghb
*/
@Repository
public class LoginAccoutDaoImpl implements LoginAccoutDAOCus {
	
	@PersistenceContext
	protected EntityManager em;
	@Override
	/*
	 * 查询账户密码
	 */
	public String queryPwd(String phone) {
		Query query = em.createQuery("select bean.pwd from LoginAccout as bean where bean.phone=?1");
		query.setParameter(1, phone);
		Object pwdObject = null;
		String pwdStr = null;
		try {
			pwdObject = query.getSingleResult();
			pwdStr = pwdObject.toString();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return pwdStr;
		}
		return pwdStr;
	}
	/*
	 * 修改账户密码
	 */
	@Override
	@Transactional
	public int modifyPwd(String phone, String pwd) {
		Query query = em.createQuery("update LoginAccout as bean set bean.pwd=?1 where bean.phone=?2");
		query.setParameter(1, pwd);
		query.setParameter(2, phone);
		int result = 0;
		try {
			result = query.executeUpdate(); //影响的记录数
			return result;
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginAccoutDAOCus#findAccout(java.lang.String)
	 * 查询账户是否存在
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean findAccout(String phone) {
		boolean phoneStr = false;
		Query query = getEm().createNamedQuery("LoginAccout.findAccout")
				.setParameter(1, phone);
		List<Object[]> list = null;
		try {
			list = query.getResultList();
			if (list != null && !list.isEmpty()) {
				phoneStr = true;
			}
		} catch (NoResultException e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return phoneStr;
		}
		return phoneStr;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginAccoutDAOCus#signIn(java.lang.String, java.lang.String)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> signIn(String phone, String pwd) {
		Query query = getEm().createNamedQuery("LoginAccout.signIn")
				.setParameter(1, phone)
				.setParameter(2, pwd);
		List<Object[]> list = new ArrayList<>();
		try {
			list =query.getResultList();
		} catch (NoResultException e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return list;
		}
		return list;
	}
	
	public EntityManager getEm() {
		return em;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginAccoutDAOCus#updateLogSession(com.pmpt.domain.LoginSession)
	 */
	@Override
	@Transactional
	public int updateLogSession(LoginSession loginSession, String phone) {
		Query query = getEm().createNamedQuery("LoginAccout.updateLogSession")
				.setParameter(1, loginSession)
				.setParameter(2, phone);
		Integer result = 0;
		try {
			result = query.executeUpdate(); // 影响的条数
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
		return result;
	}

}
