package com.pmpt.interfaces.Service;

import java.util.List;

public interface TransactionsService {

	/*
	 * 预热中、热拍中的商品参与竞拍
	 */
	List<Object[]> participateAuction();
}
