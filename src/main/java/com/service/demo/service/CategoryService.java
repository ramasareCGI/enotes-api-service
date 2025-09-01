package com.service.demo.service;

import java.util.List;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;
import com.service.demo.entity.Category;

public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<Category> getAllCategory();

	public List<CategoryResponseName> getActiveCategory();

}
