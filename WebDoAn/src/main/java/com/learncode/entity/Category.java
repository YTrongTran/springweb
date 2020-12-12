package com.learncode.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="qtht_category")
public class Category {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	@NotBlank(message = "Bắt buộc nhập tên cây")
	private String name;
	@Column(name="classify")
	@NotBlank(message = "Bắt buộc nhập tên cây cha")
	private String classify;
	
	@OneToMany(mappedBy = "category")
	List<Products> products;

	public Category() {
		super();
	}

	public Category(Long id, @NotBlank(message = "Bắt buộc nhập tên cây") String name,
			@NotBlank(message = "Bắt buộc nhập tên cây cha") String classify) {
		super();
		this.id = id;
		this.name = name;
		this.classify = classify;
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

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	
	
	
	
}
