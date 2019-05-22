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

import com.pot.model.PaymentAttribute;
import com.pot.repository.PaymentAttributeRepository;

@RestController
@RequestMapping("api/v1/")
public class PaymentAttributeController {
	@Autowired
	private PaymentAttributeRepository paymentAttributeRepository;

	@GetMapping(value = "payment_attribute")
	public List<PaymentAttribute> findAll() {
		return paymentAttributeRepository.findAll();
	}

	@GetMapping(value = "payment_attribute/{id}")
	public ResponseEntity<PaymentAttribute> findById(@PathVariable int id) {
		return paymentAttributeRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "payment_attribute")
	public PaymentAttribute create(@RequestBody PaymentAttribute paymentAttribute) {
		return paymentAttributeRepository.save(paymentAttribute);
	}

	@PutMapping(value = "payment_attribute/{id}")
	public ResponseEntity<PaymentAttribute> update(@PathVariable int id,
			@RequestBody PaymentAttribute paymentAttribute) {
		return paymentAttributeRepository.findById(id).map(record -> {
//			record.setName(paymentAttribute.getName());
//			record.setDescription(paymentAttribute.getDescription());
			PaymentAttribute updated = paymentAttributeRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "payment_attribute/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return paymentAttributeRepository.findById(id).map(record -> {
			paymentAttributeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
