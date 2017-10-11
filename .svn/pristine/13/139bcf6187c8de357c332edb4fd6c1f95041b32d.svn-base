package com.pmpt.Impl.DaoImpl;

import org.springframework.stereotype.Repository;

import com.pmpt.entities.GoodsPic;
import com.pmpt.interfaces.Dao.GoodPicDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class GoodPicDaoImpl implements GoodPicDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
	public void savePic(GoodsPic pic) {
        entityManager.persist(pic);
    }
}
