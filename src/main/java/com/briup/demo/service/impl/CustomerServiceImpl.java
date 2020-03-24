package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.dao.CustomerDao;
import com.briup.demo.mapper.CustomerMapper;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 实现用户信息交互的逻辑类
 * 
 * @author 伴我丿闯荡
 *
 */

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerDao customerDao;

	@Override
	public void saveCustomer(Customer customer) throws CustomerException {
		// 参数为引用类型，要做判空处理
		if (customer == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数不得为空!");
		}
		Customer findCustomer = customerDao.findCustomerByName(customer.getUsername());
		if (findCustomer != null) {// 判断用户是否存在
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "添加的用户已存在。");
		}
		customerMapper.insert(customer);
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
		Customer findCustomer = customerDao.findCustomerByName(customer.getUsername());
		if (findCustomer == null) {// 判断用户是否存在
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "更改的用户不存在。");
		} else {
			customer.setId(findCustomer.getId());
			customerMapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public Customer findCustomerById(Integer id) throws CustomerException {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		if (customer == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "查询的用户不存在。");
		} else {
			return customer;
		}
	}

	@Override
	public void deleteCustomerById(Integer id) throws CustomerException {
		customerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Customer> findAllCustomer() throws CustomerException {

		return customerMapper.selectByExample(new CustomerExample());
	}

}
