package com.pmpt.interfaces.Dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.pmpt.entities.LoginAccout;


/**
 * @ClassName: HomeDAO
 * @Description: TODO
 * @author: 汪洋
 * @date: 2017年8月10日 下午5:12:17
 */
public interface HomeDAO extends CrudRepository<LoginAccout, Serializable> {

	/**
	 * 根据账户ID查找账户信息
	 * 
	 * @param Id
	 * @return
	 */
	public LoginAccout findById(Integer Id);

}
