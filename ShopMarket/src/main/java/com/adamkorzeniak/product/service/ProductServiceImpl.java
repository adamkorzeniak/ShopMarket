package com.adamkorzeniak.product.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.product.model.Product;
import com.adamkorzeniak.product.repository.ProductRepository;
import com.adamkorzeniak.security.service.SecurityService;
import com.adamkorzeniak.user.model.User;
import com.adamkorzeniak.user.repository.UserRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public List<Product> findAllProducts() {
		List<Product> products =  productRepository.findAll();
		populateOwner(products);
		return products;
	}

	private void populateOwner(Collection<Product> products) {
		for(Product p: products) {
			String username = p.getUser().getUsername();
			p.setOwnerUsername(username);
			p.setSelfOwned(username.equals(securityService.getPrincipal()));
		}
	}

	@Override
	public Product addProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException();
		}
		String username = securityService.getPrincipal();
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new IllegalStateException();
		}
		product.setUser(user);
		product.setOwnerUsername(username);
		return productRepository.save(product);
	}

	@Override
	public Set<Product> search(String query) {
		if (query == null || query.length() == 0) {
			return new HashSet<Product>(findAllProducts());
		}
		query = "%" + query + "%";
		Set<Product> products = productRepository.search(query);
		populateOwner(products);
		return products;
	}
}
