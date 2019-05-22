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

import com.pot.model.ProductCategory;
import com.pot.repository.ProductCategoryRepository;

@RestController
@RequestMapping("api/v1/")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@GetMapping(value = "product_category")
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	@GetMapping(value = "product_category/{id}")
	public ResponseEntity<ProductCategory> findById(@PathVariable int id) {
		return productCategoryRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "product_category")
	public ProductCategory create(@RequestBody ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	@PutMapping(value = "product_category/{id}")
	public ResponseEntity<ProductCategory> update(@PathVariable int id, @RequestBody ProductCategory productCategory) {
		return productCategoryRepository.findById(id).map(record -> {
//			record.setName(productCategory.getName());
//			record.setDescription(productCategory.getDescription());
			ProductCategory updated = productCategoryRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "product_category/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return productCategoryRepository.findById(id).map(record -> {
			productCategoryRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
