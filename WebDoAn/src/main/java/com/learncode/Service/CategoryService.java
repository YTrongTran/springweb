package com.learncode.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.learncode.entity.Category;

public interface CategoryService {

	void deleteById(Long id);
	Category findById(Long id);

	List<Category> findAll();
	Category save(Category entity);
Page<Category> findPaginated(int pageNo, int pageSize);
	

}
