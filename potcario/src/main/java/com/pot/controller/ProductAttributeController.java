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

import com.pot.model.ProductAttribute;
import com.pot.repository.ProductAttributeRepository;

@RestController
@RequestMapping("api/v1/")
public class ProductAttributeController {
	@Autowired
	private ProductAttributeRepository productAttributeRepository;

	@GetMapping(value = "product_attribute")
	public List<ProductAttribute> findAll() {
		return productAttributeRepository.findAll();
	}

	@GetMapping(value = "product_attribute/{id}")
	public ResponseEntity<ProductAttribute> findById(@PathVariable int id) {
		return productAttributeRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "product_attribute")
	public ProductAttribute create(@RequestBody ProductAttribute productAttribute) {
		return productAttributeRepository.save(productAttribute);
	}

	@PutMapping(value = "product_attribute/{id}")
	public ResponseEntity<ProductAttribute> update(@PathVariable int id,
			@RequestBody ProductAttribute productAttribute) {
		return productAttributeRepository.findById(id).map(record -> {
//			record.setName(productAttribute.getName());
//			record.setDescription(productAttribute.getDescription());
			ProductAttribute updated = productAttributeRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "product_attribute/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return productAttributeRepository.findById(id).map(record -> {
			productAttributeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
