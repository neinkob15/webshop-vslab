package de.hska.iwi.eshop_products;


import java.util.List;
import java.util.Optional;

public interface ProductManager {

	public List<Product> getProducts();

	public Product getProductById(int id);

	public Product getProductByName(String name);

	public int addProduct(String name, double price, int categoryId, String details);

	public List<Product> getProductsForSearchValues(Optional<String> searchValue, Optional<Double> minPrice, Optional<Double> maxPrice);
	
	public boolean deleteProductsByCategoryId(int categoryId);
	
    public void deleteProductById(int id);
    
	
}