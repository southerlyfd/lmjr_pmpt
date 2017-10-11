/**
 * 
 */
package com.pmpt.interfaces.Dao;

import org.springframework.data.repository.CrudRepository;

import com.pmpt.entities.LoginAccout;


/**
 * @ClassName: ManageUsersDAO.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月23日上午11:01:38
 */
public interface ManageUsersDAO extends CrudRepository<LoginAccout, Integer>{

}
