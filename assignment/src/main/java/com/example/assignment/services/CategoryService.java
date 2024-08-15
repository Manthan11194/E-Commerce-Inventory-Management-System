package com.example.assignment.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.models.Category;

public interface CategoryService {
	
	String createCategory(CategoryDto category);
	
	Page<Category> getAllCategories(Pageable pageable);
	
	CategoryDto getCategoryById(Integer id);
	
	String updateCategory(Integer id,CategoryDto category);
	
	String deleteCategory(Integer id);
	
}
