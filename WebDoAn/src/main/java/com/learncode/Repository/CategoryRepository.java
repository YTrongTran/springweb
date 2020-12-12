package com.learncode.Repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learncode.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
}
