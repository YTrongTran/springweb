package com.learncode.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="qtht_products")
public class Products {
	
	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	Long id;
	private String name;
	private Float price;
	private String image;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productsdate;
	private Integer quantity;
	private String description;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	
	public Products() {
		super();
	}

	public Products(Long id, String name, Float price, String image, Date productsdate, Integer quantity,
			String description, String status, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.productsdate = productsdate;
		this.quantity = quantity;
		this.description = description;
		this.status = status;
		
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getProductsdate() {
		return productsdate;
	}

	public void setProductsdate(Date productsdate) {
		this.productsdate = productsdate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
	
	
}
