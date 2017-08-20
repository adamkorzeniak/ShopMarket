package com.adamkorzeniak.product.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.adamkorzeniak.product.model.Product;
import com.adamkorzeniak.product.repository.ProductRepository;
import com.adamkorzeniak.security.service.SecurityService;
import com.adamkorzeniak.user.model.User;
import com.adamkorzeniak.user.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private SecurityService securityService;
	
	@Mock
	private UserRepository userRepository;
	
    @InjectMocks
	private ProductService productService  = new ProductServiceImpl();
    
    @Before
    public void setUp() {
    	when(securityService.getPrincipal())
		.thenReturn("Adam");
    }
	
	@Test
	public void findAllProducts_allReturned() {
		List<Product> products = createProducts(5);
		
		when(productRepository.findAll())
		.thenReturn(products);
		
		List<Product> productsResult = productService.findAllProducts();
		
		assertNotNull(productsResult);
		assertEquals(5, productsResult.size());
	}
	
	@Test
	public void findAllProducts_ownerUsernameNotNull() {
		
		List<Product> products = createProducts(3);
		
		when(productRepository.findAll())
		.thenReturn(products);
		
		List<Product> productsResult = productService.findAllProducts();
		
		assertNotNull(productsResult);
		for (Product p: productsResult) {
			assertNotNull(p.getOwnerUsername());
			assertNotNull(p.isSelfOwned());
		}
	}
	
	@Test
	public void findAllProducts_noProducts() {
		
		List<Product> products = new ArrayList<>();
		
		when(productRepository.findAll())
		.thenReturn(products);
		
		List<Product> productsResult = productService.findAllProducts();
		
		assertNotNull(productsResult);
		assertEquals(0, productsResult.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addProduct_nullProduct() {
		productService.addProduct(null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void addProduct_NoSuchUser() {
		String username = "Grzesiek";
		Product p = new Product();
		User user = new User();
		user.setUsername(username);
		p.setUser(user);
		
		when(userRepository.findByUsername(any()))
		.thenReturn(null);
		
		productService.addProduct(p);
	}
	
	@Test
	public void addProduct_ValidProduct() {
		Product p = product(1);
		
		when(userRepository.findByUsername(any()))
		.thenReturn(p.getUser());
		when(productRepository.save(p))
		.thenReturn(p);
		
		Product result = productService.addProduct(p);
		
		assertNotNull(result);
		assertNotNull(result.getUser());
		assertEquals(securityService.getPrincipal(), p.getOwnerUsername());
	}
	
	@Test
	public void search_EmptyString_ReturnAll() {
		List<Product> products = createProducts(5);
		
		when(productRepository.findAll())
		.thenReturn(products);
		
		Set<Product> productResults = productService.search("");
		
		assertNotNull(productResults);
		assertEquals(5, productResults.size());
	}
	
//	@Test
//	public void search_StrangeQuery_NoResults() {
//		List<Product> products = createProducts(5);
//		
//		when(productRepository.search())
//		.thenReturn(products);
//		
//		Set<Product> productResults = productService.search("");
//		
//		assertNotNull(productResults);
//		assertEquals(5, productResults.size());
//	}
//	
	
	
	private List<Product> createProducts(int size) {
		
		List<Product> products = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			products.add(product(i));
		}
		return products;
	}
	
	private Product product(long id) {
		return product(id, null);
	}
	
	private Product product(long id, String username) {
		
		User user = new User();
		if (username == null) {
			username = "Adam " + id%3;
		}
		user.setUsername(username);
		
		Product p = new Product();
		
		p.setId(id);
		p.setDescription("Product "+ id);
		p.setName("p" + id);
		p.setPrice(id * 1000.00);
		p.setUser(user);
		
		return p;
	}
}
