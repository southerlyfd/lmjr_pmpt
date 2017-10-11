package com.pmpt.Impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.Address;
import com.pmpt.entities.Bill;
import com.pmpt.entities.Goods;
import com.pmpt.entities.Interest;
import com.pmpt.entities.LoginAccout;
import com.pmpt.entities.MyTrack;
import com.pmpt.entities.Transport;
import com.pmpt.entities.User;
import com.pmpt.entities.enums.DefaultAddr;
import com.pmpt.entities.enums.Status;
import com.pmpt.interfaces.Dao.HomeDAO;
import com.pmpt.interfaces.Dao.HomeDAOCus;
import com.pmpt.interfaces.Service.HomeService;

/**
 * @ClassName: HomeServiceImpl
 * @Description: TODO
 * @author: 汪洋
 * @date: 2017年8月10日 下午5:54:27
 */
@Service
@ComponentScan("com.pmpt.domain.home.dao")
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeDAO homeDAO;

	@Autowired
	HomeDAOCus homeDAOCus;

	/**
	 * 新增收货地址
	 * 
	 * @param loginAcId
	 * @param address
	 */
	public void save(Integer loginAcId, Address address) {
		List<Address> addrList = null;
		LoginAccout loginAc = homeDAO.findById(loginAcId);
		if (loginAc.getAddr() == null) {
			addrList = new ArrayList<>();
			addrList.add(address);
			loginAc.setAddr(addrList);
		} else {
			loginAc.getAddr().add(address);
		}
		homeDAOCus.save(loginAc);
	}

	/**
	 * 修改收货地址
	 * 
	 * @param id
	 * @param name
	 * @param mobile
	 * @param province
	 * @param detail
	 * @return
	 */
	public void updateAddr(Integer id, String name, String mobile, String province, String detail) {
		homeDAOCus.updateAddr(id, name, mobile, province, detail);
	}

	/**
	 * 删除收货地址
	 * 
	 * @param id
	 */
	public void delAddr(Integer id) {
		homeDAOCus.delAddr(id);
	}

	/**
	 * 设置默认收货地址
	 * 
	 * @param loginAcId
	 * @param id
	 */
	public void setDefaultAddr(Integer loginAcId, Integer id) {
		LoginAccout loginAc = homeDAO.findById(loginAcId);
		Iterator<Address> it = loginAc.getAddr().iterator();
		while (it.hasNext()) {
			Address address = it.next();
			address.setDefaultAddr(DefaultAddr.NOTDEFAULTADDR.getKey());
			if (address.getId() == id) {
				address.setDefaultAddr(DefaultAddr.ISDEFAULTADDR.getKey());
			}
		}
		homeDAOCus.update(loginAc);
	}

	/**
	 * 根据ID查询收货地址-修改
	 * 
	 * @param id
	 */
	public Map<String, Object> findAddrById(Integer id) {
		Map<String, Object> dataMap = null;
		Address address = homeDAOCus.findAddrById(id);
		if (address != null) {
			dataMap = new HashMap<>();
			dataMap.put("id", address.getId());
			dataMap.put("name", address.getaName());
			dataMap.put("mobile", address.getMobile());
			dataMap.put("province", address.getProvince());
			dataMap.put("detail", address.getDetail());
			dataMap.put("defaultAddr", address.getDefaultAddr());
		}
		return dataMap;
	}

	/**
	 * 查询收货地址
	 * 
	 * @param loginAcId
	 */
	public List<Object> findAddrByLoginAcId(Integer loginAcId) {
		return homeDAOCus.findAddrByLoginAcId(loginAcId);
	}

	/**
	 * 根据ID查找用户个人信息
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> findLoginInfo(Integer id) {
		Map<String, Object> dataMap = new HashMap<>();
		Object[] objects = homeDAOCus.findLoginInfo(id);
		if (objects.length != 0) {
			dataMap.put("nickName", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("picUrl", objects[1] != null ? objects[1].toString() : null);
			dataMap.put("personalValid", objects[2] != null ? objects[2].toString() : null);
			dataMap.put("enterpriceValid", objects[3] != null ? objects[3].toString() : null);
		}
		return dataMap;
	}

	/**
	 * 根据账户ID查询我的模块
	 * 
	 * @param loginAcId
	 * @return
	 */
	public Map<String, Object> findMeByLoginAcId(Integer loginAcId) {
		Map<String, Object> dataMap = new HashMap<>();
		Object[] objects = homeDAOCus.findMeByLoginAcId(loginAcId);
		if (objects.length != 0) {
			dataMap.put("picUrl", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("nickName", objects[1] != null ? objects[1].toString() : null);
			dataMap.put("buyerIds", objects[2] != null ? objects[2].toString() : null);
			dataMap.put("sellerIds", objects[3] != null ? objects[3].toString() : null);
		}
		return dataMap;
	}

	/**
	 * 修改昵称
	 * 
	 * @param nickName
	 * @param id
	 */
	public void changeNickName(String nickName, Integer id) {
		homeDAOCus.changeNickName(nickName, id);
	}

	/**
	 * 修改头像
	 * 
	 * @param fileURl
	 * @param id
	 */
	public void changeHeadImg(String fileURl, Integer id) {
		homeDAOCus.changeHeadImg(fileURl, id);
	}

	/**
	 * 查询我的收藏
	 * 
	 * @param loginAcId
	 * @param billStatus
	 * @return
	 */
	public List<Map<String, Object>> findCollectBillsById(Integer loginAcId, String billStatus) {
		List<Map<String, Object>> dataList = null;
		Map<String, Object> map = null;
		List<String> statusList = new ArrayList<>();
		if ("1".equals(billStatus)) {
			// 等待拍卖
			statusList.add(Status.READYSELL.getKey());
		} else if ("2".equals(billStatus)) {
			// 正在拍
			statusList.add(Status.SELLING.getKey());
		} else if ("3".equals(billStatus)) {
			// 已拍完
			statusList.add(Status.DELIVERY.getKey());
			statusList.add(Status.NOSELLING.getKey());
			statusList.add(Status.TAKEDELIVERY.getKey());
			statusList.add(Status.REFUNDING.getKey());
			statusList.add(Status.REFUNDED.getKey());
		} else {
			statusList.add(Status.READYSELL.getKey());
		}
		// 根据账户ID查询收藏订单
		List<Bill> billList = homeDAOCus.findCollectBills(loginAcId);
		if (billList != null) {
			dataList = new ArrayList<>();
			for (Bill bill : billList) {
				if (statusList.contains(bill.getBillStatus())) {
					map = new HashMap<>();
					List<String> picList = findFirstPicByBillId(bill.getId());
					if (!picList.isEmpty() && picList != null) {
						map.put("goodsPic", picList.get(0));
					}
					map.put("billNo", bill.getBillNo());
					map.put("billTitle", bill.getBillTitle());
					map.put("initPrice", bill.getInitPrice());
					map.put("unit", bill.getUnit());
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	/**
	 * 查询订单商品首个图片
	 * 
	 * @param billId
	 * @return
	 */
	public List<String> findFirstPicByBillId(Integer billId) {
		return homeDAOCus.findFirstPicByBillId(billId);
	}

	/**
	 * 删除收藏/提醒
	 * 
	 * @param billId
	 */
	public void delBillById(Integer billId) {
		homeDAOCus.delBillById(billId);
	}

	/**
	 * 查询买过的订单列表
	 * 
	 * @param loginAcId
	 * @param billStatus
	 * @return
	 */
	public List<Map<String, Object>> findBoughtBillsById(Integer loginAcId, String billStatus) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<String> statusList = new ArrayList<>();
		if ("1".equals(billStatus)) {
			// 拍卖中
			statusList.add(Status.SELLING.getKey());
		} else if ("2".equals(billStatus)) {
			// 待付款
			statusList.add(Status.NOPAY.getKey());
		} else if ("3".equals(billStatus)) {
			// 待收货
			statusList.add(Status.PAY.getKey());
		} else if ("4".equals(billStatus)) {
			// 已结束
			statusList.add(Status.TAKEDELIVERY.getKey());
			statusList.add(Status.REFUNDING.getKey());
			statusList.add(Status.REFUNDED.getKey());
		} else {
			statusList.add(Status.SELLING.getKey());
		}
		List<Object[]> billList = homeDAOCus.findBoughtBillsById(loginAcId, statusList);
		if (billList != null) {
			dataList = new ArrayList<>();
			Map<String, Object> countMap = new HashMap<>();
			countMap.put("count", billList.size());
			dataList.add(countMap);
			for (Object[] object : billList) {
				Map<String, Object> map = new HashMap<>();
				Integer billId = Integer.valueOf(object[0].toString());
				List<String> picList = findFirstPicByBillId(billId);
				if (!picList.isEmpty() && picList != null) {
					map.put("goodsPic", picList.get(0));
				}
				map.put("billNo", object[1] != null ? object[1].toString() : null);
				map.put("billTitle", object[2] != null ? object[2].toString() : null);
				map.put("initPrice", object[3] != null ? object[3].toString() : "0.00");
				map.put("unit", object[4] != null ? object[4].toString() : null);
				dataList.add(map);
			}
		}
		return dataList;
	}

	/**
	 * 查询发布的订单列表
	 * 
	 * @param loginAcId
	 * @param billStatus
	 * @return
	 */
	public List<Map<String, Object>> findPubBillsById(Integer loginAcId, String billStatus) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<String> statusList = new ArrayList<>();
		if ("1".equals(billStatus)) {
			// 待发布
			statusList.add(Status.SUCCPUB.getKey());
		} else if ("2".equals(billStatus)) {
			// 已发布
			statusList.add(Status.READYSELL.getKey());
			statusList.add(Status.SELLING.getKey());
		} else if ("3".equals(billStatus)) {
			// 待发货
			statusList.add(Status.RDELIVERY.getKey());
		} else if ("4".equals(billStatus)) {
			// 已结束
			statusList.add(Status.PASSIN.getKey());
			statusList.add(Status.DELIVERY.getKey());
		} else {
			statusList.add(Status.SUCCPUB.getKey());
		}
		List<Object[]> billList = homeDAOCus.findPubBillsById(loginAcId, statusList);
		if (billList != null) {
			dataList = new ArrayList<>();
			Map<String, Object> countMap = new HashMap<>();
			countMap.put("count", billList.size());
			dataList.add(countMap);
			for (Object[] object : billList) {
				Map<String, Object> map = new HashMap<>();
				Integer billId = Integer.valueOf(object[0].toString());
				List<String> picList = findFirstPicByBillId(billId);
				if (!picList.isEmpty() && picList != null) {
					map.put("goodsPic", picList.get(0));
				}
				map.put("billNo", object[1] != null ? object[1].toString() : null);
				map.put("billTitle", object[2] != null ? object[2].toString() : null);
				map.put("initPrice", object[3] != null ? object[3].toString() : "0.00");
				map.put("unit", object[4] != null ? object[4].toString() : null);
				dataList.add(map);
			}
		}
		return dataList;
	}

	/**
	 * 查询我的提醒参拍订单列表
	 * 
	 * @param loginAcId
	 * @param billStatus
	 * @return
	 */
	public List<Map<String, Object>> findRemBillsById(Integer loginAcId, String billStatus) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> map = null;
		List<String> statusList = new ArrayList<>();
		if ("1".equals(billStatus)) {
			// 即将开拍
			statusList.add(Status.READYSELL.getKey());
		} else if ("2".equals(billStatus)) {
			// 已开拍
			statusList.add(Status.SELLING.getKey());
		} else if ("3".equals(billStatus)) {
			// 已结束
			statusList.add(Status.DELIVERY.getKey());
			statusList.add(Status.NOSELLING.getKey());
			statusList.add(Status.TAKEDELIVERY.getKey());
			statusList.add(Status.REFUNDING.getKey());
			statusList.add(Status.REFUNDED.getKey());
		} else {
			statusList.add(Status.READYSELL.getKey());
		}
		// 根据账户ID查询提醒订单
		List<Bill> billList = homeDAOCus.findRemindBills(loginAcId);
		if (billList != null) {
			dataList = new ArrayList<>();
			for (Bill bill : billList) {
				if (statusList.contains(bill.getBillStatus())) {
					map = new HashMap<>();
					List<String> picList = findFirstPicByBillId(bill.getId());
					if (!picList.isEmpty() && picList != null) {
						map.put("goodsPic", picList.get(0));
					}
					map.put("billNo", bill.getBillNo());
					map.put("billTitle", bill.getBillTitle());
					map.put("initPrice", bill.getInitPrice());
					map.put("unit", bill.getUnit());
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	/**
	 * 根据商品ID查询商品
	 * 
	 * @param goodsId
	 * @return
	 */
	public Goods findGoodsById(Integer goodsId) {
		return homeDAOCus.findGoodsById(goodsId);
	}

	/**
	 * 收藏
	 * 
	 * @param loginAcId
	 * @param goodId
	 */
	public void collect(Integer loginAcId, Integer goodId) {
		// 获取账户信息
		LoginAccout loginAc = homeDAOCus.findAccountInfoById(loginAcId);
		// 根据商品ID查询订单编号
		Integer billId = homeDAOCus.findBillByGId(goodId);
		Bill bill = homeDAOCus.findInfoByBillId(billId);
		if (bill.getLoginAccouts().isEmpty() || bill.getLoginAccouts() == null) {
			List<LoginAccout> LoginAcList = new ArrayList<>();
			LoginAcList.add(loginAc);
			bill.setLoginAccouts(LoginAcList);
		} else {
			bill.getLoginAccouts().add(loginAc);
		}
		homeDAOCus.collect(bill);
	}

	/**
	 * 提醒
	 * 
	 * @param loginAcId
	 * @param goodId
	 */
	public void remind(Integer loginAcId, Integer goodId) {
		// 获取账户信息
		LoginAccout loginAc = homeDAOCus.findAccountInfoById(loginAcId);
		// 根据商品ID查询订单编号
		Integer billId = homeDAOCus.findBillByGId(goodId);
		Bill bill = homeDAOCus.findInfoByBillId(billId);
		if (bill.getRemindAccouts().isEmpty() || bill.getRemindAccouts() == null) {
			List<LoginAccout> LoginAcList = new ArrayList<>();
			LoginAcList.add(loginAc);
			bill.setRemindAccouts(LoginAcList);
		} else {
			bill.getRemindAccouts().add(loginAc);
		}
		homeDAOCus.remind(bill);
	}

	/**
	 * 发货-查询发货订单
	 * 
	 * @param billId
	 * @return
	 */
	public Map<String, Object> findSGoodsBill(Integer billId) {
		Map<String, Object> dataMap = new HashMap<>();
		Object[] objects = homeDAOCus.findSGoodsBill(billId);
		if (objects.length != 0) {
			dataMap.put("billNo", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("billTitle", objects[1] != null ? objects[1].toString() : null);
			dataMap.put("currentPrice", objects[2] != null ? objects[2].toString() : "0.00");
			dataMap.put("unit", objects[3] != null ? objects[3].toString() : null);
			dataMap.put("pledgePrice", objects[4] != null ? objects[4].toString() : "0.00");
			dataMap.put("finalPrice", objects[5] != null ? objects[5].toString() : "0.00");
			dataMap.put("gCount", objects[6] != null ? objects[6].toString() : "0");
			dataMap.put("aName", objects[7] != null ? objects[7].toString() : null);
			dataMap.put("mobile", objects[8] != null ? objects[8].toString() : null);
			dataMap.put("detailAddr", objects[9] != null ? objects[9].toString() : null);
			dataMap.put("levMsg", objects[10] != null ? objects[10].toString() : "无");

			// 小数两位变一位 后续需要修改
			BigDecimal pledgePrice = BigDecimal.valueOf(Double.valueOf(dataMap.get("pledgePrice").toString()));
			BigDecimal finalPrice = BigDecimal.valueOf(Double.valueOf(dataMap.get("finalPrice").toString()));
			dataMap.put("totalPrice", pledgePrice.add(finalPrice));
		}
		return dataMap;
	}

	/**
	 * 根据订单ID查询订单信息
	 * 
	 * @param billId
	 * @return
	 */
	public Bill findInfoByBillId(Integer billId) {
		return homeDAOCus.findInfoByBillId(billId);
	}

	/**
	 * 确认发货
	 * 
	 * @param billId
	 * @param transportList
	 * @return
	 */
	public void sendGoods(Integer billId, List<Transport> transportList) {
		Bill bill = homeDAOCus.findInfoByBillId(billId);
		if (bill.getTransport().isEmpty() || bill.getTransport() == null) {
			bill.setTransport(transportList);
		} else {
			bill.getTransport().addAll(transportList);
		}
		homeDAOCus.sendGoods(bill);
	}

	/**
	 * 根据订单ID查询货车送货信息
	 * 
	 * @param billId
	 * @return
	 */
	public List<Transport> findTransportByBillId(Integer billId) {
		return homeDAOCus.findTransportByBillId(billId);
	}

	/**
	 * 查询确认付款订单(付款前/付款后)
	 * 
	 * @param billId
	 * @return
	 */
	public Map<String, Object> findPayBillById(Integer billId) {
		Map<String, Object> dataMap = new HashMap<>();
		Object[] objects = homeDAOCus.findPayBillById(billId);
		if (objects.length != 0) {
			dataMap.put("billNo", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("billTitle", objects[1] != null ? objects[1].toString() : null);
			dataMap.put("currentPrice", objects[2] != null ? objects[2].toString() : "0.00");
			dataMap.put("unit", objects[3] != null ? objects[3].toString() : null);
			dataMap.put("pledgePrice", objects[4] != null ? objects[4].toString() : "0.00");
			dataMap.put("finalPrice", objects[5] != null ? objects[5].toString() : "0.00");
			dataMap.put("gCount", objects[6] != null ? objects[6].toString() : "0");
			dataMap.put("commPrice", objects[7] != null ? objects[7].toString() : "0.00");
			dataMap.put("aName", objects[8] != null ? objects[8].toString() : null);
			dataMap.put("mobile", objects[9] != null ? objects[9].toString() : null);
			dataMap.put("detailAddr", objects[10] != null ? objects[10].toString() : null);
			dataMap.put("levMsg", objects[11] != null ? objects[11].toString() : "无");

			// 小数两位变一位 后续需要修改
			BigDecimal pledgePrice = BigDecimal.valueOf(Double.valueOf(dataMap.get("pledgePrice").toString()));
			BigDecimal finalPrice = BigDecimal.valueOf(Double.valueOf(dataMap.get("finalPrice").toString()));
			BigDecimal commPrice = BigDecimal.valueOf(Double.valueOf(dataMap.get("commPrice").toString()));
			dataMap.put("totalPrice", pledgePrice.add(finalPrice).add(commPrice));
		}
		return dataMap;
	}

	/**
	 * 更改订单状态
	 * 
	 * @param status
	 * @param billId
	 */
	public void updateBillStatus(String status, Integer billId) {
		homeDAOCus.updateBillStatus(status, billId);
	}

	/**
	 * 商品详情页面查询
	 * 
	 * @param goodId
	 * @param loginAcId
	 * @return
	 */
	public Map<String, Object> findGoodsInfo(Integer goodId, Integer loginAcId) {
		Map<String, Object> dataMap = null;
		Object[] objects = homeDAOCus.findGoodsInfo(goodId);
		if (objects.length != 0) {
			dataMap = new HashMap<>();
			dataMap.put("billStatus", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("billTitle", objects[1] != null ? objects[1].toString() : null);
			dataMap.put("initPrice", objects[2] != null ? objects[2].toString() : "0.00");
			dataMap.put("ranges", objects[3] != null ? objects[3].toString() : "0.00");
			dataMap.put("pledgePrice", objects[4] != null ? objects[4].toString() : "0.00");
			dataMap.put("currentPrice", objects[5] != null ? objects[5].toString() : "0.00");
			dataMap.put("goodsDes", objects[6] != null ? objects[6].toString() : null);
			dataMap.put("spec", objects[7] != null ? objects[7].toString() : null);
			dataMap.put("unit", objects[8] != null ? objects[8].toString() : null);
			dataMap.put("production", objects[9] != null ? objects[9].toString() : null);
			dataMap.put("quality", objects[10] != null ? objects[10].toString() : null);
			dataMap.put("commodity", objects[11] != null ? objects[11].toString() : null);
			dataMap.put("sellerId", objects[12] != null ? objects[12].toString() : null);
			dataMap.put("billId", objects[13] != null ? objects[13].toString() : null);

			if (!"".equals(dataMap.get("sellerId")) && dataMap.get("sellerId") != null) {
				Object[] sellerInfo = homeDAOCus.findMeByLoginAcId(Integer.valueOf(dataMap.get("sellerId").toString()));
				dataMap.put("photo", sellerInfo[0] != null ? sellerInfo[0].toString() : null);
				dataMap.put("name", sellerInfo[1] != null ? sellerInfo[1].toString() : null);
				dataMap.put("pubNum", sellerInfo[3] != null ? sellerInfo[3].toString() : null);
			}
			if (!"".equals(dataMap.get("billId")) && dataMap.get("billId") != null) {
				List<String> goodsPics = findFirstPicByBillId(Integer.valueOf(dataMap.get("billId").toString()));
				dataMap.put("goodsPics", goodsPics);
				// 记录我的足迹
				MyTrack myTrack = homeDAOCus.isExistTrack(Integer.valueOf(dataMap.get("billId").toString()), loginAcId);
				if (myTrack == null) {
					homeDAOCus.addTrack(new MyTrack(MainUtilityTools.getToken(),
							Integer.valueOf(dataMap.get("billId").toString()), loginAcId));
				}
			}
		}
		return dataMap;
	}

	/**
	 * 个人用户主页查询
	 * 
	 * @param loginAcId
	 * @return
	 */
	public List<Map<String, Object>> findLoginAcHomeById(Integer loginAcId) {
		Map<String, Object> dataMap = null;
		Map<String, Object> loginAcMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Object[] loginAcInfo = homeDAOCus.findMeByLoginAcId(loginAcId);
		if (loginAcInfo.length != 0) {
			loginAcMap.put("picUrl", loginAcInfo[0] != null ? loginAcInfo[0].toString() : null);
			loginAcMap.put("nickName", loginAcInfo[1] != null ? loginAcInfo[1].toString() : null);
			// TODO 后续添加认证信息
			dataList.add(loginAcMap);
		}
		List<Object[]> billList = homeDAOCus.findLoginAcHomeById(loginAcId);
		if (billList != null) {
			for (Object[] object : billList) {
				dataMap = new HashMap<>();
				Integer billId = Integer.valueOf(object[0].toString());
				List<String> picList = findFirstPicByBillId(billId);
				if (!picList.isEmpty() && picList != null) {
					dataMap.put("goodsPic", picList.get(0));
				}
				dataMap.put("billTitle", object[1] != null ? object[1].toString() : null);
				dataMap.put("currentPrice", object[2] != null ? object[2].toString() : "0.00");
				dataMap.put("unit", object[3] != null ? object[3].toString() : null);
				// TODO 倒计时后续加
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	/**
	 * 关注
	 * 
	 * @param loginAcId
	 * @param fLoginAcId
	 */
	public void myFollowed(Integer loginAcId, Integer fLoginAcId) {
		List<LoginAccout> loginAcList = null;
		LoginAccout loginAc = homeDAO.findById(loginAcId);
		LoginAccout followloginAc = homeDAO.findById(fLoginAcId);
		if (loginAc.getLoginAcList().isEmpty() || loginAc.getLoginAcList() == null) {
			loginAcList = new ArrayList<>();
			loginAcList.add(followloginAc);
			loginAc.setLoginAcList(loginAcList);
		} else {
			loginAc.getLoginAcList().add(followloginAc);
		}
		homeDAOCus.save(loginAc);
	}

	/**
	 * 查询我的关注
	 * 
	 * @param loginAcId
	 * @return
	 */
	public List<Map<String, Object>> findMyFollowed(Integer loginAcId) {
		Map<String, Object> dataMap = null;
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<LoginAccout> myFollowedInfo = homeDAOCus.findMyFollowed(loginAcId);
		if (myFollowedInfo != null) {
			for (LoginAccout loginAc : myFollowedInfo) {
				dataMap = new HashMap<>();
				dataMap.put("fLoginAcId", loginAc.getId());
				Object[] objects = homeDAOCus.findLoginInfo(loginAcId);
				if (objects.length != 0) {
					dataMap.put("nickName", objects[0] != null ? objects[0].toString() : null);
					dataMap.put("picUrl", objects[1] != null ? objects[1].toString() : null);
				}
				dataMap.put("pubNum", loginAc.getSellerBills().size());
				dataMap.put("isFollow", "取消关注");
				// TODO 后续添加认证信息
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	/**
	 * 取消关注
	 * 
	 * @param loginAcId
	 * @param fLoginAcId
	 */
	public void cancelFollowed(Integer loginAcId, Integer fLoginAcId) {
		LoginAccout loginAc = homeDAO.findById(loginAcId);
		List<LoginAccout> myFollowedInfo = homeDAOCus.findMyFollowed(loginAcId);
		if (myFollowedInfo != null) {
			Iterator<LoginAccout> iterator = myFollowedInfo.iterator();
			while (iterator.hasNext()) {
				LoginAccout fLoginAc = iterator.next();
				if (fLoginAcId == fLoginAc.getId()) {
					iterator.remove();
				}
			}
			loginAc.setLoginAcList(myFollowedInfo);
			homeDAOCus.cancelFollowed(loginAc);
		}
	}

	/**
	 * 查询商品是否被当前用户收藏过
	 * 
	 * @param loginAcId
	 * @param goodId
	 */
	public boolean isCollected(Integer loginAcId, Integer goodId) {
		boolean isCollected = false;
		// 根据账户ID查询收藏订单
		List<Bill> collectBills = homeDAOCus.findCollectBills(loginAcId);
		// 根据商品ID查询订单编号
		Integer bill = homeDAOCus.findBillByGId(goodId);
		for (Bill cBill : collectBills) {
			if (cBill.getId() == bill) {
				isCollected = true;
			}
		}
		return isCollected;
	}

	/**
	 * 查询商品是否被当前用户设置提醒过
	 * 
	 * @param loginAcId
	 * @param goodId
	 */
	public boolean isReminded(Integer loginAcId, Integer goodId) {
		boolean isReminded = false;
		// 根据账户ID查询收藏订单
		List<Bill> remindBills = homeDAOCus.findRemindBills(loginAcId);
		// 根据商品ID查询订单编号
		Integer bill = homeDAOCus.findBillByGId(goodId);
		for (Bill rBill : remindBills) {
			if (rBill.getId() == bill) {
				isReminded = true;
			}
		}
		return isReminded;
	}

	/**
	 * 查询我的足迹
	 * 
	 * @param loginAcId
	 * @return
	 */
	public List<Map<String, Object>> findMyTrack(Integer loginAcId) {
		List<Map<String, Object>> dataList = null;
		Map<String, Object> map = null;
		List<MyTrack> myTracks = homeDAOCus.findMyTrack(loginAcId);
		if (!myTracks.isEmpty() && myTracks != null) {
			dataList = new ArrayList<>();
			for (MyTrack myTrack : myTracks) {
				map = new HashMap<>();
				map.put("id", myTrack.getId());
				List<Object[]> billList = homeDAOCus.findMyTrackByBillId(myTrack.getBillId());
				if (billList != null) {
					for (Object[] object : billList) {
						Integer billId = Integer.valueOf(object[0].toString());
						List<String> picList = findFirstPicByBillId(billId);
						if (!picList.isEmpty() && picList != null) {
							map.put("goodsPic", picList.get(0));
						}
						map.put("billId", billId);
						map.put("billTitle", object[1] != null ? object[1].toString() : null);
						map.put("currentPrice", object[2] != null ? object[2].toString() : "0.00");
						map.put("unit", object[3] != null ? object[3].toString() : null);
						dataList.add(map);
					}
				}
			}
		}
		return dataList;
	}

	/**
	 * 删除我的足迹
	 * 
	 * @param id
	 */
	public void delMyTrack(String id) {
		homeDAOCus.delMyTrack(id);
	}

	/**
	 * 查询我的喜好
	 * 
	 * @param loginAcId
	 * @return
	 */
	public List<Map<String, Object>> findMyLikes(String loginAcId) {
		List<Map<String, Object>> dataMap = null;
		Map<String, Object> map = null;
		List<Interest> interests = homeDAOCus.findMyLikes(loginAcId);
		if (!interests.isEmpty() && interests != null) {
			dataMap = new ArrayList<>();
			for (Interest interest : interests) {
				map = new HashMap<>();
				map.put("id", interest.getId());
				map.put("code", interest.getCode());
				map.put("commodity", interest.getCommodity());
				map.put("loginAccoutId", interest.getLoginAccoutId());
				dataMap.add(map);
			}
		}
		return dataMap;
	}

	/**
	 * 修改我的喜好
	 * 
	 * @param interests
	 * @return
	 */
	public void addMyLikes(List<Interest> interests) {
		if (!interests.isEmpty() && interests != null) {
			for (Interest interest : interests) {
				interest.setId(interest.getLoginAccoutId() + interest.getCode());
				homeDAOCus.delMyLikes(interest);
			}
			homeDAOCus.addMyLikes(interests);
		}
	}

	/**
	 * 判断账户是否已认证
	 * 
	 * @param loginAcId
	 * @param validFlag
	 * @return
	 */
	public Map<String, Object> isValidAccount(Integer loginAcId) {
		Map<String, Object> validMap = new HashMap<>();
		Object[] statusObj = homeDAOCus.findValidStatusById(loginAcId);
		if (statusObj.length != 0) {
			if ("0".equals(statusObj[0] != null ? statusObj[0].toString() : "0")) {
				validMap.put("personalValid", Boolean.FALSE);
			} else {
				validMap.put("personalValid", Boolean.TRUE);
			}
			if ("0".equals(statusObj[1] != null ? statusObj[1].toString() : "0")) {
				validMap.put("enterpriceValid", Boolean.FALSE);
			} else {
				validMap.put("enterpriceValid", Boolean.TRUE);
			}
		}
		return validMap;
	}

	/**
	 * 根据环信账户ID获取头像和昵称
	 * 
	 * @param hxLoginName
	 */
	public Map<String, Object> findHXInfo(String hxLoginName) {
		Map<String, Object> dataMap = null;
		Object[] objects = homeDAOCus.findHXInfo(hxLoginName);
		if (objects.length != 0) {
			dataMap = new HashMap<>();
			dataMap.put("picUrl", objects[0] != null ? objects[0].toString() : null);
			dataMap.put("nickName", objects[1] != null ? objects[1].toString() : null);
		}
		return dataMap;
	}

	/**
	 * 个人认证
	 */
	public boolean personalAuth(User user) {
		boolean isOK = false;

		// 认证成功后新增用户
		homeDAOCus.addUser(user);

		return isOK;
	}

}
