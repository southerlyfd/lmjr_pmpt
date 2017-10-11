/**
 * 
 */
package com.pmpt.interfaces.Dao;

import org.springframework.data.repository.CrudRepository;

import com.pmpt.entities.Bill;


/**
 * @ClassName: BillADAO.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月13日下午5:34:56
 */
public interface BillDAO extends CrudRepository<Bill, Integer> {

}
