/**
 * 
 */
package com.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.Parameters;
import com.pmpt.entities.Address;
import com.pmpt.entities.BillBidRec;
import com.pmpt.entities.Goods;
import com.pmpt.entities.GoodsPic;
import com.pmpt.entities.LoginAccout;
import com.pmpt.entities.Transport;
import com.pmpt.entities.enums.Status;
import com.pmpt.entities.enums.Valid;

/**
 * @author Administrator
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	// @org.junit.Test
	public static void testTime() {
		// TODO Auto-generated method stub
		Parameters parameters = new Parameters();
		parameters.setUrl("ConsumerServlet.json?action=getpassword");
		parameters.setList(new ArrayList<NameValuePair>());
		@SuppressWarnings("unused")
		String string = (String) MainUtilityTools.execute(parameters);
	}

	public static void testQuery() {
		// TODO Auto-generated method stub
		Parameters parameters = new Parameters();
		parameters.setUrl("VchicleServlet.json?action=pagequery");
		parameters.setList(new ArrayList<NameValuePair>());
		parameters.getList().add(new BasicNameValuePair("pageSize", "2"));
		parameters.getList().add(new BasicNameValuePair("currPage", "3"));
		@SuppressWarnings("unused")
		String string = (String) MainUtilityTools.execute(parameters);
	}

	public static void vchicle() {
		Parameters parameters = new Parameters();
		// 测添加
		// parameters.setUrl("VchicleServlet.json?action=insert");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("code", "112"));
		// parameters.getList().add(new BasicNameValuePair("name", "蓝瑟"));
		// parameters.getList().add(new BasicNameValuePair("brand", ""));
		// parameters.getList().add(new BasicNameValuePair("color", ""));
		// parameters.getList().add(new BasicNameValuePair("series", ""));
		// parameters.getList().add(new BasicNameValuePair("factory", ""));
		// parameters.getList().add(new BasicNameValuePair("volume",""));
		// parameters.getList().add(new BasicNameValuePair("gearNumber", ""));

		// 测查询所有
		// parameters.setUrl("VchicleServlet.json?action=query");
		// parameters.setList(new ArrayList<NameValuePair>());

		// 测条件查询
		// parameters.setUrl("VchicleServlet.json?action=criteriaQuery");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("name", "路虎"));
		// 测删除
		// parameters.setUrl("VchicleServlet.json?action=delete");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("id", "2"));

		// 测详情
		// parameters.setUrl("VchicleServlet.json?action=detail");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("vchicleId", "1"));

		// 测修改
		// parameters.setUrl("VchicleServlet.json?action=modify");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("vchicleId", "1"));
		// parameters.getList().add(new BasicNameValuePair("code", "111"));
		// parameters.getList().add(new BasicNameValuePair("name", "凯迪拉克"));
		// parameters.getList().add(new BasicNameValuePair("brand", "2"));
		// parameters.getList().add(new BasicNameValuePair("color", "2"));
		// parameters.getList().add(new BasicNameValuePair("series", "2"));
		// parameters.getList().add(new BasicNameValuePair("factory", "2"));
		// parameters.getList().add(new BasicNameValuePair("volume", "7"));
		// parameters.getList().add(new BasicNameValuePair("gearNumber",
		// "11111"));
		// 测试批量删除
		// parameters.setUrl("VchicleServlet.json?action=batchdelete");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("vchicleIds",
		// "19,23,24"));

		// 测试搜索即时车信
		// parameters.setUrl("VchicleServlet.json?action=searchcarinfo");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new BasicNameValuePair("code", "003"));
		// 测试获取密码
		// parameters.setUrl("ConsumerServlet.json?action=getpassword");
		// parameters.setList(new ArrayList<NameValuePair>());
		// 测试下一步
		// parameters.setUrl("ConsumerServlet.json?action=nextcarinfo");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(new
		// BasicNameValuePair("loginName","1987548757777"));
		// parameters.getList().add(new BasicNameValuePair("password","111"));
		// 测试添加客户
		// parameters.setUrl("ConsumerServlet.json?action=insert");
		// parameters.setList(new ArrayList<NameValuePair>());
		// parameters.getList().add(
		// new BasicNameValuePair("loginName", "888888888"));
		// parameters.getList().add(new BasicNameValuePair("password",
		// "0000000"));
		// @SuppressWarnings("unused")
		// String string = (String) MainUtilityTools.execute(parameters);
		// testTime() ;
		// testTime() ;

		// Parameters parameters = new Parameters();
		parameters.setUrl("VchicleServlet.json?action=pagequery");
		parameters.setList(new ArrayList<NameValuePair>());
		parameters.getList().add(new BasicNameValuePair("pageSize", "2"));
		parameters.getList().add(new BasicNameValuePair("currPage", "2"));
		@SuppressWarnings("unused")
		// parameters.getList().add(new BasicNameValuePair("name", "333"));
		String string = (String) MainUtilityTools.execute(parameters);
	}

	@org.junit.Test
	public void time() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date now = df.parse("2014-09-11 13:00:00");
			java.util.Date date = df.parse("2014-09-11 13:00:50");
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			if (day == 0 && hour == 0 && min == 0 && s < 60) {
				System.out.println("ok");
				return;
			}
			System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@org.junit.Test
	public void insert() {
		// TODO Auto-generated method stub
		Parameters parameters = new Parameters();
		parameters.setUrl("/org/save");
		parameters.setList(new ArrayList<NameValuePair>());
		parameters.getList().add(new BasicNameValuePair("name", "112"));
		@SuppressWarnings("unused")
		String string = (String) MainUtilityTools.execute(parameters);
		System.out.println(string);
	}
	
	@org.junit.Test
	public void login() {
		// TODO Auto-generated method stub
		Parameters parameters = new Parameters();
		parameters.setUrl("LoginServlet?action=login");
		parameters.setList(new ArrayList<NameValuePair>());
		parameters.getList().add(new BasicNameValuePair("info", "jitl"));
		parameters.getList().add(new BasicNameValuePair("info2", "admin"));
		parameters.getList().add(new BasicNameValuePair("info3", "admin"));
		@SuppressWarnings("unused")
		String string = (String) MainUtilityTools.execute(parameters);
		System.out.println(string);
	}
	
	private String url = "http://localhost:8080/VerificationCode/image";  
    private String charset = "utf-8";  
      
    @org.junit.Test  
    public void test(){
    	for (int i = 0; i < 10; i++) {
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		String httpOrgCreateTest = url;  
    		Map<String,String> createMap = new HashMap<String,String>();  
    		/*createMap.put("info","jitl");  
    		createMap.put("info2","admin");  
    		createMap.put("info3","admin");  */
    		String httpOrgCreateTestRtn = MainUtilityTools.execute2(httpOrgCreateTest,createMap,charset);  
    		System.out.println("result:"+httpOrgCreateTestRtn);  
		}
    }  
    

}
