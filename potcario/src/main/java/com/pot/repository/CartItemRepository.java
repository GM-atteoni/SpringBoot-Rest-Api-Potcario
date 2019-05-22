package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
