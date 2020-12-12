package com.learncode.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.Repository.CategoryRepository;
import com.learncode.entity.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository cateRepo;
	

	@Override
	public Category findById(Long id) {
		//return this.entity.find(Category.class, id);
		return cateRepo.findById(id).get();
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) cateRepo.findAll(); 
	}

	

	@Override
	public Category save(Category entity) {
		return cateRepo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.cateRepo.deleteById(id);
	}

	@Override
	public Page<Category> findPaginated(int pageNo, int pageSize) {
		Pageable pageble = PageRequest.of(pageNo -1, pageSize);
		return this.cateRepo.findAll(pageble);
	}
}
