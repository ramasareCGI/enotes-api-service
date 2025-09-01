package com.service.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByIsActiveTrue();

}
