package com.pmpt.interfaces.Service;

import com.pmpt.common.PageBean;
import com.pmpt.entities.Promotion;

import java.util.List;

public interface PromotionService {
	PageBean<Promotion> findAll(int pageSize, int currentPage);

    List<Promotion> findIndexPromotion();

    boolean addPromotion(Promotion promotion);

    boolean modifyPromotion(Promotion promotion);

    boolean deletePromotion(String id);
}
