package com.service.demo.service;

import java.util.List;

import com.service.demo.entity.Category;

public interface CategoryService {
	
	public Boolean saveCategory(Category category);
	
	public List<Category> getAllCategory();

}
