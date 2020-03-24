package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与栏目相关的 和前端交互的web层
 * @author 伴我丿闯荡
 *
 */
@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 查询所有栏目信息
	 * 
	 */
	@GetMapping("/getAllCategorys")
	@ApiOperation("获取所有的栏目信息")
	public Message<List<Category>> findAllCategorys() {
		List<Category> list = categoryService.findAllCategorys();
		return 	MessageUtil.success(list);
	}
	
	/**
	 * 新增栏目信息
	 * 
	 */
	@PostMapping("/addCategory")
	@ApiOperation("新增栏目信息")
	public Message<String>  addCategory(Category category){
		try {
			categoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
	}
	
	/**
	 * 修改栏目信息
	 * 
	 */
	@PostMapping("/updateCategory")
	@ApiOperation("修改栏目信息")
	public Message<String> updateCategory(Category category) {
		categoryService.saveOrUpdateCategory(category);
		return MessageUtil.success();
	}
	
	/**
	 * 根据id删除栏目信息
	 * 
	 */
	@GetMapping("/deleteCategoryById")
	@ApiOperation("根据id删除栏目信息")
	public Message<String> deleteCategoryById(int id) {
		categoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	/**
	 * 根据id查找栏目信息
	 * 
	 */
	@GetMapping("/findCategoryById")
	@ApiOperation("根据id查询栏目信息")
	public Message<Category> findCategoryById(int id) {
		Category category = categoryService.findCategoryById(id);
		return MessageUtil.success(category);
	}
	
	/**
	 * 根据id查找栏目及其包含的所有文章信息
	 * 
	 */
	@GetMapping("/findCategoryExById")
	@ApiOperation("根据栏目id查找栏目及其包含文章的信息")
	public Message<CategoryEx> findCategoryExById(int id) {
		return MessageUtil.success(categoryService.findCategoryExById(id));
	}
	
}
