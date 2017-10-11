package com.pmpt.Impl.DaoImpl;

import com.mongodb.WriteResult;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.PageBean;
import com.pmpt.entities.Promotion;
import com.pmpt.interfaces.Dao.PromotionRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static Logger logger = Logger.getLogger(PromotionRepositoryImpl.class);

    @Value("${indexPromotionNum}")
    private int indexPromotionNum;

    @Override
    public PageBean<Promotion> findAll(int pageSize, int currentPage) {
        PageBean<Promotion> pageBean = new PageBean<>();
        long count = mongoTemplate.count(new Query(Criteria.where("delete").is(false)), Promotion.class);
        pageBean.setTotalCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage((count % pageSize == 0) ? count / pageSize : (count / pageSize) + 1);
        Query query = new Query(Criteria.where("delete").is(false));
        List<Promotion> promotions = mongoTemplate.find(query.skip((currentPage - 1) * pageSize).limit(pageSize), Promotion.class);
        pageBean.setBeanList(promotions);
        return pageBean;
    }

    @Override
    public List<Promotion> findIndexPromotion() {
        return mongoTemplate.find(new Query(Criteria.where("delete").is(false)).limit(indexPromotionNum).with(new Sort(Sort.Direction.DESC, "priority")).with(new Sort(Sort.Direction.DESC, "startDate")), Promotion.class);
    }

    @Override
    public boolean addPromotion(Promotion promotion) {
        try {
            mongoTemplate.insert(promotion, "promotion");
        } catch (Exception e) {
        	MainUtilityTools.catchException(e, this.getClass(), "");
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyPromotion(Promotion promotion) {
        try {
            WriteResult id = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(promotion.getId())), update(promotion, new Update()), Promotion.class);
        } catch (Exception e) {
        	MainUtilityTools.catchException(e, this.getClass(), "更新失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePromotion(String id) {
        try {
            mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().set("delete", true), Promotion.class);
        } catch (Exception e) {
        	MainUtilityTools.catchException(e, this.getClass(), "删除失败");
            return false;
        }
        return true;
    }

    private Update update(Promotion promotion, Update update) throws IllegalAccessException {
        Field[] declaredFields = promotion.getClass().getDeclaredFields();
        for (Field field : declaredFields
                ) {
        	if(!field.getName().equals("id")) {
        		update.set(field.getName(), field.get(promotion));
        	}
        }
        return update;
    }

}
