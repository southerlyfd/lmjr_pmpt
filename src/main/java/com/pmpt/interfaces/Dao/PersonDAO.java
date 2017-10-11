/**
 * 
 */
package com.pmpt.interfaces.Dao;

import org.springframework.data.repository.CrudRepository;

import com.pmpt.entities.Person;


/**
 * @ClassName: PersonDAO.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日下午2:51:16
 */
public interface PersonDAO extends CrudRepository<Person, Integer> {

}
