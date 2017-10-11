package com.pmpt.controller;

import com.pmpt.common.*;
import com.pmpt.entities.Promotion;
import com.pmpt.interfaces.Service.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class PromotionController {

    @Autowired
	private PromotionService promotionService;

    // @Autowired
    // private QiNiuUploadFileUtil fileUtil;


    /**
     * @param currentPage 当前页码
     * @param pageSize    每页条数
     * @return 返回活动列表
     */
    @RequestMapping(value = WebURIConstant.PROMOTION_LIST)
    public Response getPromotion(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        Response response = new Response();
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(promotionService.findAll(pageSize, currentPage));
        return response;
    }

    /**
     * 查询主页banner显示活动(application.properties indexPromotionNum 参数配置最大显示条数)
     *
     * @return
     */
    @RequestMapping(WebURIConstant.INDEX_PROMOTION)
    public Response getPromotion() {
        Response response = new Response();
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(promotionService.findIndexPromotion());
        return response;
    }

    /**
     * 上传文件接口
     *
     * @param file     上传文件
     * @param sessionId 用户令牌
     * @return 返回文件公网访问URI字符串
     */
    @RequestMapping(value = WebURIConstant.UPLOAD_FILE, method = RequestMethod.POST)
    public Response uploadPic(MultipartFile file, String sessionId) {
        Response response = new Response();
        //通过sessonId验证身份;
        if (file == null) {
            response.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
            response.setMessage(ResponseStatusCode.PARAMETER_ERROR.getDesc());
            return response;
        }
        try {
            // String fileURL = fileUtil.uploadFile(file.getInputStream());
        	
        	String fileURL = MainUtilityTools.saveFile(file);
            response.setStatus(ResponseStatusCode.SUCCESS.getCode());
            response.setStatus(ResponseStatusCode.SUCCESS.getDesc());
            response.setObject(fileURL);
        } catch (Exception e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setStatus(e.getMessage());
            MainUtilityTools.catchException(e, this.getClass(), "");
        }
        return response;
    }

    /**
     * @param promotion 活动对象
     * @param sessionId  用户令牌
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(WebURIConstant.ADD_PROMOTION)
    public Response addPromotion(@RequestBody Promotion promotion, String sessionId) throws IllegalAccessException {
        Response response = new Response();
        //通过sessonId验证身份;
        try {
            Util.isNotNull(promotion);
        } catch (NullValueException e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(promotionService.addPromotion(promotion));
        return response;
    }

    @RequestMapping(WebURIConstant.UPDATE_PROMOTION)
    public Response modifyPromotion(@RequestBody Promotion promotion, String sessionId) {
        //通过sessonId验证身份;
        promotionService.modifyPromotion(promotion);
        return null;
    }

    @RequestMapping(WebURIConstant.DELETE_PROMOTION)
    public Response deletePromotion(String id, String sessionId) {
        //通过sessonId验证身份;
        promotionService.deletePromotion(id);
        return null;
    }

}
