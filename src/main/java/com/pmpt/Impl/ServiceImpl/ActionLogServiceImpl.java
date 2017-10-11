/**
 * 
 */
package com.pmpt.Impl.ServiceImpl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.ActionLog;
import com.pmpt.interfaces.Dao.ActionLogRepository;
import com.pmpt.interfaces.Service.ActionLogService;

/**
 * @author jitl
 *
 */
@Service
public class ActionLogServiceImpl implements ActionLogService {

	@Autowired
	ActionLogRepository actionLogRepository;
	
	@Resource
	LocaleMessageSourceService localeMess;
	
	/* (non-Javadoc)
	 * @see com.pmpt.service.ActionLogService#addLog(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	@Override
	public String addLog(HttpServletRequest request, String actionLogClass,String actionLogCode,String interfaceCode,String actor, String describe) {
		ActionLog actionLog = new ActionLog();
		String ip = "";
		if(request != null) {
		  ip = request.getRemoteHost();
		}
		actionLog.setActionLogClass(actionLogClass);
		actionLog.setActionLogCode(actionLogCode);
		actionLog.setInterfaceCode(interfaceCode);
		actionLog.setIp(ip);
		actionLog.setDate(new Date());
		actionLog.setDateStr(MainUtilityTools.dateToString());
		actionLog.setActor(actor);
		actionLog.setDescribe(describe);
		actionLogRepository.addLog(actionLog);
		
		return localeMess.getMessage("success");
	}

}
