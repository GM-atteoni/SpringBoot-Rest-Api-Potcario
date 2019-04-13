package com.pot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pot.exception.AuthException;
import com.pot.model.Product;
import com.pot.model.Usuario;
import com.pot.repository.ProductRepository;
import com.pot.repository.UsuarioRepository;

@RestController
@RequestMapping("api/v1/")
public class PotsController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
		//End Points Usuário
	
	//Sign In
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String cadastro(@RequestBody String user /* <<< JSON*/) {

			try {
				//Valida se o JSON está correto
				Usuario usuario = new Gson().fromJson(user, Usuario.class);
				
				//Valida se já não existe
				Usuario usuaVerif = usuarioRepository.findByEmail(usuario.getEmail());
				if(usuaVerif == null) {
					
					return new Gson().toJson(usuarioRepository.save(usuario).getCustomeruserid());
					
				} else {
					throw new AuthException("Usuário já cadastrado");
				}
				
			} catch (JsonSyntaxException | AuthException e) {
				return new Gson().toJson(e);
			}
			
	}
	//Login
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(@RequestBody String login /* <<< JSON*/) {
		
		
		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(login).getAsJsonObject();
			String email = obj.get("email").getAsString();
			
			//Valida se existe
			Usuario usuaVerif = usuarioRepository.findByEmail(email);
			if(usuaVerif == null) {
				throw new AuthException("E-mail não cadastrado");
			} else {
				//Segurança que chama
				String senha = obj.get("senha").getAsString();
				if(usuaVerif.getPwd().equals(senha)) {
					return "Logado!";
				}else {
					throw new AuthException("Senha incorreta");
				}
			}
		} catch (AuthException e) {
			return new Gson().toJson(e);
		}
		
		}

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
