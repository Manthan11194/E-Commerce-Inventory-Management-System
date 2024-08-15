package com.example.assignment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.models.Category;
import com.example.assignment.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepo;
	
	CategoryServiceImpl(CategoryRepository categoryRepo){
		this.categoryRepo = categoryRepo;
	}

	@Override
	public String createCategory(CategoryDto category) {
		
		Category categoryEntity = new Category();
		categoryEntity.setName(category.getName());
		categoryRepo.save(categoryEntity);
		
		return "Category "+ category.getName()+" added successfully.";
	}

	@Override
	public Page<Category> getAllCategories(Pageable pageable) {
		Page<Category> categories = categoryRepo.findAll(pageable);
		//List<CategoryDto> categoryDtos = convetModelToCategoryDtos(categories);
		
		return categories;
	}
	
	
	@Override
	public CategoryDto getCategoryById(Integer id) {
		CategoryDto categoryDto = new CategoryDto();
		Optional<Category> categoryOptional = categoryRepo.findById(id);
		if(categoryOptional.isPresent()) {
			categoryDto = convetModelToCategoryDto(categoryOptional.get());
		}
		
		return categoryDto;
	}
	
	@Override
	public String updateCategory(Integer id,CategoryDto categoryDto) {
		
		Optional<Category> categoryOptional = categoryRepo.findById(id);
		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get();
			category.setName(categoryDto.getName());
			categoryRepo.save(category);
			return "Category updated successfully.";
		}
		
		return "Categpry is not updated, check logs";
	}
	
	@Override
	public String deleteCategory(Integer id) {
		Optional<Category> categoryOptional = categoryRepo.findById(id);
		if(categoryOptional.isPresent()) {
			categoryRepo.deleteById(id);
			return "Category with id :"+id+ " is deleted.";
		}
		
		return "Category is not exists.";
	}
	
	
	
	private List<CategoryDto> convetModelToCategoryDtos(List<Category> categories){
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for(Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());
			
			categoryDtos.add(categoryDto);
		}
		
		return categoryDtos; 
	}
	
	private CategoryDto convetModelToCategoryDto(Category category){
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());		
		return categoryDto; 
	}
	
	
	

	

}
