package com.adamkorzeniak.product.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adamkorzeniak.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.name Like ?1 OR p.description Like ?1")
	Set<Product> search(String query);
	
	@Query("select p from Product p where p.name Like ?1")
	Set<Product> searchByName(String query);
	
	@Query("select p from Product p where p.description Like ?1")
	Set<Product> searchByDescription(String query);
}
