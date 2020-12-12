package com.learncode.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;





public class ProductsDTO {
	
	private	Long id;
	private String name;
	private Float price;
	private MultipartFile image;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productsdate;
	
	private Integer quantity;
	private String description;
	private String status;
	
	private Long categoryid;
	
	public ProductsDTO() {
		super();
	}

	public ProductsDTO(Long id, String name, Float price, MultipartFile image, Date productsdate, Integer quantity,
			String description, String status, Long categoryid) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.productsdate = productsdate;
		this.quantity = quantity;
		this.description = description;
		this.status = status;
		this.categoryid = categoryid;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
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

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	
	
	
	
}
