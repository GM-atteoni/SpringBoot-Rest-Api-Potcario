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

import com.pot.model.Category;
import com.pot.repository.CategoryRepository;

@RestController
@RequestMapping("api/v1/")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(value = "category")
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@GetMapping(value = "category/{id}")
	public ResponseEntity<Category> findById(@PathVariable int id) {
		return categoryRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "category")
	public Category create(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@PutMapping(value = "category/{id}")
	public ResponseEntity<Category> update(@PathVariable int id, @RequestBody Category category) {
		return categoryRepository.findById(id).map(record -> {
//			record.setName(category.getName());
//			record.setDescription(category.getDescription());
			Category updated = categoryRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "category/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return categoryRepository.findById(id).map(record -> {
			categoryRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
