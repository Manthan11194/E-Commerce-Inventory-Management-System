package com.example.assignment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.assignment.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{
    Page<Products> findAll(Pageable pageable);

}
