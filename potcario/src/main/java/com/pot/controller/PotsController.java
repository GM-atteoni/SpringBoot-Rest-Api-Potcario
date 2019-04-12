package com.pot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pot.model.Product;
import com.pot.repository.ProductRepository;

@RestController
@RequestMapping("api/v1/")
public class PotsController {
	
	@Autowired
	private ProductRepository productRepository;
	
		//End Points Usuário
	
	//Sign In
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String cadastro(@RequestBody String user /* <<< JSON*/) {
		
		//utilizar o new Gson().fromJSON() para transformar o json em classe
		
		
		
		return "";
	}
	//Login
	
		//End Points Produtos

	//Mostrar todos
	@RequestMapping(value = "pots", method = RequestMethod.GET)
	public String pots() {
		List<Product> listProdutos = productRepository.findAll();
		String JSON = new Gson().toJson(listProdutos);
		
		return JSON;
	}
	//Mostrar uma
	@RequestMapping(value = "pots/{id}", method = RequestMethod.GET)
	public String potDetail(@PathVariable int id) {
		Product produto = productRepository.findById(id).orElse(new Product());

		String JSON = new Gson().toJson(produto);
		
		return JSON;
	}
	
	// End Points Carrinho
	@RequestMapping(value = "cart/{user}", method = RequestMethod.GET)
	public String cart(@PathVariable int user) {
		return "poções no carrinho do usuário: " + user;
	}
	
	//Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.POST)
	public String cartAdd(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Adiciona poção: " + id;
	}
	//Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.DELETE)
	public String cartDel(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Deleta poção: " + id;
	}
	//Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.PATCH)
	public String cartUpdt(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Atualiza poção: " + id;
	}
	
	@RequestMapping(value = "*")
	public String notFound() {
		return "Página não existe";
	}

}
