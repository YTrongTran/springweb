package com.learncode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.learncode.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{
	
}
