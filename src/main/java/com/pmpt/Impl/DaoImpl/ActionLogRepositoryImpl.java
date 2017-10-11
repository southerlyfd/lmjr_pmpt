/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.ActionLog;
import com.pmpt.interfaces.Dao.ActionLogRepository;

/**
 * @author jitl
 *
 */
@Repository
public class ActionLogRepositoryImpl implements ActionLogRepository {

	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	
	/* (non-Javadoc)
	 * @see com.pmpt.domain.mongo.dao.ActionLogRepository#addLog()
	 */
	@Override
	public String addLog(ActionLog actionLog) {
		try {
			mongoTemplate.insert(actionLog);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), localeMessageSourceService.getMessage("com.pmpt.actionlog.add.exception"));
		}
		return null;
	}

}
