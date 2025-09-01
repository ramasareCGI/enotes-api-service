package com.service.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;
import com.service.demo.entity.Category;
import com.service.demo.repository.CategoryRepository;
import com.service.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveCategory(CategoryDto categorydto) {

//		Category category = new Category();
//		category.setName(categorydto.getName());
//		category.setDescription(categorydto.getDescription());
//		category.setIsActive(categorydto.getIsActive());
//
		Category category = mapper.map(categorydto, Category.class);
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category saveCategory = categoryRepository.save(category);
		if (ObjectUtils.isArray(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categorys = categoryRepository.findAll();
		return categorys;
	}

	@Override
	public List<CategoryResponseName> getActiveCategory() {
		List<Category> categorys = categoryRepository.findByIsActiveTrue();
		List<CategoryResponseName> categoryList = categorys.stream()
				.map(cat -> mapper.map(cat, CategoryResponseName.class)).toList();

		return categoryList;
	}

}
