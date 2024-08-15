package com.example.assignment.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.assignment.controllers.ProductController;
import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.dtos.ProductDto;
import com.example.assignment.dtos.ProductRequestDto;
import com.example.assignment.models.Category;
import com.example.assignment.models.Products;
import com.example.assignment.repositories.CategoryRepository;
import com.example.assignment.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepo;
	private CategoryRepository categoryRepo;
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	ProductServiceImpl(ProductRepository productRepo,CategoryRepository categoryRepo){
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public String createProduct(ProductRequestDto productDto) {
		logger.info("Create a product.");
		Products product = new Products();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		
		Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
		if(categoryOptional.isPresent()) {
			product.setCategory(categoryOptional.get());
		}
		productRepo.save(product);
		logger.info("Product Created Successfully.");
		return "Product added successfully.";
	}

	@Override
	public Page<Products> getAllProducts(Pageable pageable) {
		Page<Products> products = productRepo.findAll(pageable);
		logger.info("Feching Products.");
		return products;
	}

	@Override
	public ProductDto getProductById(Integer id) {
		ProductDto productDto = new ProductDto();
		Optional<Products> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			productDto = convetModelToProductDto(productOptional.get());
		}
		logger.info("Feching Product By ID.");
		return productDto;
		
	}
	private ProductDto convetModelToProductDto(Products product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(product.getCategory().getId());
		categoryDto.setName(product.getCategory().getName());
		
		productDto.setCategoryDto(categoryDto);
		
		return productDto;
	}

	@Override
	public String updateProduct(Integer id, ProductDto productDto) {
		Optional<Products> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			Products product = productOptional.get();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			productRepo.save(product);
			logger.info("Update Product.");
			return "Product updated successfully.";
		}
		
		return "Product is not updated, check logs";
	
		
	}

	@Override
	public String deleteProduct(Integer id) {
		Optional<Products> productOptional = productRepo.findById(id);
		if(productOptional.isPresent()) {
			productRepo.deleteById(id);
			logger.info("Delete Product.");
			return "Product with id :"+id+ " is deleted.";
		}
		
		return "Product is not exists.";
	}

	
	
}
