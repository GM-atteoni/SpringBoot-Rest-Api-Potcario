package com.pot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pot.exception.AuthException;
import com.pot.model.User;
import com.pot.repository.UserRepository;

@RestController
@RequestMapping("api/v1/")
public class UserController {
	@Autowired
	private UserRepository usuarioRepository;

	// Sign In
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String cadastro(@RequestBody String user /* <<< JSON */) {

		try {
			// Valida se o JSON está correto
			User usuario = new Gson().fromJson(user, User.class);

			// Valida se já não existe
			User usuaVerif = usuarioRepository.findByEmail(usuario.getEmail());
			if (usuaVerif == null) {

				return new Gson().toJson(usuarioRepository.save(usuario).getCustomeruserid());

			} else {
				throw new AuthException("Usuário já cadastrado");
			}

		} catch (JsonSyntaxException | AuthException e) {
			return new Gson().toJson(e);
		}

	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(@RequestBody String login /* <<< JSON */) {

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(login).getAsJsonObject();
			String email = obj.get("email").getAsString();

			// Valida se existe
			User usuaVerif = usuarioRepository.findByEmail(email);
			if (usuaVerif == null) {
				throw new AuthException("E-mail não cadastrado");
			} else {
				// Segurança que chama
				String senha = obj.get("senha").getAsString();
				if (usuaVerif.getPwd().equals(senha)) {
					return "Logado!";
				} else {
					throw new AuthException("Senha incorreta");
				}
			}
		} catch (AuthException e) {
			return new Gson().toJson(e);
		}

	}
}
