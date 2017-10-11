package com.pmpt.interfaces.Service;

import java.util.List;
import java.util.Map;


public interface IndexService {
	// 首页秒杀
	List<Object[]> findQuickDeal();
	
	// 热门分类展示
	List<Map<String, String>> getClassifications();
	
	// 首页预热
	List<Object[]> findWarmUp();
	
	// 热门品名
	List<Object[]> getHotCommodity();
	
	// 正在热拍（未完成）
	List<Object[]> getHotAuction();
	
	// 地域精选
	List<Object[]> getRegionalSelection();
	
	// 更多精选
	List<Object[]> getMoreSelections();
	
	// 拍卖中的商品详情页信息展示
	Map<String, Object> findInAuction(Integer billId);
	
	// 竞拍记录和当前最高价
	Map<String, Object> findBidRecord(Integer billId);
	
}
