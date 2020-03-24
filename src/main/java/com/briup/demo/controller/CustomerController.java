package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.dao.CustomerDao;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与用户相关的 和前端交互的web层
 * 
 * @author 伴我丿闯荡
 *
 */

@RestController
@Api(description = "用户相关接口")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private CustomerDao customerDao;

	@PostMapping("/addCustomer")
	@ApiOperation("添加新用户")
	public Message<String> saveCustomer(Customer customer) {
		try {
			customerService.saveCustomer(customer);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}

	@PostMapping("/updateCustomer")
	@ApiOperation("更新用户信息")
	public Message<String> updateCustomer(Customer customer) {
		try {
			customerService.updateCustomer(customer);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}

	@GetMapping("/findCustomer")
	@ApiOperation("根据id查询用户信息")
	public Message<Customer> findCustomerById(Integer id) {
		try {
			Customer customer = customerService.findCustomerById(id);
			return MessageUtil.success(customer);
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}

	@GetMapping("/deleteCustomer")
	@ApiOperation("根据id删除用户")
	public Message<String> deleteCustomerById(Integer id) {
		try {
			customerService.deleteCustomerById(id);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}

	@GetMapping("/findAllCustomer")
	@ApiOperation("查询所有用户信息")
	public Message<List<Customer>> findAllCustomer() {
		try {
			return MessageUtil.success(customerService.findAllCustomer());
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}

	/**
	 * 用户登录信息
	 * 
	 */
	@PostMapping("/LoginCustomer")
	@ApiOperation("用户登录界面")
	public Message<String> LoginCustomer(Customer customer) {
		try {
			Customer findCustomer = customerDao.findCustomerByName(customer.getUsername());
			if (findCustomer == null) {
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "用户名不存在");
			}
			if (!findCustomer.getPassword().equals(customer.getPassword())) {
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "密码错误");
			} else
				return MessageUtil.success("用户登录成功");
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}
}
