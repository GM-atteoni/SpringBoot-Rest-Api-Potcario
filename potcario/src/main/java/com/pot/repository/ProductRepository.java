package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
