package com.pmpt.controller;

import com.pmpt.common.MainUtilityTools;
// 	import com.pmpt.common.QiNiuUploadFileUtil;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.entities.Bill;
import com.pmpt.entities.BillVo;
import com.pmpt.entities.GoodsPic;
import com.pmpt.entities.Locate;
import com.pmpt.entities.Pterocarpus;
import com.pmpt.entities.enums.Constant;
import com.pmpt.entities.enums.GoodPicTypeEnum;
import com.pmpt.entities.enums.Status;
import com.pmpt.interfaces.Service.GoodsPicService;
import com.pmpt.interfaces.Service.PublishService;
import com.pmpt.interfaces.Service.VerificationCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 发布拍卖商品接口
 */
@RestController
public class PublishController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private PublishService publishService;

    /*@Autowired
    private QiNiuUploadFileUtil fileUtil;*/

    @Autowired
    private GoodsPicService goodsPicService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证Session是否存在,是否调用取决于后期前端处理逻辑
     *
     * @param sessionId 用户token
     * @return
     */
    @RequestMapping(WebURIConstant.PUBLISH_IS_LOGIN)
    public Response isLogin(String sessionId) {
        Response response = new Response();
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(verificationCodeService.getRedis(sessionId) != null);
        return response;
    }

    /**
     * 获取产地信息
     *
     * @param sessionId 用户token
     * @return 返回产地列表, 没有则返回长度为零的集合
     */
    @RequestMapping(WebURIConstant.PUBLISH_GET_LOCATE)
    public Response getLocate(String sessionId) {
        Response response = new Response();
        List<Locate> locate;
        try {
            locate = publishService.getLocate();
            response.setStatus(ResponseStatusCode.SUCCESS.getCode());
            response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
            response.setObject(locate);
        } catch (Exception e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 获取品名信息
     * @param sessionId 用户token
     * @param id 用户所选产地信息
     * @return 返回品名列表，没有则返回长度为零的集合
     */
    @RequestMapping(WebURIConstant.PUBLISH_PTEROCARPUS_NAME)
    public Response getPterocarpus(String sessionId, Long id) {
        Response response = new Response();
        List<Pterocarpus> pterocarpuses;
        try {
            pterocarpuses = publishService.getPterocarpus(id);
            response.setStatus(ResponseStatusCode.SUCCESS.getCode());
            response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
            response.setObject(pterocarpuses);
        } catch (Exception e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 发布商品,保存发布订单
     * @param sessionId
     * @param bill
     * @return 返回订单对象
     */
    @RequestMapping(WebURIConstant.PUBLISH_ADD_TASK)
    public Response addPublishTask(String sessionId, @RequestBody Bill bill) {
        Response response = new Response();
        //拼装Bill对象订单和状态属性
        String billNo = new SimpleDateFormat(Constant.DATEFORMAT).format(new Date()) + generateKey(redisTemplate.boundValueOps(Constant.INCR_BILL_NO).increment(Constant.BILL_NO_INCRBY));
        bill.setBillNo(billNo);
        bill.setBillStatus(Status.SUCCPUB.getKey());
        try {
            //返回表现层展示对象
            BillVo vo = publishService.addBill(bill);
            response.setStatus(ResponseStatusCode.SUCCESS.getCode());
            response.setMessage(ResponseStatusCode.SUCCESS.getCode());
            response.setObject(vo);
        } catch (Exception e) {
            redisTemplate.boundValueOps(Constant.INCR_BILL_NO).increment(Constant.BILL_NO_ROLLBACK);
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 添加订单主图方法
     * @param sessionId 用户token
     * @param files spring封装文件流对象
     * @return 将保存的GoodPic对象返回给前端组装Bill对象
     */
    @RequestMapping(WebURIConstant.PUBLISH_ADD_MAIN_PICTURE)
    public Response addPublishMainPicture(String sessionId, @RequestParam MultipartFile[] files) {
        Response response = new Response();
        List<GoodsPic> goodsPics;
        if (files == null || files.length == 0 || files.length > Constant.MAX_MAIN_PICTURE_COUNT) {
            response.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
            response.setMessage(ResponseStatusCode.PARAMETER_ERROR.getDesc());
            return response;
        }
        try {
            goodsPics = saveGoodsPic(files, GoodPicTypeEnum.MAIN_PIC, new LinkedList<>());
        } catch (IOException e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(goodsPics);
        return response;
    }

    /**
     * 添加订单视频方法
     *
     * @param sessionId 用户token
     * @param file      spring封装文件对象
     * @return 返回文件公网访问URI, 交由前端拼装订单对象
     */
    @RequestMapping(WebURIConstant.PUBLISH_ADD_VIDEO)
    public Response addPublishVideo(String sessionId, MultipartFile file) {
        Response response = new Response();
        try {
            // String fileURI = fileUtil.uploadFile(file.getInputStream());
        	String fileURI= MainUtilityTools.saveFile(file);
            response.setStatus(ResponseStatusCode.SUCCESS.getCode());
            response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
            response.setObject(fileURI);
        } catch (Exception e) {
        	MainUtilityTools.catchException(e, this.getClass(), "");
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }

    /**
     * 添加订单描述附图方法
     * @param sessionId 用户token
     * @param files spring封装文件对象
     * @return 返回GoodPic集合，交由前端组装Bill对象
     */
    @RequestMapping(WebURIConstant.PUBLISH_ADD_DESCRIBE_PICTURE)
    public Response addDescribePicture(String sessionId, @RequestParam MultipartFile[] files) {
        Response response = new Response();
        List<GoodsPic> goodsPics;
        if (files == null || files.length < Constant.MIN_DESCRIBE_PICTURE_COUNT || files.length > Constant.MAX_DESCRIBE_PICTURE_COUNT) {
            response.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
            response.setMessage(ResponseStatusCode.PARAMETER_ERROR.getDesc());
            return response;
        }
        try {
            goodsPics = saveGoodsPic(files, GoodPicTypeEnum.DESCRIBE, new LinkedList<>());
        } catch (IOException e) {
            response.setStatus(ResponseStatusCode.EXCEPTION.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
        response.setStatus(ResponseStatusCode.SUCCESS.getCode());
        response.setMessage(ResponseStatusCode.SUCCESS.getDesc());
        response.setObject(goodsPics);
        return response;
    }

    private List<GoodsPic> saveGoodsPicture(MultipartFile[] files, GoodPicTypeEnum type, List<GoodsPic> goodsPics) throws IOException {
        for (MultipartFile file : files) {
            // String fileUri = fileUtil.uploadFile(file.getInputStream());
        	
        	String fileUri= MainUtilityTools.saveFile(file);
            GoodsPic pic = new GoodsPic();
            pic.setPicUrl(fileUri);
            pic.setFlag(type.getType());
            goodsPicService.addGoodPic(pic);
            goodsPics.add(pic);
        }
        return goodsPics;
    }

    private String generateKey(Long i) {
        String key = i.toString();
        while (key.length() < Constant.BILL_NO_MAX_LENGTH) {
            key = Constant.ZERO + key;
        }
        return key;
    }

    private List<GoodsPic> saveGoodsPic(MultipartFile[] files, GoodPicTypeEnum type, List<GoodsPic> goodsPics) throws IOException {
    	int i = 1;
    	for (MultipartFile file : files) {
    		// String fileUri = fileUtil.uploadFile(file.getInputStream());

    		String fileUri= MainUtilityTools.saveFile(file);
    		GoodsPic pic = new GoodsPic();
    		pic.setPicUrl(fileUri);
    		if (type.getIndex() == 1) {
    			pic.setFlag(i + "");
    			i++;
    		}else if (type.getIndex() == 3) {
    			pic.setFlag(i + "");
    		}
    		goodsPicService.addGoodPic(pic);
    		goodsPics.add(pic);
    		i++;
    	}

    	return goodsPics;
    }
}
