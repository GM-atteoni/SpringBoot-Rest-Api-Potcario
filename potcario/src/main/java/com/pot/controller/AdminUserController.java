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

import com.pot.model.AdminUser;
import com.pot.repository.AdminUserRepository;

@RestController
@RequestMapping("api/v1/")
public class AdminUserController {
	@Autowired
	private AdminUserRepository adminUserRepository;

	@GetMapping(value = "admin_user")
	public List<AdminUser> findAll() {
		return adminUserRepository.findAll();
	}

	@GetMapping(value = "admin_user/{id}")
	public ResponseEntity<AdminUser> findById(@PathVariable int id) {
		return adminUserRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "admin_user")
	public AdminUser create(@RequestBody AdminUser adminUser) {
		return adminUserRepository.save(adminUser);
	}

	@PutMapping(value = "admin_user/{id}")
	public ResponseEntity<AdminUser> update(@PathVariable int id, @RequestBody AdminUser adminUser) {
		return adminUserRepository.findById(id).map(record -> {
//			record.setName(adminUser.getName());
//			record.setDescription(adminUser.getDescription());
			AdminUser updated = adminUserRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "admin_user/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return adminUserRepository.findById(id).map(record -> {
			adminUserRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
