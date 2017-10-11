package com.pmpt.Impl.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.interfaces.Dao.TransactionsDAOCus;

/**
 * @ClassName: TransactionDAOImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月29日下午1:57:59
 */
@Repository
public class TransactionsDAOImpl implements TransactionsDAOCus {

	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> participateAuction() {
		String sql = "";
		Query query = getEm().createNativeQuery(sql);
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return list;
		}
		return list;
	}

}
