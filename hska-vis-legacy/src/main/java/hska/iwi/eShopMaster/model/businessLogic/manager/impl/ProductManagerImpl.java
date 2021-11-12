package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public class ProductManagerImpl implements ProductManager {
    
    final WebTarget target;
    final WebTarget catTarget;

    public ProductManagerImpl() {
        Client client = ClientBuilder.newClient();
        target = client.target(UriBuilder.fromUri("http://web-shop-products:8081/").build());
        catTarget = client.target(UriBuilder.fromUri("http://web-shop-categories:8082/").build());
    }

    public List<Product> getProducts() {
        List<Product> products = target
          .path("products")
          .request(MediaType.APPLICATION_JSON)
          .get(new GenericType<List<Product>> () {});
        return products;
    }

    public Product getProductById(int id) {
        return target
        .path("products/" + String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .get(Product.class);
    }

    public Product getProductByName(String name) {
        List<Product> products = target
        .path("products?name=" + name)
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Product>> () {});
        if (products.size() > 0) {
            return products.get(0);
        } else {
            return null;
        }
    }

    public int addProduct(String name, double price, int categoryId, String details) {
        Category cat = catTarget
        .path("categories/" + String.valueOf(categoryId))
        .request(MediaType.APPLICATION_JSON)
        .get(Category.class);
        Product p = new Product(name, price, cat, details);
        int id = target
        .path("products")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.json(p), Integer.class);
        return id;
    }

    public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice) {
        return target
        .path("products")
        .queryParam("searchValue", searchValue)
        .queryParam("minPrice", searchMinPrice)
        .queryParam("maxPrice", searchMaxPrice)
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Product>> () {});
    }

    public boolean deleteProductsByCategoryId(int categoryId) {
        // TODO Auto-generated method stub
        return false;
    }

    public void deleteProductById(int id) {
        target
        .path("products/" + String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .delete(String.class);
    }
}
