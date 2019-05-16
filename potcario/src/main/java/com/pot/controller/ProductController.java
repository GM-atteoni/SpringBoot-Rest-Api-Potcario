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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pot.model.Product;
import com.pot.repository.ProductRepository;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value = "product")
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@GetMapping(value = "product/{id}")
	public ResponseEntity<Product> findById(@PathVariable int id) {
		return productRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "product")
	public Product create(@RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping(value = "product/{id}")
	public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
		return productRepository.findById(id).map(record -> {
			record.setName(product.getName());
			record.setDescription(product.getDescription());
			Product updated = productRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "product/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return productRepository.findById(id).map(record -> {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
