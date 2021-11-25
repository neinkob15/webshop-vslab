package de.hska.iwi.eshop_products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.PureJavaCrc32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController implements ProductManager {

  @Autowired
  ProductRepository productRepository;
  
  @Autowired
  CategoryManager categoryManager;


  @GetMapping("/products")
  public List<Product> getAllProducts(@RequestParam("name") Optional<String> name, 
                                      @RequestParam("searchValue") Optional<String> searchValue,
                                      @RequestParam("minPrice") Optional<Double> minPrice,
                                      @RequestParam("maxPrice") Optional<Double> maxPrice) {
    if (name.isPresent()) {
      List<Product> l = new ArrayList<Product>();
      try {
        Product p = this.getProductByName(name.get());
        p.setCategory(categoryManager.getById(p.getCategoryId()));
        l.add(p);
      } catch (ProductNotFoundException e) {}
      return l;
    } else if (searchValue.isPresent() || minPrice.isPresent() || maxPrice.isPresent()) {
      List<Product> products = this.getProductsForSearchValues(searchValue, minPrice, maxPrice);
      for (int i = 0; i < products.size(); i++) {
        Product temp = products.get(i);
        temp.setCategory(categoryManager.getById(products.get(i).getCategoryId()));
        products.set(i, temp);
      }
      return products;
    } else {
      List<Product> products = this.getProducts();
      for (int i = 0; i < products.size(); i++) {
        Product temp = products.get(i);
        temp.setCategory(categoryManager.getById(products.get(i).getCategoryId()));
        products.set(i, temp);
      }
      return products;
    }
  }

  @Override
  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  @Override
  @GetMapping("/products/{id}")
  public Product getProductById(@PathVariable int id) {
    Product p = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    p.setCategory(categoryManager.getById(p.getCategoryId()));
    return p;
  }

  @Override
  public Product getProductByName(String name) {
    return productRepository.findByName(name).orElseThrow(() -> new ProductNotFoundException(name));
  }

  @PostMapping("/products")
  public int createProduct(@RequestBody Product p) {
    p.setCategoryId(p.getCategory().getId());
    categoryManager.getById(p.getCategoryId());
    Product newP = this.productRepository.save(p);
    return newP.getId();
  }

  @Override
  public int addProduct(String name, double price, int categoryId, String details) {
    return 0;
  }

  @Override
  public List<Product> getProductsForSearchValues(Optional<String> searchValue, Optional<Double> minPrice, Optional<Double> maxPrice) {
    if(searchValue.isPresent()) {
      if(minPrice.isPresent() && maxPrice.isPresent()) {
        return productRepository.findByNameLikeOrDetailsLikeAndPriceBetween(searchValue.get(),searchValue.get(),
                minPrice.get(), maxPrice.get());
      }
      return productRepository.findByNameLikeOrDetailsLike(searchValue.get(),searchValue.get());
    } else if(minPrice.isPresent() && maxPrice.isPresent()) {
        return productRepository.findByPriceBetween(minPrice.get(), maxPrice.get());
    }
    return new ArrayList<Product>();
  }

  @Override
  @DeleteMapping("/products")
  public boolean deleteProductsByCategoryId(@RequestParam("categoryId") int categoryId) {
    this.productRepository.deleteByCategoryId(categoryId);
    return true;
  }

  @Override
  @DeleteMapping("/products/{id}")
  public void deleteProductById(@PathVariable int id) {
    this.productRepository.deleteById(id);
  }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException extends RuntimeException{
  public ProductNotFoundException(int productId){
    super("Product " + productId + " does not exist.");
  }
  public ProductNotFoundException(String productName){
    super("Product " + productName + " does not exist.");
  }
}