package com.pmpt.Impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmpt.interfaces.Dao.TransactionsDAOCus;
import com.pmpt.interfaces.Service.TransactionsService;

/**
 * @ClassName: TransactionServiceImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月29日下午1:55:21
 */
@Service
public class TransactionServiceImpl implements TransactionsService {

	@Autowired
	TransactionsDAOCus dao2;
	
	@Override
	public List<Object[]> participateAuction() {
		return dao2.participateAuction();
	}

}
