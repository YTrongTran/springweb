package com.learncode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.Repository.CategoryRepository;
import com.learncode.Repository.ProductsRepository;
import com.learncode.entity.Category;
import com.learncode.entity.Products;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	CategoryRepository categoryRepository; 

	@Override
	public List<Category> findCategoryAll() {
		return (List<Category>) categoryRepository.findAll(); 
	}

	
	@Override
	public Optional<Products> findById(Long id) {
		return productsRepository.findById(id);
	}

	@Override
	public List<Products> findAll() {
		return (List<Products>) productsRepository.findAll(); 
	}


	@Override
	public Products save(Products entity) {
		return productsRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.productsRepository.deleteById(id);
	}


	@Override
	public Page<Products> findPaginated(int pageNo, int pageSize) {
	
		Pageable pageble = PageRequest.of(pageNo -1, pageSize);
		return this.productsRepository.findAll(pageble);
	}



}
