package com.pot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pot.model.Product;
import com.pot.repository.ProductRepository;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public String pots() {
		List<Product> productList = productRepository.findAll();
		String JSON = new Gson().toJson(productList);

		return JSON;
	}

	@RequestMapping(value = "product/{id}", method = RequestMethod.GET)
	public String potDetail(@PathVariable int id) {
		Product product = productRepository.findById(id).orElse(new Product());

		String JSON = new Gson().toJson(product);

		return JSON;
	}
}
