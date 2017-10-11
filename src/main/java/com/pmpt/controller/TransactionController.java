package com.pmpt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.interfaces.Service.TransactionsService;

/**
 * @ClassName: TransactionController.java
 * @Description: 竞拍流程
 * @author jianghb
 * @date 2017年9月29日下午1:15:08
 */
@RestController
public class TransactionController {

	@Autowired
	private TransactionsService transactionsService;
	
	@Resource
	private LocaleMessageSourceService localeMsgService;
	
	/*
	 * 预热中、热拍中的商品参与竞拍
	 */
	public Response participateAuction() {
		Response res = new Response();
		List<Object[]> list = new ArrayList<>();
		list = transactionsService.participateAuction();
		res.setObject(list);
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeMsgService.getMessage("success"));
		return res;
	}
	
	/*
	 * 热拍中商品进行竞拍加价
	 */
	
	/*
	 * 买家付款后状态修改
	 */
	
	/*
	 * 卖家发货后状态修改
	 */
	
	/*
	 * 买家收到货后确认
	 */
	
}
