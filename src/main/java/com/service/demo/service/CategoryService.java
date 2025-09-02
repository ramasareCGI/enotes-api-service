package com.service.demo.service;

import java.util.List;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;


public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryResponseName> getActiveCategory();

	public CategoryDto getCategoryById(Integer id) throws Exception;

	public Boolean deleteCategoryById(Integer id);

}
