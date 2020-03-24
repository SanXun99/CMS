package com.briup.demo.mapper.ex;

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 处理 查询栏目及其包含的文章信息
 * @author 伴我丿闯荡
 *
 */
public interface CategoryExMapper {
	/**
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryExs();
	
	/**
	 * 实现根据id查找栏目及其包含的所有文章信息
	 * @param id
	 * @return
	 */
	CategoryEx findCategoryExById(int id);
}
