package com.adamkorzeniak.product.service;

import java.util.List;
import java.util.Set;

import com.adamkorzeniak.product.model.Product;

public interface ProductService {

	List<Product> findAllProducts();

	Product addProduct(Product product);

	Set<Product> search(String query);

}
