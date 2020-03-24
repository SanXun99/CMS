package com.briup.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.briup.demo.bean.Customer;


/**
 * 使用Jpa
 * @author 伴我丿闯荡
 *
 */
public interface CustomerDao extends JpaRepository<Customer,Integer>{
	/**
	 * 根据用户名查询用户信息
	 * @param UserName
	 * @return
	 */
	 @Query(value = "select * from cms_customer where username = ?1",nativeQuery=true)
	 Customer findCustomerByName(String userName);
}
