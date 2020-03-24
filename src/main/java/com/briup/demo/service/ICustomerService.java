package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

/**
 * 用户相关的service层
 * @author 伴我丿闯荡
 *
 */

public interface ICustomerService {
	/**
	 * 添加用户信息
	 * @param customer
	 * @throws CustomerException
	 */
	public void saveCustomer(Customer customer) throws CustomerException;
	
	/**
	 * 更改用户信息
	 * @param customer
	 * @throws CustomerException
	 */
	public void updateCustomer(Customer customer) throws CustomerException;
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 * @throws CustomerException
	 */
	public Customer findCustomerById(Integer id) throws CustomerException;
	
	/**
	 * 根据id删除用户信息
	 * @param id
	 * @throws CustomerException
	 */
	public void deleteCustomerById(Integer id) throws CustomerException;
	
	/**
	 * 查询所有用户信息
	 * @return
	 * @throws CustomerException
	 */
	public List<Customer> findAllCustomer() throws CustomerException;
}
