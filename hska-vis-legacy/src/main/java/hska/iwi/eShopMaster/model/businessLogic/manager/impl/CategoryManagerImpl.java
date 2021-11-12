package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

public class CategoryManagerImpl implements CategoryManager {

    final WebTarget target;

    public CategoryManagerImpl() {
        Client client = ClientBuilder.newClient();
        target = client.target(UriBuilder.fromUri("http://web-shop-categories:8082/").build());
    }

    public List<Category> getCategories() {
        List<Category> categories = target
          .path("categories")
          .request(MediaType.APPLICATION_JSON)
          .get(new GenericType<List<Category>> () {});
        return categories;
    }

    public Category getCategory(int id) {
        return target
        .path("categories/" + String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .get(Category.class);
    }

    public Category getCategoryByName(String name) {
        List<Category> products = target
        .path("categories?name=" + name)
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Category>> () {});
        if (products.size() > 0) {
            return products.get(0);
        } else {
            return null;
        }
    }

    public void addCategory(String name) {
        Category c = new Category(name);
        target
        .path("categories")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.json(c), String.class);
    }

    public void delCategory(Category cat) {
        this.delCategoryById(cat.getId());
    }

    public void delCategoryById(int id) {
        target
        .path("categories/" + String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .delete(String.class);
    }
    
}
