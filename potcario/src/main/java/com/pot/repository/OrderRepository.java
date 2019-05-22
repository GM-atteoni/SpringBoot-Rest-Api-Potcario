package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
