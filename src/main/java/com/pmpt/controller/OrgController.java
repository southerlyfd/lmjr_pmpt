package com.pmpt.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.ActionLogClass;
import com.pmpt.common.ActionLogCode;
import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.entities.Organization;
import com.pmpt.interfaces.Service.ActionLogService;
import com.pmpt.interfaces.Service.OrgService;

@RestController
@RequestMapping("/org")
public class OrgController {
	
	@Autowired
	private OrgService service;
	
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	
	@Resource
	private ActionLogService actionLogService;
	
	// 新建
    @RequestMapping("/save")
    public Response save(Model model,String name,Integer parentId,HttpServletResponse response) {
        String nameTemp = name;
        Integer parentId1 = parentId;
        
        Organization organization = new Organization();
        organization.setName(nameTemp);
        if (parentId1 != null) {
        	Organization organizationParent = new Organization();
        	organizationParent.setId(parentId1);
        	organization.setParentOrg(organizationParent);
		}
        
        try {
			service.save(organization);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
	        res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
	        res.setMessage(localeMessageSourceService.getMessage("fail"));
	        res.setObject(organization);
			return res;
		}
        
        Response res = new Response();
        res.setStatus(ResponseStatusCode.SUCCESS.getCode());
        res.setMessage(localeMessageSourceService.getMessage("success"));
        res.setObject(organization);
		return res;
    }
    // 删除
    @RequestMapping("/delete")
    public Response delete(Model model,Integer id,HttpServletResponse response) {
        
        Organization organization = new Organization();
        organization.setId(id);
        
        try {
			service.delete(organization);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
	        res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
	        res.setMessage(localeMessageSourceService.getMessage("fail"));
	        res.setObject(organization);
			return res;
		}
        
        Response res = new Response();
        res.setStatus(ResponseStatusCode.SUCCESS.getCode());
        res.setMessage(localeMessageSourceService.getMessage("success"));
        res.setObject(organization);
		return res;
    }
    // 修改
    @RequestMapping("/update")
    public Response update(Model model,Integer id,String name,HttpServletResponse response) {
        String nameTemp = name;
        
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(nameTemp);
        
        try {
			service.save(organization);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
	        res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
	        res.setMessage(localeMessageSourceService.getMessage("fail"));
	        res.setObject(organization);
			return res;
		}
        
        
        
        Response res = new Response();
        res.setStatus(ResponseStatusCode.SUCCESS.getCode());
        res.setMessage(localeMessageSourceService.getMessage("success"));
        res.setObject(organization);
		return res;
    }
    // 查询全部
    @RequestMapping("/findAll")
    public Response findAll(HttpServletRequest request) {
    	MainUtilityTools.getLogger().error("ERROR8888MainUtilityTools");
    	Iterable<Organization> iterable = null;
        try {
        	iterable = service.findAll();
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
	        res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
	        res.setMessage("失败");
			return res;
		}
        
        Response res = new Response();
        res.setStatus(ResponseStatusCode.SUCCESS.getCode());
        res.setMessage(localeMessageSourceService.getMessage("success"));
        res.setObject(iterable);
		return res;
    }
    
 // 查询全部
    @RequestMapping("/findByName")
    public Response findByName(String name) {
    	List<Organization> organizations = null;
        try {
        	organizations = service.findByName(name);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
	        res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
	        res.setMessage(localeMessageSourceService.getMessage("result.no"));
			return res;
		}
        
        Response res = new Response();
        res.setStatus(ResponseStatusCode.SUCCESS.getCode());
        res.setMessage(localeMessageSourceService.getMessage("success"));
        res.setObject(organizations);
		return res;
    }
    
    @RequestMapping("/test")
    public void test(HttpServletRequest request,String name) {
    	
    	actionLogService.addLog(request, ActionLogClass.INFO.getCode(),ActionLogCode.ORG.getCode(),"8888aaaccc","namsdjfkj", "查询，用户修改密码，充值");
    }
    
    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res) {
      String fileName = "48769041-ff6f-47ac-be68-b259dcbfa213";
      res.setHeader("content-type", "application/octet-stream");
      res.setContentType("application/octet-stream");
      res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
      byte[] buff = new byte[1024];
      BufferedInputStream bis = null;
      OutputStream os = null;
      try {
        os = res.getOutputStream();
        bis = new BufferedInputStream(new FileInputStream(new File("/home/jitl/file/2017-09-01/"
            + fileName)));
        int i = bis.read(buff);
        while (i != -1) {
          os.write(buff, 0, buff.length);
          os.flush();
          i = bis.read(buff);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (bis != null) {
          try {
            bis.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println("success");
    }
}