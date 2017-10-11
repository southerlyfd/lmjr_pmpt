package com.pmpt.interfaces.Dao;

import java.util.List;

/**
 * @ClassName: TransactionDAOCus.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月29日下午1:53:05
 */
public interface TransactionsDAOCus {

	/*
	 * 预热中、热拍中的商品参与竞拍
	 */
	List<Object[]> participateAuction();
}
