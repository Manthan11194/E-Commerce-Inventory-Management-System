package com.example.assignment.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.models.Category;
import com.example.assignment.services.CategoryService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
    private static final Logger logger = LogManager.getLogger(CategoryController.class);
	
	//@Autowired
	//Constructor injection.
	private CategoryService categoryService;
	CategoryController(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	//Create a category
	@PostMapping
	public ResponseEntity<String> createCategory(@RequestBody CategoryDto category) {
		String responseBody = new String();
        try {
        	logger.info("Create a category.");
            responseBody = categoryService.createCategory(category);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        	logger.error("Error occured!",e.getMessage());
        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	//Get all categories
	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(
			@RequestParam(defaultValue = "0") int page ,
			@RequestParam(defaultValue = "10") int size) {
		
        try {
        	logger.info("Get all categories.");
            Pageable pageable = PageRequest.of(page,size);

            Page<Category> categories = categoryService.getAllCategories(pageable);
            return new ResponseEntity(categories, HttpStatus.OK);
            
        } catch (Exception e) {
        	logger.error("Error occured!",e.getMessage());

        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	//Get Category by id.
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
		CategoryDto categoryDto = new CategoryDto();
        try {
        	logger.info("Get category by id: ",id);
        	categoryDto = categoryService.getCategoryById(id);
            return new ResponseEntity(categoryDto, HttpStatus.OK);
            
        } catch (Exception e) {
        	logger.error("Error occured!",e.getMessage());

        }
        return new ResponseEntity<>(categoryDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	//Update a category.
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer id,@RequestBody CategoryDto category) {
		String responseBody = new String();
        try {
        	logger.info("Updating category.");
            responseBody = categoryService.updateCategory(id,category);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        	logger.error("Error occured!",e.getMessage());

        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	//Delete a category
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
		String responseBody = new String();
        try {
        	logger.info("Deleting a categpry.");
            responseBody = categoryService.deleteCategory(id);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        	logger.error("Error occured!",e.getMessage());

        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	

	
	
}
