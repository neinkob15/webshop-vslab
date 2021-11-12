package de.hska.iwi.eshop_categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class CategoryController implements CategoryManager {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  ProductManager productManager;

  @GetMapping("/categories")
  public List<Category> getCategories(@RequestParam("name") Optional<String> name) {
    if (name.isPresent()) {
      List<Category> l = new ArrayList<Category>();
      try {
        l.add(this.getCategoryByName(name.get()));
      } catch (CategoryNotFoundException e) {}
      return l;
    } else {
      return categoryRepository.findAll();
    } 
  }

  @Override
  public List<Category> getCategories() {
    return null;
  }

  @Override
  @GetMapping("/categories/{id}")
  public Category getCategory(@PathVariable int id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
  }

  @PostMapping("/categories")
  public void addFullCategory(@RequestBody Category c) {
    this.addCategory(c.getName());
  }


  @Override
  public void addCategory(String name) {
    Category c = new Category();
    c.setName(name);
    this.categoryRepository.save(c);
  }

  @Override
  public void delCategory(Category cat) {
    this.categoryRepository.delete(cat);
  }

  @Override
  @DeleteMapping("/categories/{id}")
  public void delCategoryById(@PathVariable int id) {
    this.productManager.deleteProductsByCategoryId(id);
    this.categoryRepository.deleteById(id);
  }

  
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class CategoryNotFoundException extends RuntimeException{
  public CategoryNotFoundException(int categoryId){
    super("category " + categoryId + " does not exist.");
  }
  public CategoryNotFoundException(String categoryName){
    super("category " + categoryName + " does not exist.");
  }
}