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

import com.pot.model.Cart;
import com.pot.repository.CartRepository;

@RestController
@RequestMapping("api/v1/")
public class CartController {
	@Autowired
	private CartRepository cartRepository;

	@GetMapping(value = "cart")
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	@GetMapping(value = "cart/{id}")
	public ResponseEntity<Cart> findById(@PathVariable int id) {
		return cartRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "cart")
	public Cart create(@RequestBody Cart cart) {
		return cartRepository.save(cart);
	}

	@PutMapping(value = "cart/{id}")
	public ResponseEntity<Cart> update(@PathVariable int id, @RequestBody Cart cart) {
		return cartRepository.findById(id).map(record -> {
//			record.setName(cart.getName());
//			record.setDescription(cart.getDescription());
			Cart updated = cartRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "cart/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return cartRepository.findById(id).map(record -> {
			cartRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
