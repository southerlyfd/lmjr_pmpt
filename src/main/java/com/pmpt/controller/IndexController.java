package com.pmpt.controller;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.interfaces.Service.ActionLogService;
import com.pmpt.interfaces.Service.IndexService;
import com.pmpt.interfaces.Service.PromotionService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	private IndexService indexService;

	@Autowired
	private PromotionService promotionService;
	@Resource
	private LocaleMessageSourceService localeMsgService;
	@Resource
	private ActionLogService actionLogService;

	// 首页
	@RequestMapping("/gethomepage")
	public Response getHomePage() {
		Response res = new Response();
		Map<String, Object> map = new HashMap<>();

		// Banner导航，后台上传1-5张图(返回数据：图片url、商品url、商品编号)
		map.put("banner", promotionService.findIndexPromotion());

		// 更多秒杀图片展示（返回数据：图片第一张图url、剩余结束时间、显示出价最高价、企业用户显示企业icon的URL、商品俗名）-(俗名表确定后修改)
		map.put("quickDeal", indexService.findQuickDeal());

		// 热门分类图片展示,最多展示8个，排序：按热度（返回数据：分类图片URL、类名）
		map.put("classification", indexService.getClassifications());

		// 更多预热图片展示，最多展示8张，排序：时间最快开始拍卖（返回数据：商品第一张图片URL、即将开始时间、起拍价格、企业用户显示企业icon的URL、商品俗名）-(俗名表确定后修改)
		map.put("warmUp",indexService.findWarmUp());

		// 热门品名图片展示
		map.put("hotCommodity", indexService.getHotCommodity());

		// 正在热拍图片展示（未完成）-（auctionBill和bill表需关联）
		map.put("hotAuction", indexService.findQuickDeal());

		// 地域精选（取前五个国家）
		map.put("regionalSelection", indexService.getRegionalSelection());

		// 更多精选图片展示（未完成-1.0版本不做）
		//map.put("getMoreSelections", indexService.getMoreSelections());

		res.setObject(map);
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeMsgService.getMessage("success"));
		return res;
	}

	// (home中已经写好)——s拍卖中的商品详情页信息展示(home中已经写好)
	//	@RequestMapping("/findinauction")
	public Response findInAuction(Integer billId) {
		Response res = new Response();
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeMsgService.getMessage("success"));
		res.setObject(indexService.findInAuction(billId));
		return res;
	}

	/**
	 * 竞拍记录和当前最高价
	 * @param billId
	 *				 订单编号
	 * @return
	 */
	@RequestMapping(WebURIConstant.BILL_FINDBIDRECORD)
	public Response findBidRecord(Integer billId) {
		Response res = new Response();
		Map<String, Object> map = new HashMap<>();
		map = indexService.findBidRecord(billId);
		if (map != null && map.size() != 0) {
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setMessage(localeMsgService.getMessage("success"));
			res.setObject(map);
			return res;
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMsgService.getMessage("fail"));
		res.setObject(map);
		return res;
	}














}
