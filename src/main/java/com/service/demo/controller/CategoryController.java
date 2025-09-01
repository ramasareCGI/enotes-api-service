package com.service.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;
import com.service.demo.entity.Category;
import com.service.demo.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/savecategory")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categorydto) {
		Boolean saveCategory = categoryService.saveCategory(categorydto);
		if (saveCategory) {
			return new ResponseEntity<>("save success", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<?> getALlCategory() {
		List<Category> allCategory = categoryService.getAllCategory();
		if (org.springframework.util.CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/active-category")
	public ResponseEntity<?> getActiveCategory() {
		List<CategoryResponseName> allCategory = categoryService.getActiveCategory();
		if (org.springframework.util.CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

}
