/**
 * 
 */
package com.pmpt.interfaces.Dao;

import java.util.List;

/**
 * @ClassName: PromotionDAOCus.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月13日下午5:29:01
 */
public interface BillDAOCus {
	
	// 查询首页秒杀图片展示信息
	List<Object[]> findQuickDeal();
	
	// 查询首页预热图片展示信息
	List<Object[]> findWarmUp();
	
	// 拍卖中的商品详情页信息展示
	List<Object[]> findInAuction(Integer billId);
	
	// 展示商品图片（1-主图，3-细节描述图）
	List<Object[]> findMainFigure(Integer goodsId, Integer flag);
	
	// 竞拍记录和当前最高价
	List<Object[]> findBidRecord(Integer billId);
	
	// 喜好展示——MySQL版
	List<Object[]> exhibitLike();
	
	// 正在热拍
	List<Object[]> getHotAuction();
	
	// 地域精选
	List<Object[]> getRegionalSelection();
	
	// 更多精选
	List<Object[]> getMoreSelections();
	
	// 热门品名
	List<Object[]> getHotCommodity();
}
