package com.pot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pot.model.Order;
import com.pot.repository.OrderRepository;

@RestController
@RequestMapping("api/v1/")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(value = "order")
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@GetMapping(value = "order/{id}")
	public ResponseEntity<Order> findById(@PathVariable int id) {
		return orderRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "order")
	public Order create(@RequestBody Order order) {
		return orderRepository.save(order);
	}

	@PutMapping(value = "order/{id}")
	public ResponseEntity<Order> update(@PathVariable int id, @RequestBody Order order) {
		return orderRepository.findById(id).map(record -> {
//			record.setName(order.getName());
//			record.setDescription(order.getDescription());
			Order updated = orderRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "order/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return orderRepository.findById(id).map(record -> {
			orderRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
