package com.pmpt.Impl.ServiceImpl;

import com.pmpt.common.PageBean;
import com.pmpt.entities.Promotion;
import com.pmpt.interfaces.Dao.PromotionRepository;
import com.pmpt.interfaces.Service.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository repository;

    @Override
	public PageBean<Promotion> findAll(int pageSize, int currentPage) {
        return repository.findAll(pageSize, currentPage);
    }

    @Override
    public List<Promotion> findIndexPromotion() {
        return repository.findIndexPromotion();
    }

    @Override
    public boolean addPromotion(Promotion promotion) {
        return repository.addPromotion(promotion);
    }

    @Override
    public boolean modifyPromotion(Promotion promotion) {
        //判断活动修改是否符合规则
        return repository.modifyPromotion(promotion);
    }

    @Override
    public boolean deletePromotion(String id) {
        //判断是否符合删除规则
        return repository.deletePromotion(id);
    }

}
