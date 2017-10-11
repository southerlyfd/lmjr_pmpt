package com.pmpt.Impl.DaoImpl;

import org.springframework.stereotype.Repository;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.Bill;
import com.pmpt.entities.Locate;
import com.pmpt.entities.Pterocarpus;
import com.pmpt.interfaces.Dao.PublishDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PublishDAOImpl implements PublishDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Locate> getAllLocate() {
        Query query = entityManager.createNativeQuery("SELECT id,name FROM rhr.tb_locate", Locate.class);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Pterocarpus> getPterocarpusByLocateId(Long id) {
        Query query = entityManager.createNativeQuery("SELECT pt.id AS id ,pt.name AS name,pt.ld_name AS ld_name FROM rhr.tb_pterocarpus pt INNER JOIN rhr.tb_locate_pterocarpus tlp ON pt.id = tlp.pid AND tlp.lid = ?", Pterocarpus.class).setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public void addBill(Bill bill) {
        entityManager.persist(bill);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> exhibitContinent() {
		String sql = "SELECT bean.id, bean.continentName\r\n" + 
				"FROM continent bean\r\n" + 
				"ORDER BY bean.code ASC";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return list;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> countrys(Integer continentId) {
		String sql = "select a.id,a.name,a.logoBuildPic \r\n" + 
				"from Country as a\r\n" + 
				"left join Continent as b on b.id = a.continentId\r\n" + 
				"where b.id = :continentId\r\n" + 
				"group by a.code asc";
		Query query = entityManager.createNativeQuery(sql)
				.setParameter("continentId", continentId);
		List<Object[]> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return list;
		}
		return list;
	}
}
