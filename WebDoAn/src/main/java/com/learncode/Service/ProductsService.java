package com.learncode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.learncode.entity.Category;
import com.learncode.entity.Products;

public interface ProductsService {

	void deleteById(Long id);
	Products save(Products entity);
	List<Products> findAll();
	List<Category> findCategoryAll();
	Optional<Products> findById(Long id);

	Page<Products> findPaginated(int pageNo, int pageSize);
	


}
