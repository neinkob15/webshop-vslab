package de.hska.iwi.eshop_categories;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This class contains details about categories.
 */
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	public Category() {
	}

	public int getId() {
		return this.id;
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
}
