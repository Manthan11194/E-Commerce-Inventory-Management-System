package com.example.assignment.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.dtos.ProductDto;
import com.example.assignment.dtos.ProductRequestDto;
import com.example.assignment.models.Category;
import com.example.assignment.models.Products;

public interface ProductService {
	
	String createProduct(ProductRequestDto product);
	
	Page<Products> getAllProducts(Pageable pageable);
	
	ProductDto getProductById(Integer id);
	
	String updateProduct(Integer id,ProductDto product);
	
	String deleteProduct(Integer id);

}
