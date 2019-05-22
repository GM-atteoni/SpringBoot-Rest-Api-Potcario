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

import com.pot.model.CartItem;
import com.pot.repository.CartItemRepository;

@RestController
@RequestMapping("api/v1/")
public class CartItemController {
	@Autowired
	private CartItemRepository cartItemRepository;

	@GetMapping(value = "cart_item")
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@GetMapping(value = "cart_item/{id}")
	public ResponseEntity<CartItem> findById(@PathVariable int id) {
		return cartItemRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "cart_item")
	public CartItem create(@RequestBody CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@PutMapping(value = "cart_item/{id}")
	public ResponseEntity<CartItem> update(@PathVariable int id, @RequestBody CartItem cartItem) {
		return cartItemRepository.findById(id).map(record -> {
//			record.setName(cartItem.getName());
//			record.setDescription(cartItem.getDescription());
			CartItem updated = cartItemRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "cart_item/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return cartItemRepository.findById(id).map(record -> {
			cartItemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
