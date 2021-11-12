package de.hska.iwi.eshop_products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Category category;

	@Column(name = "price")
	private double price;

	@JsonIgnore
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "details")
	private String details;

	public Product() {
	}

	public Product(String name, double price, int categoryId) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

	public Product(String name, double price, int categoryId, String details) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.details = details;
	}

	public int getId() {
		return this.id;
	}

	@Transient
	public void setCategory(Category cat) {
		this.category = cat;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return this.categoryId;
	}
	public Category getCategory() {
		return this.category;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
