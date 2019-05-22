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

import com.pot.model.Payment;
import com.pot.repository.PaymentRepository;

@RestController
@RequestMapping("api/v1/")
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;

	@GetMapping(value = "payment")
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@GetMapping(value = "payment/{id}")
	public ResponseEntity<Payment> findById(@PathVariable int id) {
		return paymentRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "payment")
	public Payment create(@RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}

	@PutMapping(value = "payment/{id}")
	public ResponseEntity<Payment> update(@PathVariable int id, @RequestBody Payment payment) {
		return paymentRepository.findById(id).map(record -> {
//			record.setName(payment.getName());
//			record.setDescription(payment.getDescription());
			Payment updated = paymentRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "payment/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return paymentRepository.findById(id).map(record -> {
			paymentRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
