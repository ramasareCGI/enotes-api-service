package com.service.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.service.demo.entity.Category;
import com.service.demo.repository.CategoryRepository;
import com.service.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Boolean saveCategory(Category category) {
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category saveCategory = categoryRepository.save(category);
		if(ObjectUtils.isArray(saveCategory))
		{
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
	List<Category> categorys = categoryRepository.findAll();
		return categorys;
	}

}
