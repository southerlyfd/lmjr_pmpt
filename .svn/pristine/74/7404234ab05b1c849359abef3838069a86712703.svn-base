package com.pmpt.interfaces.Service;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.pmpt.entities.Organization;
import com.pmpt.interfaces.Dao.OrgDAO;
import com.pmpt.interfaces.Dao.OrgDAOCus;


@Service
@ComponentScan("com.pmpt.domain.org.dao")
public class OrgService {
    @Autowired
    private OrgDAO dao;
    
    @Autowired
    private OrgDAOCus dao2;
    
    @PersistenceContext
    EntityManager em;

	public Iterable<Organization> findAll() {
        return dao.findAll();
    }
    
    public void test() {
    	// Query query = em.createQuery("select la.id,la.phone,la.userId.name,la.userId.nickName,la.userId.sex from LoginAccout la");
    	Query query = em.createNativeQuery("select LoginAccout.id,LoginAccout.phone,User.name,User.nickName,User.sex from LoginAccout left join User on LoginAccout.userId = User.id");
    	List<Object[]> list = query.getResultList();
    	list.size();
    }
    
    public void save(Organization entity){
    	
    	if (entity.getParentOrg() != null&&entity.getParentOrg().getId() != null) {
    		Organization organization2 = dao.findOne(entity.getParentOrg().getId());// 取出父级id查询获得到数据
    		entity.setParentOrg(organization2);
		}
    	
    	dao.save(entity);
    }
    public void delete(Organization entity){
    	dao.delete(entity);
    }
    
    public List<Organization> findByName(String name) {
        return dao2.findByNameCus(name);
    	// return null;
    }
}