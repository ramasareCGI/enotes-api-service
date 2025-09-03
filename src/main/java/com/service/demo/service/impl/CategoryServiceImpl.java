package com.service.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;
import com.service.demo.entity.Category;
import com.service.demo.exception.ExitDetaException;
import com.service.demo.exception.ResourceNotFoundException;
import com.service.demo.repository.CategoryRepository;
import com.service.demo.service.CategoryService;
import com.service.demo.util.Validation;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public Boolean saveCategory(CategoryDto categorydto) {

//		Category category = new Category();
//		category.setName(categorydto.getName());
//		category.setDescription(categorydto.getDescription());
//		category.setIsActive(categorydto.getIsActive());
//
		//Validation checking 
		validation.categoryValidation(categorydto);
		
		//check category exit or not
		
		Boolean exit=categoryRepository.existsByName(categorydto.getName().trim() );
		if(exit)
		{
			throw new ExitDetaException("category name alredy exit ");
		}
		
		Category category = mapper.map(categorydto, Category.class);
		category.setIsDeleted(true);
//		category.setCreatedBy(1);
//		category.setCreatedOn(new Date());
		Category saveCategory = categoryRepository.save(category);
		if (ObjectUtils.isArray(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categorys = categoryRepository.findByIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categorys.stream().map(cat->mapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	
	}

	@Override
	public List<CategoryResponseName> getActiveCategory() {
		List<Category> categorys = categoryRepository.findByIsActiveTrue();
		List<CategoryResponseName> categoryList = categorys.stream()
				.map(cat -> mapper.map(cat, CategoryResponseName.class)).toList();

		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		Category findCategoryById = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Category not foun with id ="+id));
		if (ObjectUtils.isEmpty(findCategoryById)) {
			//Category category = findCategoryById.get();
			return mapper.map(findCategoryById, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> findCategoryById = categoryRepository.findById(id);
		if(findCategoryById.isPresent())
		{
			Category category = findCategoryById.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		
		return false;

	}

}
