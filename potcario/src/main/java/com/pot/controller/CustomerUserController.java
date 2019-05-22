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

import com.pot.model.CustomerUser;
import com.pot.repository.CustomerUserRepository;

@RestController
@RequestMapping("api/v1/")
public class CustomerUserController {
	@Autowired
	private CustomerUserRepository customerUserRepository;

	@GetMapping(value = "customer_user")
	public List<CustomerUser> findAll() {
		return customerUserRepository.findAll();
	}

	@GetMapping(value = "customer_user/{id}")
	public ResponseEntity<CustomerUser> findById(@PathVariable int id) {
		return customerUserRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "customer_user")
	public CustomerUser create(@RequestBody CustomerUser customerUser) {
		return customerUserRepository.save(customerUser);
	}

	@PutMapping(value = "customer_user/{id}")
	public ResponseEntity<CustomerUser> update(@PathVariable int id, @RequestBody CustomerUser customerUser) {
		return customerUserRepository.findById(id).map(record -> {
//			record.setName(customerUser.getName());
//			record.setDescription(customerUser.getDescription());
			CustomerUser updated = customerUserRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "customer_user/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return customerUserRepository.findById(id).map(record -> {
			customerUserRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
