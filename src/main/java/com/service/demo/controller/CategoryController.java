package com.service.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.CategoryResponseName;
import com.service.demo.service.CategoryService;
import com.service.demo.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	

	@PostMapping("/savecategory")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categorydto) {
		Boolean saveCategory = categoryService.saveCategory(categorydto);
		if (saveCategory) {
			return CommonUtil.createBuildResponseMessage("success",HttpStatus.CREATED );
			// return new ResponseEntity<>("save success", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorMessageResponse("not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<?> getALlCategory() {
//		String str=null;
//		str.toUpperCase();
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		
		if (CollectionUtils.isEmpty(allCategory)) {
			
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
			// return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/active-category")
	public ResponseEntity<?> getActiveCategory() {
		List<CategoryResponseName> allCategory = categoryService.getActiveCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception {

		CategoryDto categoryDto = categoryService.getCategoryById(id);
		if (!ObjectUtils.isEmpty(categoryDto)) {
		return	CommonUtil.createErrorMessageResponse("Internal Server Error",  HttpStatus.NOT_FOUND);
			//return new ResponseEntity<>("category not found with ID =" + id, HttpStatus.NOT_FOUND);
		}
		return	CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);
		//return new ResponseEntity<>(id, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable Integer id) {
		Boolean deleted = categoryService.deleteCategoryById(id);
		if (deleted) {
		return CommonUtil.createBuildResponse("Category deleted success", HttpStatus.OK);
			//return new ResponseEntity<>("category deleted success =" + id, HttpStatus.OK);
		}
		return	CommonUtil.createErrorMessageResponse("Category not  deleted",  HttpStatus.NOT_FOUND);
		//return new ResponseEntity<>("category ID not found =" + id, HttpStatus.NOT_FOUND);

	}
}
