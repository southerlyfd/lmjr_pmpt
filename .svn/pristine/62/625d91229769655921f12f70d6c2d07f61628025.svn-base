/**
 * 
 */
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
import com.pmpt.interfaces.Dao.LoginSessionDAOCus;

/**
 * @ClassName: LoginSessionDAOImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月17日下午4:00:03
 */
@Repository
public class LoginSessionDAOImpl implements LoginSessionDAOCus {
	@PersistenceContext
	protected EntityManager em;
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginSessionDAOCus#memoryCache(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public int memoryCache(Integer loginSession, String token, String deviceType) {
		Query query = getEm().createNamedQuery("LoginSession.update")
				.setParameter(1, token)
				.setParameter(2, deviceType)
				.setParameter(3, loginSession);
		int result = 0;
		try {
			result = query.executeUpdate(); // 影响的条数
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.loginAccout.dao.LoginSessionDAOCus#findByToken(java.lang.String)
	 * 查询token- LoginSession.findByToken
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByToken(Integer id) {
		Query query = getEm().createNamedQuery("LoginSession.findByToken")
				.setParameter(1, id);
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			result = query.getResultList();
		} catch (NoResultException e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return result;
		}
		return result;
	}
	
	public EntityManager getEm() {
		return em;
	}


}
