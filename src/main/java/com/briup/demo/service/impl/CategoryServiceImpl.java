package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 操作栏目的service功能类
 * @author 伴我丿闯荡
 *
 */

@Service
public class CategoryServiceImpl implements ICategoryService {
	//栏目的dao
	@Autowired
	private CategoryMapper categoryMapper;
	
	//栏目的扩展dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	//文章的dao
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Category> findAllCategorys() throws CustomerException {

		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		// 参数为引用类型，要做判空处理
		if (category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数不得为空!");// 等于null不会执行下面的语句
		}
		// 判断category对象的id是否为空，如果为空则添加栏目，不为空则修改栏目信息。
		if (category.getId() == null) {
			CategoryExample example = new CategoryExample();
			//添加编号相同的条件
			example.createCriteria().andCodeEqualTo(category.getCode());
			if(categoryMapper.selectByExample(example).size() != 0) {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "存在编号相同的节目");
			}
			//清除条件，继续进行筛选
			example.clear();
			//添加节目名相同的条件
			example.createCriteria().andNameEqualTo(category.getName());
			if (categoryMapper.selectByExample(example).size() != 0) {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "该节目已存在");
			}
			categoryMapper.insert(category);
		} else {
			categoryMapper.updateByPrimaryKey(category);
		}

	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		//删除栏目的同时，需要先删除栏目中包含的文章信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}
	
	
	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {
		return categoryExMapper.findCategoryExById(id);
	}

}
