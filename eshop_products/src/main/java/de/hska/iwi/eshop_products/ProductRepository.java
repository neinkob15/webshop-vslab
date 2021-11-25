package de.hska.iwi.eshop_products;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String productName);
    List<Product> findAll();

    @Transactional
    void deleteByCategoryId(int id);

    @Query("SELECT p FROM Product p "
         + "where (:searchValue IS NULL OR ((p.name LIKE %:searchValue%) OR (p.details LIKE %:searchValue%))) "
         + "AND ((:priceMin IS NULL) OR (p.price >= :priceMin)) "
         + "AND ((:priceMax IS NULL) OR (p.price <= :priceMax)) "
         )
    List<Product> search(String searchValue, Double priceMin, Double priceMax);

    List<Product> findByNameLikeOrDetailsLike(String searchName, String searchDetails);
    List<Product> findByPriceBetween(Double priceMin, Double priceMax);
    List<Product> findByNameLikeOrDetailsLikeAndPriceBetween(String searchName, String searchDetails, Double priceMin, Double priceMax);
}