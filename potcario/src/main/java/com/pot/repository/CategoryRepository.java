package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
