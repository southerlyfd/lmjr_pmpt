/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.interfaces.Dao.BillDAOCus;


/**
 * @ClassName: BillDAOCusImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月13日下午5:36:21
 */
@Repository
public class BillDAOImpl implements BillDAOCus {
	
	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	
	/*
	 * 首页秒杀信息展示
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findQuickDeal() {
		String sql = "select a.endDate z_jieshushijian,c.alias z_suming,d.picUrl z_shangpinzhutu,\r\n" + 
				"	case when a.sellerType = '1' then f.iconURL\r\n" + 
				"	else null end enterprice_iconurl,\r\n" + 
				"    case when b.id is null then a.initPrice\r\n" + 
				"    else max(b.bidAmount) end z_zuigaojias\r\n" + 
				"    from Bill a\r\n" + 
				"    left join BillBidRec as b on a.id = b.bill\r\n" + 
				"    left join Goods as c on a.id = c.billId\r\n" + 
				"    left join GoodsPic d on d.goodId=c.id and d.flag='1'\r\n" + 
				"    left join LoginAccout as e on e.id = a.sellerId\r\n" + 
				"    left join Enterprice as f on e.enterprice = f.id\r\n" + 
				"    where a.isValid='01' and a.billStatus='3'\r\n" + 
				"    group by a.id order by DATEDIFF(a.endDate, now()) asc,datediff(now(), a.startDate) asc\r\n" + 
				"	 limit 5";
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

	/* 
	 * 首页预热信息展示
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findWarmUp() {
		String sql = "select a.initPrice z_qipaijia, a.startDate z_kaishishijian, b.commodity z_suming, c.picUrl z_shangpingzhutu,\r\n" + 
				"		e.iconURL z_iconurl\r\n" + 
				"	from Bill as a\r\n" + 
				"    left join goods as b on b.billId = a.id\r\n" + 
				"    left join goodspic as c on c.goodId = b.id and c.flag = '1'\r\n" + 
				"    left join loginaccout as d on d.id = a.sellerId\r\n" + 
				"    left join enterprice as e on e.id = d.enterprice\r\n" + 
				"	where a.isValid='01' and a.billStatus='2'\r\n" + 
				"    group by a.id order by DATEDIFF(a.startDate, now()) asc limit 5";
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findInAuction(Integer billId) {
		String sql = "select c.id t_shangpingID, a.endDate as z_jieshushijian, a.billTitle as z_dingdanbiaoti,\r\n" + 
				"	a.initPrice z_qipaijia, a.ranges as z_jiajiafudu, a.pledgePrice as z_baozhengjin, a.totalPrice as z_zongjine,\r\n" + 
				"    max(b.bidAmount) as z_zuigaojia, c.videoURL as z_shipin\r\n" + 
				"from Bill as a \r\n" + 
				"	left join billBidRec as b on b.bill = a.id\r\n" + 
				"    left join goods as c on c.billId = a.id\r\n" + 
				"where a.id = :billId and a.billStatus = '3'";
		Query query = getEm().createNativeQuery(sql)
				.setParameter("billId", billId);
		List<Object[]> list = new ArrayList<>();
		try {
			list = query.getResultList();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findMainFigure(Integer goodsId, Integer flag) {
		String sql = "SELECT a.picUrl AS z_shangpingtupian\r\n" + 
				"FROM goodspic a\r\n" + 
				"WHERE a.goodId = :goodsId\r\n" + 
				"	AND CASE WHEN :flag = 3 THEN a.flag IN (3) ELSE a.flag IN (1, 2) END";
		Query query = getEm().createNativeQuery(sql)
				.setParameter("goodsId", goodsId)
				.setParameter("flag", flag);
		List<Object[]> list = new ArrayList<>();
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
	public List<Object[]> findBidRecord(Integer billId) {
		String sql = "select a.auctionNum, a.bidAmount, a.bidTime \r\n" + 
				"from billbidrec as a\r\n" + 
				"where a.bill = :billId\r\n" + 
				"order by a.bidAmount desc limit 5";
		Query query = getEm().createNativeQuery(sql)
				.setParameter("billId", billId);
		List<Object[]> list = new ArrayList<>();
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
	public List<Object[]> exhibitLike() {
		String sql = "SELECT bean.id, bean.productName, bean.interestUrl\r\n" + 
				"FROM interests bean\r\n" + 
				"ORDER BY bean.code ASC";
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

	// 首页正在热拍图片展示
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getHotAuction() {
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
	
	// 地域精选
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRegionalSelection() {
		String sql = "select a.code z_bianhao, a.logoBuildPic z_biaozhitupian, a.name z_guojia \r\n" + 
				"from country a\r\n" + 
				"group by a.code asc limit 5";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMoreSelections() {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getHotCommodity() {
		String sql = "select a.productName z_pinming, a.interestUrl z_tupian\r\n" + 
				"from interests as a order by a.code asc";
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
