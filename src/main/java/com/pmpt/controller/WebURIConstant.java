package com.pmpt.controller;

public class WebURIConstant {
    // 活动URI
    public static final String PROMOTION = "/promotion";
    public static final String PROMOTION_LIST = PROMOTION + "/";
    public static final String ADD_PROMOTION = PROMOTION + "/add";
    public static final String UPDATE_PROMOTION = PROMOTION + "/update";
    public static final String DELETE_PROMOTION = PROMOTION + "/delete";

    //上传文件
    public static final String UPLOAD_FILE = "/upload/file";

    //首页URI
    public static final String INDEX_PROMOTION = PROMOTION + "/index";
    public static final String INDEX = "/index";
    
    // 商品详情
    public static final String  BILL = "/bill";
    public static final String BILL_FINDBIDRECORD = BILL + "/findbidrecord"; // 订单竞拍记录和当前最高价
    
    //发布商品
    public static final String PUBLISH = "/publish";
    public static final String PUBLISH_IS_LOGIN = PUBLISH + "/islogin";
    public static final String PUBLISH_GET_LOCATE = PUBLISH + "/getlocate";
    public static final String PUBLISH_PTEROCARPUS_NAME = PUBLISH + "/getname";
    public static final String PUBLISH_ADD_TASK = PUBLISH + "/addtask";
    public static final String PUBLISH_ADD_MAIN_PICTURE = PUBLISH + "/goods/mainpic";
    public static final String PUBLISH_ADD_VIDEO = PUBLISH + "/goods/video";
    public static final String PUBLISH_ADD_DESCRIBE_PICTURE = PUBLISH + "/goods/describe";
    // 后台管理
    public static final String MANAGE = "/manage";
    public static final String MANAGE_PERSON_SAVE = MANAGE + "/person/save";
    public static final String MANAGE_PERSON_FINDALL = MANAGE + "/person/findall";
    public static final String MANAGE_PERSON_DELETE = MANAGE + "/person/delete";
    public static final String MANAGE_PERSON_UPDATE = MANAGE + "/person/update";
    public static final String MANAGE_PERSON_FINDBYID = MANAGE + "/person/findbyid";
    public static final String MANAGE_PERSON_SIGNIN = MANAGE +"/person/signin";
    public static final String MANAGE_PERSON_UPDATEPWD = MANAGE +"/person/updatepwd";
    
    // 客户信息管理
    public static final String MANAGEUSERS = "/manageusers";
    public static final String MANAGEUSERS_FINDALL = MANAGEUSERS + "/findall";
    public static final String MANAGEUSERS_CONDITIONALQUERY = MANAGEUSERS + "/conditionalquery";
    public static final String MANAGEUSERS_QUERYUSER = MANAGEUSERS + "/queryuser";
    
    // 用户登录、注册、信息采集管理
    public static final String LOGINACCOUT = "/loginaccout";
    public static final String LOGINACCOUT_SIGNIN = LOGINACCOUT + "/signin"; // 登录
    public static final String LOGINACCOUT_GETSTATUS = LOGINACCOUT + "/getstatus"; // 从缓存中获取用户状态码
    public static final String LOGINACCOUT_REGISTER = LOGINACCOUT + "/register"; // 注册
    public static final String LOGINACCOUT_LOGOUT = LOGINACCOUT + "/logout"; // 退出接口
    public static final String LOGINACCOUT_MODIFYPWD = LOGINACCOUT + "/modifypwd"; // 修改密码
    public static final String LOGINACCOUT_VERIFYCODE = LOGINACCOUT + "/verifycode"; // 验证短信验证码
    public static final String LOGINACCOUT_FORGOTPWD = LOGINACCOUT + "/forgotpwd"; // 忘记密码提交修改密码
    public static final String LOGINACCOUT_FINDACCOUT = LOGINACCOUT + "/findaccout"; // 验证账户是否已经注册过
    public static final String LOGINACCOUT_GETSMS = LOGINACCOUT + "/getsms"; // 发送注册短信验证码
    public static final String LOGINACCOUT_GETVERIFICATIONCODE = LOGINACCOUT + "/getverificationcode"; // 验证验证码是否输入正确
    public static final String LOGINACCOUT_ADD_INTEREST = LOGINACCOUT + "/addinterest"; // 添加喜好
    public static final String LOGINACCOUT_INTERESTS = LOGINACCOUT + "/interests"; // 展示喜好
    
    public static final String VERIFICATIONCODE_IMAGE = "/verificationcode/image"; // 获取页面图形验证码
}
