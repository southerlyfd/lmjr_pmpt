/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pmpt.entities.Organization;
import com.pmpt.interfaces.Dao.OrgDAOCus;


/**
 * @author jitl
 *
 */
@Repository
public class OrgDAOImpl implements OrgDAOCus {

	@PersistenceContext
	protected EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.xmlg.org.dao.OrgDAO#findByNameCus(java.lang.String)
	 */
	@Override
	public List<Organization> findByNameCus(String name) {
		Query query = em.createQuery("select bean from Organization bean where bean.name like ?1");
		query.setParameter(1, "%" + name + "%");
		
		List<Organization> organizations = null;
		try {
			organizations = (List<Organization>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
		
		return organizations;
	}

}
