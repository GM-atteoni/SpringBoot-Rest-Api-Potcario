package com.pot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class CartController {
	// End Points Carrinho
	@RequestMapping(value = "cart/{user}", method = RequestMethod.GET)
	public String cart(@PathVariable int user) {
		return "poções no carrinho do usuário: " + user;
	}

	// Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.POST)
	public String cartAdd(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Adiciona poção: " + id;
	}

	// Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.DELETE)
	public String cartDel(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Deleta poção: " + id;
	}

	// Não implementadas ainda
	@RequestMapping(value = "cart/{user}/{id}", method = RequestMethod.PATCH)
	public String cartUpdt(@PathVariable("user") int user, @PathVariable("id") int id) {
		return "Usuário:" + user + "Atualiza poção: " + id;
	}
}
