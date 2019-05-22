package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
