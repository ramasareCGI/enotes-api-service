package com.service.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
